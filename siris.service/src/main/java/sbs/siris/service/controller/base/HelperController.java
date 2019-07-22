package sbs.siris.service.controller.base;

import java.util.LinkedHashMap;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import sbs.cross.config.Setup;
import sbs.cross.log.Record;
import sbs.extranet.autenticacion.javabean.UsuarioSession;

public class HelperController {

	public static final String USER_NONE = "[[NONE]]";
	public static final String USER_NO_CAUGHT = "no-caught";
	public static final String TOKE_NONE = "0";
	public final static String KEY_PUBLIC = Setup.getValueKeySys("KeyPublic");
	public final static String FORMAT_DATE = Setup.getValueKeySys("KeyFormatDate");
	public final static Long TIMEOUT_TOKEN = Setup.getValueKeySys("KeyTimeoutToken", Long.class);

	public static String getCodUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null) {
			
			UsuarioSession usuarioSession = (UsuarioSession) authentication.getPrincipal();
			
			return usuarioSession.getC_c_usuario();
		}
		return USER_NONE;
	}

	public static String getTokeCurrent() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null && authentication.getPrincipal() != null)
			return authentication.getCredentials().toString();
		return TOKE_NONE;
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
}