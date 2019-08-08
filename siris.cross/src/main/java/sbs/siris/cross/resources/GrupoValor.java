package sbs.siris.cross.resources;

import sbs.cross.config.Setup;

public class GrupoValor {

	private static final String GRUPO_PROPERTY = "grupos.properties";

	public static final String SIN_DESTINO = Setup.getValueKey(GRUPO_PROPERTY, "sin_destino");
	public static final String SIRIS_VALIDACION_TIPO_VALIDACION = Setup.getValueKey(GRUPO_PROPERTY, "siris_validacion.tipo_validacion");
	public static final String SIRIS_INFORME_TIPO_EVENTO = Setup.getValueKey(GRUPO_PROPERTY, "siris_informe.tipo_evento");
	
	public static final String SIRIS_CANALES_TIP_CANAL_DETALLE_CANALES_A = Setup.getValueKey(GRUPO_PROPERTY, "siris_canales.tip_canal_detalle.canales_a");
	public static final String SIRIS_CANALES_TIP_CANAL_DETALLE_CANALES_B = Setup.getValueKey(GRUPO_PROPERTY, "siris_canales.tip_canal_detalle.canales_b");
	public static final String SIRIS_CANALES_TIP_CANAL_DETALLE_CANALES_C = Setup.getValueKey(GRUPO_PROPERTY, "siris_canales.tip_canal_detalle.canales_c");
	
	public static final String SIRIS_IMPACTO_IMPACTO_DETAIL = Setup.getValueKey(GRUPO_PROPERTY, "siris_impacto.impacto_detail");
	
	public static final String SIRIS_EVALUACION_RESP_EVALUACION_QUESTION1 = Setup.getValueKey(GRUPO_PROPERTY, "siris_evaluacion.resp_evaluacion.question1");
	public static final String SIRIS_EVALUACION_RESP_EVALUACION_QUESTION_OTHERS = Setup.getValueKey(GRUPO_PROPERTY, "siris_evaluacion.resp_evaluacion.question_others");
}
