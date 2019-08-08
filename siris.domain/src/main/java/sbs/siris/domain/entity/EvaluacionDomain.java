package sbs.siris.domain.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbs.siris.data.EvaluacionMapper;
import sbs.siris.domain.base.BaseDomain;



@Service
public class EvaluacionDomain extends BaseDomain<Evaluacion> {
	
	@Autowired
	private EvaluacionMapper mapper;
	
	public EvaluacionDomain(EvaluacionMapper evaluacionMapper) {
		super.setMapper(evaluacionMapper);
	}
	
}
