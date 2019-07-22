package sbs.siris.domain.base;

import sbs.cross.config.Setup;

public class DomainProperties {

	// properties
	public static final String CONSTANT_PROPERTY_FILE = "constant.properties";
	// of:constant.properties
	public static final String NO_EXISTE = Setup.getValueKey(CONSTANT_PROPERTY_FILE, "entidad.no_existe");
	public static final String NO_DATOS = Setup.getValueKey(CONSTANT_PROPERTY_FILE, "entidad.no_datos");
	public static final String EMPTY_MODEL = Setup.getValueKey(CONSTANT_PROPERTY_FILE, "entidad.empty");
	public static final String INVALID_USER = Setup.getValueKey(CONSTANT_PROPERTY_FILE, "user.invalid");
	public static final String INVALID_TOKEN = Setup.getValueKey(CONSTANT_PROPERTY_FILE, "token.invalid");
	
	public static final String FILTRO_NO_EXISTE = Setup.getValueKey(CONSTANT_PROPERTY_FILE, "entidad.filtro.no_existe");
	public static final String ERROR_EMAIL = Setup.getValueKey(CONSTANT_PROPERTY_FILE, "entidad.email.no_validated");
	public static final String DDFINAL =  Setup.getValueKey(CONSTANT_PROPERTY_FILE, "creacion_cuenta.coopacDDfinal");
	public static final String SGC_COOPAC_REPRESENTANTE_NON_TIPO_CARGO =  Setup.getValueKey(CONSTANT_PROPERTY_FILE, "sgc_coopac_representante.non_tipo_cargo");
}
