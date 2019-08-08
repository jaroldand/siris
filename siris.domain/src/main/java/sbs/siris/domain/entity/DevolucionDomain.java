package sbs.siris.domain.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbs.siris.data.DevolucionMapper;
import sbs.siris.domain.base.BaseDomain;



@Service
public class DevolucionDomain extends BaseDomain<Devolucion> {
	
	@Autowired
	private DevolucionMapper mapper;
	
	public DevolucionDomain(DevolucionMapper devolucionMapper) {
		super.setMapper(devolucionMapper);
	}
	
}
