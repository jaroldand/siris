package sbs.siris.domain.entity;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sbs.siris.cross.resources.ConstanteValor;
import sbs.siris.cross.resources.TipoValor;
import sbs.siris.data.NotificacionMapper;
import sbs.siris.domain.base.BaseDomain;



@Service
public class NotificacionDomain extends BaseDomain<Notificacion> {
	
	@Autowired
	private NotificacionMapper mapper;
	
	public NotificacionDomain(NotificacionMapper notificacionMapper) {
		super.setMapper(notificacionMapper);
	}
	
	public void generarConstanciaEnvio(Integer idEvento, String user) {
		
		Notificacion notificacion = new Notificacion();
		notificacion.setIdNotificacion( getSequenceLong() );
		notificacion.setIdEvento(idEvento);
		notificacion.setEstNotificacion(TipoValor.SIRIS_NOTIFICACION_EST_NOTIFICACION_ENVIADO);
		notificacion.setTipNotificacion(TipoValor.SIRIS_NOTIFICACION_TIP_NOTIFICACION_CONSTANCIA_ENVIO);
		
		notificacion.setIndActivo(ConstanteValor.IND_ACTIVO);
		
		insert(notificacion, user);
		
	}
}
