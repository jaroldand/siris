package sbs.siris.web.controller.base;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;

import sbs.cross.config.Setup;
import sbs.cross.crypto.AesCustom;
import sbs.cross.log.Record;
import sbs.extranet.autenticacion.javabean.UsuarioSession;
import sbs.siris.domain.entity.UsuarioLogin;

public class HelperController {

	public static final String USER_NONE = "[[NONE]]";
	public static final String USER_NO_CAUGHT = "no-caught";
	public static final String TOKE_NONE = "0";
	public final static String URL_ERROR = "/sgc_web/start/error?msg=";
	public final static String TOKEN_NO_EXIST = "el token no existe";
	public final static String KEY_PUBLIC = Setup.getValueKeySys("KeyPublic");
	public final static String FORMAT_DATE = Setup.getValueKeySys("KeyFormatDate");
	public final static String KEY_FRASE = "OK";
	public final static RestTemplate REST_TEMPLATE = new RestTemplate();
	public final static String URL_COOPAC_USUARIO = Setup.getValueKey("urlBase.properties", "url_coopac_login");
	public final static Long TIMEOUT_TOKEN = Setup.getValueKeySys("KeyTimeoutToken", Long.class);

	public static String getCodUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UsuarioLogin)
				return ((UsuarioLogin) principal).getCodUsuario();
			else
				return principal.toString();
		}
		return USER_NONE;
	}
	
	public static String getUserNameApp() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null) {
			
			UsuarioSession usuarioSession = (UsuarioSession) authentication.getPrincipal();
			
			return usuarioSession.getC_t_nombre() + " " + usuarioSession.getC_t_apellidos(); 
		}
		
		return null;
	}
	
	public static String getIdCoopacEncoded() {

		Integer idCoopac = getIdCoopac();
		if(idCoopac == null)
			return null;
		
		String encriptado = AesCustom.encriptarAes(idCoopac.toString(), HelperController.KEY_PUBLIC);
		
		return codeForUrl(encriptado);
	}
	
	public static String codeForUrl(String strEncript)
    {
		strEncript = strEncript.replace(" ", "@[1]@");
		strEncript = strEncript.replace("+", "@[2]@");
		strEncript = strEncript.replace("=", "@[3]@");
		strEncript = strEncript.replace("/", "@[4]@");
		strEncript = strEncript.replace("&", "@[5]@");
		strEncript = strEncript.replace("\\", "@[6]@");
		strEncript = strEncript.replace("%", "@[7]@");
		strEncript = strEncript.replace("<", "@[8]@");
		strEncript = strEncript.replace(">", "@[8]@");
		strEncript = strEncript.replace("$", "@[10]@");
		strEncript = strEncript.replace("'", "@[11]@");
		strEncript = strEncript.replace("?", "@[12]@");
		strEncript = strEncript.replace("¿", "@[13]@");
		strEncript = strEncript.replace("#", "@[14]@");
		strEncript = strEncript.replace("|", "@[15]@");
		strEncript = strEncript.replace("!", "@[16]@");
		strEncript = strEncript.replace("\"\"", "@[17]@");
		strEncript = strEncript.replace("~", "@[18]@");

        return strEncript;
    }


	public static Integer getIdCoopac() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UsuarioLogin)
				return ((UsuarioLogin) principal).getIdCoopac();
		}
		return null;
	}
	
	public static String getIdEntVig() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UsuarioSession)
				return ((UsuarioSession) principal).getUsuario().getC_c_ent_vigilada();
		}
		
		return null;
	}
	
	public static String getTipoEntVig() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UsuarioSession)
				return ((UsuarioSession) principal).getUsuario().getC_c_tip_entidad();
		}
		
		return null;
	}

	public static Integer getIdSolicitud() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null) {
			Object principal = authentication.getPrincipal();
			if (principal instanceof UsuarioLogin)
				return ((UsuarioLogin) principal).getIdSolicitud();
		}
		return null;
	}

	public static String inserLog(Exception exception, HttpServletRequest request, String codUser) {
		return inserLog(exception, request, codUser, 2);
	}

	public static String inserLog(Exception exception, HttpServletRequest request, String codUser, int startStep) {
		LinkedHashMap<String, String> response = new LinkedHashMap<>();
		response.put("Mensaje", exception.getMessage());
		response.put("IP Remota", request.getRemoteAddr());
		response.put("Api", request.getMethod() + " " + request.getPathInfo());
		response.put("Url", request.getRequestURL().toString());
		response.put("Uri", request.getRequestURI());
		if (!request.getParameterMap().isEmpty())
			response.put("Params", request.getParameterMap().toString());
		if (request.getQueryString() != null)
			response.put("Query", request.getQueryString());

		String str = FromBufferToString(request);
		if (str != null)
			response.put("Body", str);

		response.put("Usuario", codUser);
		return Record.error(response, exception, startStep);
	}

	public static String FromBufferToString(HttpServletRequest request) {
		String result = null;
		try {
			if (request.getReader() != null) {
				String str = request.getReader().lines().collect(Collectors.joining());
				if (str != null && !str.isEmpty())
					result = str;
			}
		} catch (Exception e) {
			result = null;
		}
		return result;
	}

	public static String getStringReplaced(String cadena, Object... valores) {
		return MessageFormat.format(cadena, valores);
	}
}