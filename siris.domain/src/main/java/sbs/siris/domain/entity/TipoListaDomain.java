package sbs.siris.domain.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbs.cross.util.exception.BusinessAsyncException;
import sbs.cross.util.exception.LevelType;
import sbs.cross.util.exception.StatusCode;
import sbs.siris.cross.resources.GrupoValor;
import sbs.siris.data.TipoListaMapper;
import sbs.siris.domain.base.BaseDomain;
import sbs.siris.domain.base.DomainProperties;
import sbs.siris.domain.entity.base.BaseParam;

@Service
public class TipoListaDomain extends BaseDomain<TipoLista> {

	@Autowired
	private TipoListaMapper mapper;
	/*
	public TipoListaDomain(TipoListaMapper tipoListaMapper) {
		mapper = tipoListaMapper;
		super.setMapper(tipoListaMapper);
	}*/

	public List<ClaveValor> listaPorGrupo(String codGrupo) {
		BaseParam<ClaveValor> param = new BaseParam<>();
		param.setKey(codGrupo);
		mapper.selectListClaveValor(param);
		if (param.getList() == null || param.getList().size() <= 0)
			throw new BusinessAsyncException(DomainProperties.NO_EXISTE, LevelType.WARNING, StatusCode.NO_FOUND);
		return param.getList();
	}
	
	public List<TipoLista> listaPorKey(String likeKey) {
		BaseParam<TipoLista> param = new BaseParam<>();
		param.setKey(likeKey);
		mapper.selectByPrimaryKey(param);
		if (param.getList() == null || param.getList().size() <= 0)
			throw new BusinessAsyncException(DomainProperties.NO_EXISTE, LevelType.WARNING, StatusCode.NO_FOUND);
		
		return param.getList();
	}

	public List<ClaveValor> tiposValidacion() {
		return listaPorGrupo(GrupoValor.SIRIS_VALIDACION_TIPO_VALIDACION);
	}
	
	public List<ClaveValor> tiposEventos() {
		return listaPorGrupo(GrupoValor.SIRIS_INFORME_TIPO_EVENTO);
	}
	
	public List<ClaveValor> tiposCanales004() {
		return listaPorGrupo(GrupoValor.SIRIS_CANALES_TIP_CANAL_DETALLE_CANALES_A);
	}
	
	public List<ClaveValor> tiposCanales005() {
		return listaPorGrupo(GrupoValor.SIRIS_CANALES_TIP_CANAL_DETALLE_CANALES_B);
	}
	
	public List<ClaveValor> tiposCanales006() {
		return listaPorGrupo(GrupoValor.SIRIS_CANALES_TIP_CANAL_DETALLE_CANALES_C);
	}
	
	public List<ClaveValor> obtenerImpactosInterrupcion() {
		return listaPorGrupo(GrupoValor.SIRIS_IMPACTO_IMPACTO_DETAIL);
	}
	
	public List<ClaveValor> obtenerPreguntaEvaluacion1() {
		return listaPorGrupo(GrupoValor.SIRIS_EVALUACION_RESP_EVALUACION_QUESTION1);
	}
	
	public List<ClaveValor> obtenerPreguntaEvaluacionOtros() {
		return listaPorGrupo(GrupoValor.SIRIS_EVALUACION_RESP_EVALUACION_QUESTION_OTHERS);
	}

	public List<TipoLista> obtenerUbigeoDepartamentos() {
		return listaPorKey("%0000");
	}
}