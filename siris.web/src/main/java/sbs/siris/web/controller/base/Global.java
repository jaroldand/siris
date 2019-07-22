package sbs.siris.web.controller.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import sbs.cross.config.Setup;

@Component
public class Global implements ApplicationListener<ContextRefreshedEvent> {

	private static boolean LOAD = false;

	@Autowired
	ApplicationContext appContext;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().equals(appContext)) {
			if (!LOAD) {
				//Setup.replaceProperties("urlBase.properties", "uriCreacionUsuario.properties");
				Setup.replaceProperties("urlBase.properties", "uriRegistroSiris.properties");
				LOAD = true;
			}
		}
	}
}
