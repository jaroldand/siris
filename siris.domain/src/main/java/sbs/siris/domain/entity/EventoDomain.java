package sbs.siris.domain.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Service;

import sbs.siris.cross.resources.ConstanteValor;
import sbs.siris.cross.resources.TipoValor;
import sbs.siris.data.EventoMapper;
import sbs.siris.domain.base.BaseDomain;



@Service
public class EventoDomain extends BaseDomain<Evento> {
	
	public EventoDomain(EventoMapper eventoMapper) {
		super.setMapper(eventoMapper);
	}
	
	public Evento insertEventoInicial(Evento evento, String codUser) {

		evento.setIdEvento( (int) mapper.getSequenceLong() );
		evento.setIdEstado(TipoValor.SIRIS_EVENTO_ID_ESTADO_BORRADOR);
		
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		evento.setCodAnio( String.valueOf(localDate.getYear()) );
		evento.setFechaEvento(date);
		
		evento.setIndActivo(ConstanteValor.IND_ACTIVO);
		
		insert(evento, codUser);
		
		return evento;
	}
	
	public Evento obtenerEventoById(Integer idEvento) {
		
		Evento evento = getEntityByKey(idEvento, null);
		return evento;
	}
}
