package sbs.siris.domain.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbs.siris.data.PlanAccionMapper;
import sbs.siris.domain.base.BaseDomain;



@Service
public class PlanAccionDomain extends BaseDomain<PlanAccion> {
	
	@Autowired
	private PlanAccionMapper mapper;
	
	public PlanAccionDomain(PlanAccionMapper planAccionMapper) {
		super.setMapper(planAccionMapper);
	}
}
