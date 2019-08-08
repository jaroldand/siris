package sbs.siris.domain.core;

import java.lang.reflect.Method;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import sbs.cross.log.Record;
import sbs.cross.util.DateTime;
import sbs.cross.util.exception.BusinessAsyncException;
import sbs.cross.util.exception.LevelType;
import sbs.cross.util.exception.StatusCode;
import sbs.siris.cross.resources.ConstanteValor;
import sbs.siris.data.EventoReporteMapper;
import sbs.siris.domain.base.DomainProperties;
import sbs.siris.domain.entity.Canales;
import sbs.siris.domain.entity.CanalesDomain;
import sbs.siris.domain.entity.ClaveValor;
import sbs.siris.domain.entity.Correo;
import sbs.siris.domain.entity.CorreoDomain;
import sbs.siris.domain.entity.Devolucion;
import sbs.siris.domain.entity.DevolucionDomain;
import sbs.siris.domain.entity.Evaluacion;
import sbs.siris.domain.entity.EvaluacionDomain;
import sbs.siris.domain.entity.Evento;
import sbs.siris.domain.entity.EventoDomain;
import sbs.siris.domain.entity.File;
import sbs.siris.domain.entity.FileDomain;
import sbs.siris.domain.entity.Impacto;
import sbs.siris.domain.entity.ImpactoDomain;
import sbs.siris.domain.entity.Informe;
import sbs.siris.domain.entity.InformeDomain;
import sbs.siris.domain.entity.NotificacionDomain;
import sbs.siris.domain.entity.ParametroDomain;
import sbs.siris.domain.entity.Parametros;
import sbs.siris.domain.entity.PlanAccion;
import sbs.siris.domain.entity.PlanAccionDomain;
import sbs.siris.domain.entity.TipoListaDomain;
import sbs.siris.domain.entity.Validacion;
import sbs.siris.domain.entity.ValidacionDomain;
import sbs.siris.domain.entity.base.BaseParam;
import sbs.siris.domain.entity.dto.BandejaDTO;
import sbs.siris.domain.entity.dto.DatosValidacionDTO;
import sbs.siris.domain.entity.dto.EventoReporteDTO;
import sbs.siris.domain.entity.dto.ResultDTO;

@Service
public class EventoReporteDomain {
	
	@Autowired
	private EventoReporteMapper mapper;
	
	@Autowired
	private EventoDomain eventoDomain;
	
	@Autowired
	private ValidacionDomain validacionDomain;
	
	@Autowired
	private TipoListaDomain tipoListaDomain;
	
	@Autowired
	private ParametroDomain parametroDomain;
	
	@Autowired
	private InformeDomain informeDomain;
	
	@Autowired
	private CanalesDomain canalesDomain;
	
	@Autowired
	private ImpactoDomain impactoDomain;
	
	@Autowired
	private PlanAccionDomain planAccionDomain;
	
	@Autowired
	private FileDomain fileDomain;
	
	@Autowired
	private FileGeneratorDomain fileGeneratorDomain;
	
	@Autowired
	private NotificacionDomain notificacionDomain;
	
	@Autowired
	private CorreoDomain correoDomain;
	
	@Autowired
	private EvaluacionDomain evaluacionDomain;
	
	@Autowired
	private DevolucionDomain devolucionDomain;
	
	public List<BandejaDTO> obtenerEventosBandeja(String idEntVig) {
		
		BaseParam<BandejaDTO> param = new BaseParam<BandejaDTO>();
		param.setKey(idEntVig);
		mapper.obtenerEventos(param);
		
		return param.getList();
	}
	
	public List<BandejaDTO> obtenerEventosBandejaHistorica(String idEntVig, String anio) {
		
		Map<String, Object> keyMap = new HashMap<String, Object>();
		keyMap.put("Anio",  ConstanteValor.VALOR_TODOS.toUpperCase().equals(anio.toUpperCase()) ? "" : anio);
		
		BaseParam<BandejaDTO> param = new BaseParam<BandejaDTO>();
		param.setKey(idEntVig);
		param.setKeyMap(keyMap);
		mapper.obtenerEventosHist(param);
		
		return param.getList();
	}
	
	public EventoReporteDTO obtenerReporteEvento(Integer idEvento) {
		
		Informe informe = informeDomain.buscarInforme(idEvento);
		File archAdicional = fileDomain.buscarFileInformeComent(informe.getIdInforme());
		
		Correo correoInforme = correoDomain.buscarCorreoInforme(informe.getIdInforme());
		
		Canales canal = new Canales();
		canal.setIdEvento(idEvento);
		
		List<Canales> canales = canalesDomain.buscar(canal, null);
		
		List<Impacto> impactos = impactoDomain.buscarImpactos(idEvento);
		
		
		PlanAccion param = new PlanAccion();
		param.setIdEvento(idEvento);
		
		List<PlanAccion> planAccion = planAccionDomain.buscar(param, null);
		
		EventoReporteDTO resultado = new EventoReporteDTO();
		informe.setFileComentAdic(archAdicional.getIdFile());
		informe.setNameFileComentAdic(archAdicional.getDesDocumento()+"."+archAdicional.getDesExtension());
		resultado.setInforme(informe);
		
		resultado.setCorreoInforme(correoInforme);
		
		resultado.setCanales(canales);
		resultado.setCanalesActivos(canales);
		
		resultado.setPlanAccion(planAccion);
		resultado.setPlanAccionActivos(planAccion);
		
		resultado.setImpacto(impactos);
		
		return resultado;
	}
	
	public Boolean deleteReporte(Integer idEvento, String user) {
		
		Evento evento = new Evento();
		evento.setIdEvento(idEvento);
		evento.setAuditUserMod(user);
		
		BaseParam<Evento> param = new BaseParam<Evento>();
		param.setEntity(evento);
		mapper.eliminarEventoReporte(param);
		
		return true;
	}
	
	public Map<String, Evaluacion> loadEvaluacionEvento(Integer idEvento) {
	
		List<Evaluacion> evaluaciones = evaluacionDomain.getListEntity(idEvento, null, false);

		Map<String, Evaluacion> evaluacion = new HashMap<String, Evaluacion>();
		evaluaciones.forEach((p)-> {
			
			evaluacion.put("eval"+p.getTipoEvaluacion(), p);
		});
		
		return evaluacion;
	}
	
	public Devolucion loadDevolucionEvento(Integer idEvento) {
		
		List<Devolucion> devoluciones = devolucionDomain.getListEntity(idEvento, null, false);
		
		if(CollectionUtils.isEmpty(devoluciones)) return new Devolucion();
		
		return devoluciones.get(0);
	}
	
	public Map<String, Validacion> loadValidacionEvento(Integer idEvento) {
		
		List<Validacion> listaValidacion = validacionDomain.listarValidacionByEvent(idEvento);
		
		List<ClaveValor> grupoValidacion = tipoListaDomain.tiposValidacion();
		
		Map<String, Validacion> validaciones = new HashMap<String, Validacion>();
			
		grupoValidacion.stream().forEach((p)-> {
			if(CollectionUtils.isEmpty(listaValidacion)) {
				validaciones.put(p.getValor(), new Validacion(idEvento, p.getClave(), "0" ));
			} else {
				
				listaValidacion.stream().filter(   s -> p.getClave().equals(s.getTipoValidacion())  ).forEach((entry) -> {
					validaciones.put(p.getValor(), entry);
				});
			}
		});
		
		return validaciones;
	}
	
	public ResultDTO verificarEvento(Map<String, Validacion> motivos) throws SQLException {
		
		Map<String, Object> mapaSend = new HashMap<String, Object>(motivos);
		
		BaseParam<Map<String, Validacion>> param = new BaseParam<Map<String, Validacion>>();
		param.setKeyMap(mapaSend);
		mapper.validarEventos(param);
		
		String mensaje_screen = "";
		if((Integer)param.getKey() == 0) {//invalido
			Blob mensaje_valid = (Blob)param.getResult();
			mensaje_screen = new String(mensaje_valid.getBytes(1l, (int) mensaje_valid.length()));
		}
		
		ResultDTO result = new ResultDTO();
		result.setCodigo((Integer)param.getKey());
		result.setMessage(mensaje_screen);
		
		return result;
	}
	
	public List<ClaveValor> obtenerCanalesPorTipoEnt(String idTipoEnt) {
		
		List<Parametros> listaEntidades = parametroDomain.listarParametrosByDesc(idTipoEnt);
		
		List<ClaveValor> result = new ArrayList<ClaveValor>(); 
		
		listaEntidades.forEach((entry) -> {
			try {
				Method canalesByTipoEnt = TipoListaDomain.class.getDeclaredMethod("tiposCanales" + entry.getValor1());
				result.addAll( (List<ClaveValor>) canalesByTipoEnt.invoke(tipoListaDomain) );
			} catch (Exception e) {
				throw new BusinessAsyncException(DomainProperties.NO_EXISTE, LevelType.WARNING, StatusCode.NO_FOUND);
			}
		});
		
		return result;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public DatosValidacionDTO saveEvento(DatosValidacionDTO parametros, String user) {
		
		if( ObjectUtils.isEmpty(parametros.getIdEvento() ) || parametros.getIdEvento() < 0 ) {
			// inserta evento
			Evento evento = new Evento();
			evento.setIdEntidadVig(parametros.getIdEntidadVig());
			eventoDomain.insertEventoInicial(evento, user);
			
			parametros.setIdEvento(evento.getIdEvento());
			
			// inserta informe
			Informe informe = new Informe();
			informe.setIdEvento(parametros.getIdEvento());
			informe.setIdInforme((int) informeDomain.getSequenceLong() );
			informe.setIndActivo(ConstanteValor.IND_ACTIVO);
			
			informeDomain.insert(informe, user);
		}
		
		parametros.getValidaciones().forEach((k,v)->{
			if( ObjectUtils.isEmpty(v.getIdValidacion()) ) {
				v.setIdValidacion((int) validacionDomain.getSequenceLong());
				v.setIdEvento(parametros.getIdEvento());
				
				validacionDomain.insert(v, user);//nuevo registro
			} else {
				
				validacionDomain.updateByKey(v.getIdValidacion(), v, user);
			}//update
		});
		
		return parametros;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public EventoReporteDTO enviarReporte(EventoReporteDTO parametros, String user) throws Exception {
		
		//registro de data
		Informe informe = parametros.getInforme();
		informe.setFecEnvio(new Date());
		saveReporte(parametros, user);
		
		//genera el pdf
		byte[] constanciaPDF = fileGeneratorDomain.generarPDFConstanciaEnvio(parametros);
		
		//guardar el file en bd
		fileDomain.registrarConstanciaPDFFile(user, constanciaPDF, parametros.getIdEvento(), parametros.getInforme().getFecIniInterrupcion());
		
		//actualizar el estado del evento
		BaseParam<Evento> param = new BaseParam<Evento>();
		Evento entity = new Evento();
		entity.setIdEvento(parametros.getIdEvento());
		entity.setAuditUserMod(user);
		param.setEntity(entity);
		
		mapper.enviarEventoReporte(param);
		
		//registrar bitacora de la constancia
		notificacionDomain.generarConstanciaEnvio(parametros.getIdEvento(), user);
		
		return parametros;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public EventoReporteDTO saveReporte(EventoReporteDTO parametros, String user) throws Exception {
		
		/* Inicio save informe */
		Informe informe = parametros.getInforme();
		
		if( !StringUtils.isEmpty(informe.getFecIniInterrupcionStr()) ) {
			informe.setFecIniInterrupcion(  DateTime.dateFromString(informe.getFecIniInterrupcionStr(), "dd/MM/yyyy") );
		}
		
		if( !StringUtils.isEmpty(informe.getFecFinInterrupcionStr()) ) {
			informe.setFecFinInterrupcion(  DateTime.dateFromString(informe.getFecFinInterrupcionStr(), "dd/MM/yyyy") );
		}
		
		if( !StringUtils.isEmpty(informe.getFecEnvioRepInicialStr()) ) {
			informe.setFecEnvioRepInicial(  DateTime.dateFromString(informe.getFecEnvioRepInicialStr(), "dd/MM/yyyy") );
		}
		
		if(  ObjectUtils.isEmpty(informe.getIdInforme())  ) {
			informe.setIdEvento(parametros.getIdEvento());
			informe.setIdInforme((int) informeDomain.getSequenceLong() );
			informe.setIndActivo(ConstanteValor.IND_ACTIVO);
			
			informeDomain.insert(informe, user);
		} else {
			
			informeDomain.updateByKey(informe.getIdInforme(), informe, user);
		}
		
		/* Fin save informe */
		
		/* Inicio save correo */
		Correo correoInforme = parametros.getCorreoInforme();
		correoDomain.grabarCorreoInforme(correoInforme, informe.getIdInforme(), user);
		
		/* Fin save correo */
		
		/* Inicio save canales */
		List<Canales> canales = parametros.getCanales();
		
		canales.forEach((entry) -> {
			
			if(  ObjectUtils.isEmpty(entry.getIdCanales())  ) {//insert
				entry.setIdEvento(parametros.getIdEvento());
				entry.setIdCanales( (int) canalesDomain.getSequenceLong() );
				entry.setIndActivo(ConstanteValor.IND_ACTIVO);
				
				canalesDomain.insert(entry, user);
				
			} else {//update
				entry.setIdEvento(parametros.getIdEvento());
				entry.setIndActivo(ConstanteValor.IND_ACTIVO);
				
				canalesDomain.updateByKey(entry.getIdCanales(), entry, user);
			}
			
		});
		
		parametros.getCanalesActivos().forEach((entry) -> {
			
			Canales canalActivo = canales.stream().filter(item -> item.getIdCanales().equals( entry.getIdCanales() ) ).findAny().orElse(null);
			
			if(  ObjectUtils.isEmpty(canalActivo) ) {//si no se encuentra en la lista se inactiva
				entry.setIdEvento(parametros.getIdEvento());
				entry.setIndActivo(ConstanteValor.IND_INACTIVO);
				canalesDomain.updateByKey(entry.getIdCanales(), entry, user);
			}
			
		});
		/* Fin save canales */
		
		
		/* Inicio save impacto */
		List<Impacto> impactos = parametros.getImpacto();
		
		impactos.forEach((entry) -> {
			if( ObjectUtils.isEmpty(entry.getIdImpacto()) ) {//insert
				entry.setIdEvento(parametros.getIdEvento());
				entry.setIdImpacto((int) impactoDomain.getSequenceLong() );
				entry.setIndActivo(ConstanteValor.IND_ACTIVO);
				
				impactoDomain.insert(entry, user);
				
			} else {//update
				entry.setIdEvento(parametros.getIdEvento());
				
				impactoDomain.updateByKey(entry.getIdImpacto(), entry, user);
			}
		});
		
		/* Fin save impacto */
		
		
		/* Inicio save planAccion */
		List<PlanAccion> planAccion = parametros.getPlanAccion();
		planAccion.forEach((entry) -> {
			if( ObjectUtils.isEmpty(entry.getIdPlanAccion()) ) {//insert
				entry.setIdEvento(parametros.getIdEvento());
				entry.setIdPlanAccion((int) impactoDomain.getSequenceLong());
				entry.setIndActivo(ConstanteValor.IND_ACTIVO);
				
				if( !StringUtils.isEmpty(entry.getFecImplementacionStr()) ) {
					try {
						entry.setFecImplementacion(  DateTime.dateFromString(entry.getFecImplementacionStr(), "dd/MM/yyyy") );
					} catch (Exception e) {
						Record.error(user, e);
					}
				}
				
				planAccionDomain.insert(entry, user);
				
			} /*else {//update
				
			}*/
		});
		
		parametros.getPlanAccionActivos().forEach((entry) -> {
			PlanAccion planAccionActivo = planAccion.stream().filter(item -> item.getIdPlanAccion().equals( entry.getIdPlanAccion() ) ).findAny().orElse(null);
			
			if(  ObjectUtils.isEmpty(planAccionActivo) ) {//si no se encuentra en la lista se inactiva
				entry.setIdEvento(parametros.getIdEvento());
				entry.setIndActivo(ConstanteValor.IND_INACTIVO);
				
				if( !StringUtils.isEmpty(entry.getFecImplementacionStr()) ) {
					try {
						entry.setFecImplementacion(  DateTime.dateFromString(entry.getFecImplementacionStr(), "dd/MM/yyyy") );
					} catch (Exception e) {
						Record.error(user, e);
					}
				}
				
				planAccionDomain.updateByKey(entry.getIdPlanAccion(), entry, user);
			}
		});
		
		/* Fin save planAccion */
		
		return parametros;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Long uploadFile(File documento, MultipartFile file, Integer idInforme, String codUser) throws Exception {
		
		Long idFile = fileDomain.guardarFileAdjuntoAccionesEnInforme(documento, file, idInforme, codUser);
		
		return idFile;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Boolean deleteFile(Integer idEvento, Long idFile, String codUser) {
		
		Map<String, Object> mapaSend = new HashMap<String, Object>();
		mapaSend.put("idFile", idFile);
		
		BaseParam<Map<String, Long>> param = new BaseParam<Map<String, Long>>();
		param.setKeyMap(mapaSend);
		
		mapper.deleteFileCommentAdic(param);
		
		return true;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public File obtenerFileConstanciaEnvio(Long idEvento) throws Exception {
		
		BaseParam<File> param = new BaseParam<File>();
		param.setKey(idEvento);
		mapper.obtenerConstanciaEnvio(param);
		
		return CollectionUtils.isEmpty(param.getList()) ? new File() : param.getList().get(0);
	}
	
	public List<String> obtenerAniosHist() {
		
		BaseParam<String> param = new BaseParam<String>();
		mapper.obtenerAniosHist(param);
		
		return param.getList();
	}
	
	public List<String> obtenerDiffEval(Long idEvento) {
		
		BaseParam<String> param = new BaseParam<String>();
		param.setKey(idEvento);
		mapper.obtenerDiffEval(param);
		
		return param.getList();
	}
}
