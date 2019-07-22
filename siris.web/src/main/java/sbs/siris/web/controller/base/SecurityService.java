package sbs.siris.web.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authenticationManager;

	public void autologin(String username, Object usuario, HttpServletRequest req) {
		if (username == null)
			throw new BadCredentialsException("Por favor ingrese un usuario válido.");

		String password = username;
		UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(usuario, password);
		Authentication auth = authenticationManager.authenticate(authReq);
		if (auth.isAuthenticated()) {
			//SecurityContext sc = SecurityContextHolder.getContext();
			SecurityContext sc = SecurityContextHolder.createEmptyContext();
			sc.setAuthentication(auth);
			HttpSession session = req.getSession(true);
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, sc);
		}
	}

}