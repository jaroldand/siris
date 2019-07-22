package sbs.siris.data;

import java.util.Map;

import sbs.siris.domain.entity.Evento;
import sbs.siris.domain.entity.File;
import sbs.siris.domain.entity.Validacion;
import sbs.siris.domain.entity.base.BaseParam;
import sbs.siris.domain.entity.dto.BandejaDTO;

public interface EventoReporteMapper {
	
	void validarEventos(BaseParam<Map<String, Validacion>> param);
	
	void obtenerEventos(BaseParam<BandejaDTO> param);
	
	void obtenerEventosHist(BaseParam<BandejaDTO> param);
	
	void deleteFileCommentAdic(BaseParam<Map<String, Long>> param);
	
	void enviarEventoReporte(BaseParam<Evento> param);
	
	void obtenerConstanciaEnvio(BaseParam<File> param);
	
	void eliminarEventoReporte(BaseParam<Evento> param);
	
	void obtenerAniosHist(BaseParam<String> param);
}