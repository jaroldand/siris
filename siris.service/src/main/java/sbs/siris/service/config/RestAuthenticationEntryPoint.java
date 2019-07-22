package sbs.siris.service.config;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import sbs.cross.log.Record;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public final class RestAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

	@Override
	public void commence(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException authException) throws IOException, ServletException {
		// response.addHeader("WWW-Authenticate", "Basic realm=\"" + getRealmName() + "\"");
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setHeader("Content-Type", "text/html; charset=UTF-8");
		final PrintWriter writer = response.getWriter();
		Record.error(getRealmName(), authException);
		// response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		writer.println("HTTP Status " + HttpServletResponse.SC_UNAUTHORIZED + " - " + authException.getMessage() + " - [Para mas información comuníquese con (511) 630-9000 - 3000 SBS]");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		setRealmName("Denied for SBS");
		super.afterPropertiesSet();
	}

	// @Override
	// public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
	// response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	// }
}
