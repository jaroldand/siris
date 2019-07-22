package sbs.siris.cross.resources;

import sbs.cross.config.Setup;

public class MensajesDB {

	private static final String PARAMETRO_PROPERTY = "message_db.properties";

	public static final String ERROR_20300 = Setup.getValueKey(PARAMETRO_PROPERTY, "error_20300");
}
