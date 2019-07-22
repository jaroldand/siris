package sbs.siris.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import sbs.cross.config.Setup;
import sbs.cross.log.Record;
import sbs.extranet.autenticacion.javabean.UsuarioSession;
import sbs.siris.web.controller.base.AsyncResult;
import sbs.siris.web.controller.base.HelperController;
import sbs.siris.web.controller.base.SecurityService;

@Controller
@RequestMapping("/start")
public class StartController {

	@Autowired
	private SecurityService securityService;

	@GetMapping
	public DeferredResult<String> token(@RequestParam("token") String token, HttpServletRequest request) {
		return AsyncResult.Call(() -> {
			/*String tokenNew = new String(Base64.getUrlDecoder().decode(token));
			String url = HelperController.URL_ERROR;
			if (tokenNew.length() == 0)
				url += HelperController.TOKEN_NO_EXIST;
			else {
				String[] val = null;
				try {
					// 1.- Validación si el token es correcto
					val = AesCustom.desencriptarAes(tokenNew, HelperController.KEY_PUBLIC).split("\\|");

					// 2.- Tipo de aplicación
					String app = val[0];

					// 2.- val[1] => Validación si el timeout
					Date dateCurrent = DateTime.now();
					Date dateToken = DateTime.dateFromString(val[1], HelperController.FORMAT_DATE);
					if (dateCurrent.getTime() > dateToken.getTime())
						throw new Exception("WEB: el token ha caducado.");

					// 3.- val[2] => Obtener el usuario
					String usuario = val[2];
					// 3.- val[0] => Validación del tipo de aplicación
					if (app.equals("app")) {
						// 4.- redirec a la página de registro coopac
						url = Setup.getValueKey("uriRegistroSiris.properties", "start");
					} else if (app.equals("add")) {
						// 4.- redirec a la página de creación de coopac
						//url = Setup.getValueKey("uriCreacionUsuario.properties", "start");
					}

					// INICIANDO SESIÓN
					securityService.autologin(usuario, request);

				} catch (Exception e) {
					Record.error(e);
					url = HelperController.URL_ERROR;
					url += e.getMessage();
				}

			}*/
			
			String url = HelperController.URL_ERROR;
			try {
				
		        UsuarioSession usuarioSession = (UsuarioSession) request.getSession().getAttribute("usuarioSession");
		        if(usuarioSession == null)
		        	throw new Exception("El usuario no existe.");
		        
		        url = Setup.getValueKey("uriRegistroSiris.properties", "start");
		        
 		        // INICIANDO SESIÓN
				securityService.autologin(usuarioSession.getC_c_usuario(), usuarioSession, request);
		        
			} catch (Exception e) {
				Record.error(e);
				url = HelperController.URL_ERROR;
				url += e.getMessage();
			}
		        
		        
			return "redirect:" + url;
		});

	}

	@GetMapping("/error")
	public ModelAndView error(@RequestParam("msg") String msg) {
		ModelAndView model = new ModelAndView("pub/global/error.html");
		model.addObject("msg", msg);
		return model;
	}

	@GetMapping("/irOpciones")
	public DeferredResult<String> login(HttpServletRequest request) {
		return AsyncResult.Call(() -> {
			String url = HelperController.URL_ERROR;
			HttpSession session = request.getSession(false);
			if(session != null) {
				session.invalidate();
			}
			url = Setup.getValueKey("urlBase.properties", "url_coopac_login_menu");
			return "redirect:" + url + "/menu.jsp";
		});
	}
	
	@GetMapping("/logout")
	public DeferredResult<String> logout(HttpServletRequest request) {
		return AsyncResult.Call(() -> {
			String url = HelperController.URL_ERROR;
			HttpSession session = request.getSession(false);
			if(session != null) {
				session.invalidate();
			}
						
			url = Setup.getValueKey("urlBase.properties", "url_logout_menu");
			return "redirect:" + url;
		});
	}	
	
	@GetMapping("/timeoutCoopac")
	public DeferredResult<String> timeout(HttpServletRequest request,HttpServletResponse response) {
		return AsyncResult.Call(() -> {
			HttpSession session = request.getSession(false);
			session.invalidate();			
			return "/pub/logout.html";
		});

	}
}
