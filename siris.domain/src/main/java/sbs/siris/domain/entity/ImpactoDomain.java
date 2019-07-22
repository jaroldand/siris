package sbs.siris.domain.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import sbs.siris.cross.resources.TipoValor;
import sbs.siris.data.ImpactoMapper;
import sbs.siris.domain.base.BaseDomain;



@Service
public class ImpactoDomain extends BaseDomain<Impacto> {
	
	@Autowired
	private ImpactoMapper mapper;
	
	public ImpactoDomain(ImpactoMapper impactoMapper) {
		super.setMapper(impactoMapper);
	}
	
	public List<Impacto> buscarImpactos(Integer idEvento) {
		
		Impacto impacto = new Impacto();
		impacto.setIdEvento(idEvento);
		
		List<Impacto> impactos = buscar(impacto, null);
		
		if(CollectionUtils.isEmpty(impactos)) {
			impactos.add(new Impacto(TipoValor.SIRIS_IMPACTO_TIPO_IMPACTO_FINANCIERO));
			impactos.add(new Impacto(TipoValor.SIRIS_IMPACTO_TIPO_IMPACTO_REPUTACIONAL));
			impactos.add(new Impacto(TipoValor.SIRIS_IMPACTO_TIPO_IMPACTO_CLIENTES_COLABORADORES));
			impactos.add(new Impacto(TipoValor.SIRIS_IMPACTO_TIPO_IMPACTO_REGULATORIO));
			impactos.add(new Impacto(TipoValor.SIRIS_IMPACTO_TIPO_IMPACTO_OBJETIVOS_ESTRATEGICOS));
		}
		
		return impactos;
	}
}
