package sbs.siris.domain.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import sbs.siris.data.InformeMapper;
import sbs.siris.domain.base.BaseDomain;
import sbs.siris.domain.entity.base.BaseParam;



@Service
public class InformeDomain extends BaseDomain<Informe> {
	
	@Autowired
	private InformeMapper mapper;
	
	public InformeDomain(InformeMapper informeMapper) {
		super.setMapper(informeMapper);
	}
	
	
	public Informe buscarInforme(Integer idEvento) {
		
		Informe param = new Informe();
		param.setIdEvento(idEvento);
		
		List<Informe> informes = buscar(param, null);
		
		return CollectionUtils.isEmpty(informes) ? new Informe() : informes.get(0);
	}
	
	public void updateFileAdic(Integer idEvento, Long idFile) {
		
		BaseParam<Informe> param = new BaseParam<Informe>();
		Informe entity = new Informe();
		entity.setIdEvento(idEvento);
		entity.setFileComentAdic(idFile);
		
		param.setEntity(entity);
		
		mapper.updateFileAdic(param);
	}
}
