package sbs.siris.web.controller.base;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import sbs.cross.config.Setup;
import sbs.cross.crypto.AesCustom;
import sbs.cross.util.DateTime;
import sbs.extranet.autenticacion.javabean.UsuarioSession;
import siris.web.base.UserToken;

@RestController
@RequestMapping("/service_discovery")
public class ServiceDiscovery {

	// Aqui usar algun parámentro de session.(obligatorio)
	@GetMapping("/token")
	public DeferredResult<UserToken> getToken(HttpSession session) {
		String userName = "unauthorized";
		String passwd = "unauthorized";
		String codEntVig = "unauthorized";
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null) {
			Object principal = authentication.getPrincipal();
			passwd = authentication.getCredentials().toString();
			if (principal instanceof UsuarioSession) {
				userName = ((UsuarioSession) principal).getC_c_usuario();
				codEntVig = ((UsuarioSession) principal).getUsuario().getC_c_ent_vigilada();
			}
			else
				userName = principal.toString();
			//
			String sfTime = DateTime.stringFromDate(DateTime.nowAddSec(HelperController.TIMEOUT_TOKEN), HelperController.FORMAT_DATE);
			passwd = AesCustom.encriptarAes(passwd, HelperController.KEY_PUBLIC);
			userName = AesCustom.encriptarAes(userName + "|" + sfTime + "|" + codEntVig, HelperController.KEY_PUBLIC);
			//
		}

		final String passwdFinal = passwd;
		final String userNameFinal = userName;
		return AsyncResult.Call(() -> {
			UserToken user = new UserToken();
			user.setTicket(userNameFinal);
			user.setToken(passwdFinal);
			return user;
		});
	}
	@GetMapping("/tokenCoopac")
	public DeferredResult<String> getIdCoopacEncoded(){
		Integer idCoopac = HelperController.getIdCoopac();
		if(idCoopac == null)
			return null;
		String sfTime = DateTime.stringFromDate(DateTime.nowAddSec(HelperController.TIMEOUT_TOKEN), HelperController.FORMAT_DATE);
		String encriptado = AesCustom.encriptarAes(idCoopac.toString()+ "|" + sfTime, HelperController.KEY_PUBLIC);
		return AsyncResult.Call(() -> {			
			return HelperController.codeForUrl(encriptado);
		});
		
	}
	@GetMapping("/urls")
	public DeferredResult<Map<String, String>> getUrl(@RequestParam("propertie") String propertie) {
		return AsyncResult.Call(() -> {
			return Setup.getHashMap(propertie);
		});
	}
}
