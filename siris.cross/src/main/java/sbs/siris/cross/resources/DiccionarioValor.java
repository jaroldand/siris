package sbs.siris.cross.resources;

import sbs.cross.config.Setup;

public class DiccionarioValor {

	private static final String DICCIONARIO_PROPERTY = "diccionario.properties";

	public static final short SIRIS_EVENTO = Setup.getValueKey(DICCIONARIO_PROPERTY, "siris_evento", short.class);
	public static final short SIRIS_INFORME = Setup.getValueKey(DICCIONARIO_PROPERTY, "siris_informe", short.class);
	
}
