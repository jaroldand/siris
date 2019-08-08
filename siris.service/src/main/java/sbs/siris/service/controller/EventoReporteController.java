package sbs.siris.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import sbs.siris.domain.core.EventoReporteDomain;
import sbs.siris.domain.entity.ClaveValor;
import sbs.siris.domain.entity.Devolucion;
import sbs.siris.domain.entity.Evaluacion;
import sbs.siris.domain.entity.File;
import sbs.siris.domain.entity.FileDomain;
import sbs.siris.domain.entity.Validacion;
import sbs.siris.domain.entity.dto.BandejaDTO;
import sbs.siris.domain.entity.dto.DatosValidacionDTO;
import sbs.siris.domain.entity.dto.EventoReporteDTO;
import sbs.siris.domain.entity.dto.ResultDTO;
import sbs.siris.service.controller.base.AsyncResult;
import sbs.siris.service.controller.base.HelperController;

@RestController
@RequestMapping("/evento_reporte")
public class EventoReporteController {

	@Autowired
	private EventoReporteDomain eventoReporteDomain;
	
	@Autowired
	private FileDomain fileDomain;
	
	@GetMapping(value = "/search/evento/{idEntVig}")
	public DeferredResult<ResponseEntity<List<BandejaDTO>>> obtenerEventosBandeja(@PathVariable String idEntVig) {
		return AsyncResult.Call(() -> {
			return new ResponseEntity<List<BandejaDTO>>(eventoReporteDomain.obtenerEventosBandeja(idEntVig), HttpStatus.OK);
		});
	}
	
	@GetMapping(value = "/search/evento_hist/{idEntVig}/{anio}")
	public DeferredResult<ResponseEntity<List<BandejaDTO>>> obtenerEventosBandejaHistorica(@PathVariable String idEntVig, @PathVariable String anio) {
		return AsyncResult.Call(() -> {
			return new ResponseEntity<List<BandejaDTO>>(eventoReporteDomain.obtenerEventosBandejaHistorica(idEntVig, anio), HttpStatus.OK);
		});
	}
	
	@GetMapping(value = "/load/evaluacion/{idEvento}")
	public DeferredResult<ResponseEntity<Map<String, Evaluacion>>> obtenerEvaluacion(@PathVariable Integer idEvento) {
		return AsyncResult.Call(() -> {
			return new ResponseEntity<Map<String, Evaluacion>>(eventoReporteDomain.loadEvaluacionEvento(idEvento), HttpStatus.OK);
		});
	}
	
	@GetMapping(value = "/load/devolucion/{idEvento}")
	public DeferredResult<ResponseEntity<Devolucion>> obtenerDevolucion(@PathVariable Integer idEvento) {
		return AsyncResult.Call(() -> {
			return new ResponseEntity<Devolucion>(eventoReporteDomain.loadDevolucionEvento(idEvento), HttpStatus.OK);
		});
	}
	
	@GetMapping(value = "/load/evento/{idEvento}")
	public DeferredResult<ResponseEntity<Map<String, Validacion>>> obtenerValidacionEvento(@PathVariable Integer idEvento) {
		return AsyncResult.Call(() -> {
			return new ResponseEntity<Map<String, Validacion>>(eventoReporteDomain.loadValidacionEvento(idEvento), HttpStatus.OK);
		});
	}
	
	@PostMapping(value = "/verificar/evento")
	public DeferredResult<ResponseEntity<ResultDTO>> verificarEvento(@RequestBody Map<String, Validacion> motivos) {
		return AsyncResult.Call(() -> {
			return new ResponseEntity<ResultDTO>(eventoReporteDomain.verificarEvento(motivos), HttpStatus.OK);
		});
	}
	
	@PostMapping(value = "/save/evento")
	public DeferredResult<ResponseEntity<DatosValidacionDTO>> saveEvento(@RequestBody DatosValidacionDTO parametros) {
		String user = HelperController.getCodUser();
		return AsyncResult.Call(() -> {
			return new ResponseEntity<DatosValidacionDTO>(eventoReporteDomain.saveEvento(parametros, user), HttpStatus.OK);
		});
	}
	
	@GetMapping(value = "/load/canales/{idTipoEnt}")
	public DeferredResult<ResponseEntity<List<ClaveValor>>> obtenerCanalesPorTipoEnt(@PathVariable String idTipoEnt) {
		return AsyncResult.Call(() -> {
			return new ResponseEntity<List<ClaveValor>>(eventoReporteDomain.obtenerCanalesPorTipoEnt(idTipoEnt), HttpStatus.OK);
		});
	}
	
	@PostMapping(value = "/save/reporte")
	public DeferredResult<ResponseEntity<EventoReporteDTO>> saveReporte(@RequestBody EventoReporteDTO parametros) {
		String user = HelperController.getCodUser();
		return AsyncResult.Call(() -> {
			return new ResponseEntity<EventoReporteDTO>(eventoReporteDomain.saveReporte(parametros, user), HttpStatus.OK);
		});
	}
	
	@PostMapping(value = "/enviar/reporte")
	public DeferredResult<ResponseEntity<EventoReporteDTO>> enviarReporte(@RequestBody EventoReporteDTO parametros) {
		String user = HelperController.getCodUser();
		return AsyncResult.Call(() -> {
			return new ResponseEntity<EventoReporteDTO>(eventoReporteDomain.enviarReporte(parametros, user), HttpStatus.OK);
		});
	}
	
	@GetMapping(value = "/load/reporte/{idEvento}")
	public DeferredResult<ResponseEntity<EventoReporteDTO>> obtenerReporteEvento(@PathVariable Integer idEvento) {
		return AsyncResult.Call(() -> {
			return new ResponseEntity<EventoReporteDTO>(eventoReporteDomain.obtenerReporteEvento(idEvento), HttpStatus.OK);
		});
	}
	
	@PostMapping(value = "/delete/reporte")
	public DeferredResult<ResponseEntity<Boolean>> deleteReporte(@RequestBody Integer idEvento) {
		String user = HelperController.getCodUser();
		return AsyncResult.Call(() -> {
			return new ResponseEntity<Boolean>(eventoReporteDomain.deleteReporte(idEvento, user), HttpStatus.OK);
		});
	}
	
	@PostMapping(value = "/upload_file")
	public DeferredResult<ResponseEntity<Long>> uploadFile(@ModelAttribute File documento, @RequestParam("file") MultipartFile uploadfile, @RequestParam("idInforme") Integer idInforme) throws Exception {
		String user = HelperController.getCodUser();
		return AsyncResult.Call(() -> {
			return new ResponseEntity<Long>(eventoReporteDomain.uploadFile(documento, uploadfile, idInforme, user), HttpStatus.CREATED);
		}, user);
	}
	
	@GetMapping(value = "/delete_file/{idEvento}/{idFile}")
	public DeferredResult<ResponseEntity<Boolean>> deleteFile(@PathVariable Integer idEvento, @PathVariable Long idFile) {
		String user = HelperController.getCodUser();
		return AsyncResult.Call(() -> {
			return new ResponseEntity<Boolean>(eventoReporteDomain.deleteFile(idEvento, idFile, user), HttpStatus.OK);
		});
	}
	
	@GetMapping(value = "/download_file/{idFile}")
	public DeferredResult<ResponseEntity<Resource>> downloadFileComentarioAdicional(@PathVariable Long idFile) {
		
		return AsyncResult.Call(() -> {
			File documento = fileDomain.getEntityByKey(idFile, null);

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + documento.getDesDocumento() + "." + documento.getDesExtension()).contentLength(documento.getNumTamanio())
					.contentType(MediaType.parseMediaType("application/" + documento.getDesExtension())).body(new ByteArrayResource(documento.getBinArchivo()));
		});
	}
	
	@GetMapping(value = "/download_file_constancia/{idEvento}")
	public DeferredResult<ResponseEntity<Resource>> downloadFileConstancia(@PathVariable Long idEvento) {
		
		return AsyncResult.Call(() -> {
			File documento = eventoReporteDomain.obtenerFileConstanciaEnvio(idEvento);

			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + documento.getDesDocumento() + "." + documento.getDesExtension()).contentLength(documento.getNumTamanio())
					.contentType(MediaType.parseMediaType("application/" + documento.getDesExtension())).body(new ByteArrayResource(documento.getBinArchivo()));
		});
	}
	
	@GetMapping(value = "/anios_hist")
	public DeferredResult<ResponseEntity<List<String>>> obtenerAniosHist() {
		return AsyncResult.Call(() -> {
			return new ResponseEntity<List<String>>(eventoReporteDomain.obtenerAniosHist(), HttpStatus.OK);
		});
	}
	
	@GetMapping(value = "/eval_diff/{idEvento}")
	public DeferredResult<ResponseEntity<List<String>>> obtenerDiffEval(@PathVariable Long idEvento) {
		return AsyncResult.Call(() -> {
			return new ResponseEntity<List<String>>(eventoReporteDomain.obtenerDiffEval(idEvento), HttpStatus.OK);
		});
	}
}
