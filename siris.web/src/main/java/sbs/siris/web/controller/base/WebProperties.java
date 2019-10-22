package sbs.siris.web.controller.base;

import sbs.cross.config.Setup;

public class WebProperties {
	public static final String MENSAJE_PROPERTIES_FILE = "mensaje.properties";
	public static final String URL_PROPERTIES_FILE = "urlBase.properties";
	public static final String PUB_DATOS_COOPAC_SUNAT_ERROR =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_coopac.sunatError");
	public static final String PUB_DATOS_COOPAC_VALIDACION_EXISTE =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_coopac.validacionExite");
	//public static final String PUB_DATOS_COOPAC_MESANJE_RUC =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_coopac.mesanjeRuc");

	public static final String PUB_DATOS_REPRESENTANTE_VALIDA_TIPO_DOCUMENTO =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.validaTipoDocumento");
	public static final String PUB_DATOS_REPRESENTANTE_VALIDA_DOC_INGRESO =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.validaDocIngreso");
	public static final String PUB_DATOS_REPRESENTANTE_VALIDA_DNI_LONG =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.validaDNILong");
	public static final String PUB_DATOS_REPRESENTANTE_VALIDA_CE_LONG =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.validaCELong");
	public static final String PUB_DATOS_REPRESENTANTE_VALIDA_PASAPORTE_LONG = Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.validaPasaporteLong");
	public static final String PUB_DATOS_REPRESENTANTE_VALIDA_DNI_FECHAEM =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.validaDNIFechaEm");
	public static final String PUB_DATOS_REPRESENTANTE_VALIDA_DNI_NOMMADRE =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.validaDNINomMadre");
	public static final String PUB_DATOS_REPRESENTANTE_VALIDA_RESULTADO =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.validaResultado");
	public static final String PUB_DATOS_REPRESENTANTE_RENIEC_ERROR =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.reniecError");
	public static final String PUB_DATOS_REPRESENTANTE_VALIDA_REPLEGAL =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.validaRepLegal");
	public static final String PUB_DATOS_REPRESENTANTE_TERMINOS_CONDICIONES =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.terminosCondiciones");
	public static final String PUB_DATOS_REPRESENTANTE_COINCIDE_CORREOCONF =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.coincideCorreoConf");
	public static final String PUB_DATOS_REPRESENTANTE_COINCIDE_CORREOCONFA =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.coincideCorreoConfA");
	public static final String PUB_CONFIRMACION_USUARIO_SUCCESS =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.confirmacion_usuario.success");
	public static final String PUB_CONFIRMACION_USUARIO_ERROR =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.confirmacion_usuario.error");
	public static final String PUB_CONFIRMACION_USUARIO_ERROR_CORREO =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.confirmacion_usuario.error_correo");
	public static final String PUB_CONFIRMACION_USUARIO_ERROR_ARCHIVO =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.confirmacion_usuario.error_archivo");
	public static final String PUB_CONFIRMACION_USUARIO_ERROR_USURIO_EXT =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.confirmacion_usuario.error_usurio_ext");
	public static final String PUB_CONFIRMACION_USUARIO_VALIDACION_EXT =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.confirmacion_usuario.validacion_ext");
	public static final String PUB_DATOS_REPRESENTANTE_FOMATOCORREOERR =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.fomatoCorreoErr");
	public static final String PUB_DATOS_REPRESENTANTE_FORMATOCORREOAERR =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.formatoCorreoAErr");
	public static final String PUB_DATOS_REPRESENTANTE_CORREODIFF =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.correoDiff");
	public static final String PUB_DATOS_REPRESENTANTE_BUSQUEDADNIERR =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.busquedaDNIErr");
	public static final String PUB_DATOS_REPRESENTANTE_TELEFDIFF =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.telefDiff");
	public static final String PUB_DATOS_REPRESENTANTE_VALIDATELFOCELTAMA =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.validaTelfOCelTama");
	public static final String PUB_DATOS_REPRESENTANTE_VALIDATELFOFIJTAMA =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.validaTelfOFijTama");
	public static final String PUB_DATOS_REPRESENTANTE_VALIDATELFACELTAMA =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.validaTelfACelTama");
	public static final String PUB_DATOS_REPRESENTANTE_VALIDATELFAFIJTAMA =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.datos_representante.validaTelfAFijTama");
	public static final String PUB_ERROR_SGTE =  Setup.getValueKey(MENSAJE_PROPERTIES_FILE, "pub.error_sgte");
	
	public static final String URL_LOGIN_BASE = Setup.getValueKey(URL_PROPERTIES_FILE, "url_login_menu");
}
