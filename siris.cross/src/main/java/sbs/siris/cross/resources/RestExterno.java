package sbs.siris.cross.resources;

import sbs.cross.config.Setup;

public class RestExterno {
	private static final String REST_EXTERO = "restexterno.properties";
	
	public static final String SERVICIO_RENIEC = Setup.getValueKey(REST_EXTERO, "url_service_reniec");
	public static final String USUARIO_RENIEC = Setup.getValueKey(REST_EXTERO, "reniec.usuario");
	
	public static final String SERVICIO_CORREO = Setup.getValueKey(REST_EXTERO, "url_service_correo");
	public static final String SERVICIO_CORREO_NOMBRE = Setup.getValueKey(REST_EXTERO, "url_service_correo.nombre");
	public static final String SERVICIO_CORREO_EMAIL = Setup.getValueKey(REST_EXTERO, "url_service_correo.correofrom");
	public static final String SERVICIO_CORREO_ASUNTO_REGISTRO_COOPAC = Setup.getValueKey(REST_EXTERO, "url_service_correo.asunto_registro_coopac");
	public static final String TOKENDURACION =  Setup.getValueKey(REST_EXTERO, "creacion_cuenta.tokenduracion");
	public static final String URL_LOGIN =  Setup.getValueKey(REST_EXTERO, "url_login");
	
	public static final String SERVICIO_SISGED = Setup.getValueKey(REST_EXTERO, "url_service_sisged");
}
