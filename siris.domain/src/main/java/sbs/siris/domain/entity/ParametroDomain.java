package sbs.siris.domain.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbs.siris.data.ParametrosMapper;
import sbs.siris.domain.base.BaseDomain;
import sbs.siris.domain.entity.base.BaseParam;



@Service
public class ParametroDomain extends BaseDomain<Parametros> {
	
	@Autowired
	private ParametrosMapper parametrosMapper;

	public List<Parametros> listarParametrosByDesc(String desc) {
		BaseParam<Parametros> param = new BaseParam<>();
		param.setKey(desc);
		
		parametrosMapper.listarParametrosByDesc(param);
		
		return param.getList();
	}
}