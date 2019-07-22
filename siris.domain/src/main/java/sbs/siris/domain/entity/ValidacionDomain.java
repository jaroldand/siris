package sbs.siris.domain.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbs.siris.data.ValidacionMapper;
import sbs.siris.domain.base.BaseDomain;
import sbs.siris.domain.entity.base.BaseParam;



@Service
public class ValidacionDomain extends BaseDomain<Validacion> {
	
	@Autowired
	private ValidacionMapper mapper;
	
	public ValidacionDomain(ValidacionMapper validacionMapper) {
		super.setMapper(validacionMapper);
	}
	
	public List<Validacion> listarValidacionByEvent(Integer idEvento) {
		
		BaseParam<Validacion> param = new BaseParam<Validacion>();
		param.setKey(idEvento);
		mapper.listarValidacionByEvent(param);
		
		return param.getList();
	}
}
