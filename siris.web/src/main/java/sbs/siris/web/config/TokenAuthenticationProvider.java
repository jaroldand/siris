package sbs.siris.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import sbs.cross.log.Record;
import sbs.extranet.autenticacion.javabean.UsuarioSession;
import sbs.siris.web.controller.base.HelperController;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		try {
			String username = auth.getName();
			String password = auth.getCredentials().toString();
			if (username == null)
				throw new BadCredentialsException("El usuario esta incorrecto o no se ha sido ingresado");

			List<GrantedAuthority> grantedAuths = new ArrayList<>();

			if (!username.equals(HelperController.USER_NONE)) {
				
		        UsuarioSession usuarioSession = (UsuarioSession) auth.getPrincipal();
		        
		        if (usuarioSession == null)
					throw new DisabledException("El usuario no existe.");


				grantedAuths.add(new SimpleGrantedAuthority("ROLE_APP"));
				return new PreAuthenticatedAuthenticationToken(usuarioSession, password, grantedAuths);

			} else if (username.equals(HelperController.USER_NONE)) {
				grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADD"));
				return new PreAuthenticatedAuthenticationToken(username, password, grantedAuths);
			}
			Record.info("El usuario esta incorrecto.");
			throw new BadCredentialsException("El usuario esta incorrecto.");

		} catch (Exception e) {
			Record.error(e);
			throw new BadCredentialsException("El sistema de autenticación externa falló  => " + e.getMessage());
		}

	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

}

//
//
// HTTP Status 401 - Bad credentials - [Para mas informaci�n comun�quese con (511) 630-9000 - 3000 SBS]
// Error 403: Forbidden