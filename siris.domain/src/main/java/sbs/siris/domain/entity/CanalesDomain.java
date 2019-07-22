package sbs.siris.domain.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbs.siris.data.CanalesMapper;
import sbs.siris.domain.base.BaseDomain;



@Service
public class CanalesDomain extends BaseDomain<Canales> {
	
	@Autowired
	private CanalesMapper mapper;
	
	public CanalesDomain(CanalesMapper canalesMapper) {
		super.setMapper(canalesMapper);
	}
	
}
