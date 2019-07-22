package sbs.siris.service.config;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import sbs.cross.crypto.AesCustom;
import sbs.cross.log.Record;
import sbs.cross.util.DateTime;
import sbs.extranet.autenticacion.javabean.UsuarioSession;
import sbs.siris.service.controller.base.HelperController;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

	// @Autowired
	// private UsuarioDomain usuarioDomain;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
		String password = auth.getCredentials().toString();
		try {

			// Quiza necesite una validación previa en BD (ojo aqui puede ocurrir un problema de concurrencia)
			if (username == null || username.equals("unauthorized"))
				throw new BadCredentialsException("El usuario esta incorrecto o no se ha ingresado.");

			if (password == null)
				throw new BadCredentialsException("La constraseña es incorrecta o es inválida.");

			String[] val = AesCustom.desencriptarAes(username, HelperController.KEY_PUBLIC).split("\\|");

			// Validamos user y timeout
			String userCurrent = val[0];// se puede guardar log del usuario o validar en BD
			String time = val[1];

			Date dateCurrent = DateTime.now();
			Date dateToken = DateTime.dateFromString(time, HelperController.FORMAT_DATE);
			if (dateCurrent.getTime() > dateToken.getTime())
				throw new Exception("API CORE: el token ha caducado.");
			List<GrantedAuthority> grantedAuths = new ArrayList<>();
			if (!userCurrent.equals(HelperController.USER_NONE)) {
				
				UsuarioSession usuarioInvoke = new UsuarioSession();
				usuarioInvoke.setC_c_usuario(userCurrent);
				usuarioInvoke.setC_c_empresa(val[2]);

				grantedAuths.add(new SimpleGrantedAuthority("ROLE_APP"));
				return new PreAuthenticatedAuthenticationToken(usuarioInvoke, password, grantedAuths);

			} else if (userCurrent.equals(HelperController.USER_NONE)) {
				grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADD"));
				return new PreAuthenticatedAuthenticationToken(userCurrent, password, grantedAuths);
			}
			throw new BadCredentialsException("El usuario esta incorrecto.");

		} catch (

		Exception e) {
			Record.error(e);
			throw new BadCredentialsException("El sistema de autenticación externa falló. " + e.getMessage());
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