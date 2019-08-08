package sbs.siris.service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import sbs.siris.domain.entity.ClaveValor;
import sbs.siris.domain.entity.TipoLista;
import sbs.siris.domain.entity.TipoListaDomain;
import sbs.siris.service.controller.base.AsyncResult;
import sbs.siris.service.controller.base.BaseController;

@RestController
@RequestMapping("/tipos")
public class TipoListaController extends BaseController<TipoLista, String> {

	public TipoListaController(TipoListaDomain domain) {
		super.setDomain(domain);
	}

	
	@GetMapping("/tipos_eventos")
	public DeferredResult<ResponseEntity<List<ClaveValor>>> tiposEventos() {
		return AsyncResult.Call(() -> {
			return new ResponseEntity<List<ClaveValor>>(((TipoListaDomain) domain).tiposEventos(), HttpStatus.OK);
		});
	}
	
	@GetMapping("/departamentos")
	public DeferredResult<ResponseEntity<List<TipoLista>>> obtenerDepartamentos() {
		return AsyncResult.Call(() -> {
			return new ResponseEntity<List<TipoLista>>(((TipoListaDomain) domain).obtenerUbigeoDepartamentos(), HttpStatus.OK);
		});
	}
	
	@GetMapping("/impactos")
	public DeferredResult<ResponseEntity<List<ClaveValor>>> obtenerImpactosInterrupcion() {
		return AsyncResult.Call(() -> {
			return new ResponseEntity<List<ClaveValor>>(((TipoListaDomain) domain).obtenerImpactosInterrupcion(), HttpStatus.OK);
		});
	}
	
	@GetMapping("/question1")
	public DeferredResult<ResponseEntity<List<ClaveValor>>> obtenerEvalQuestions1() {
		return AsyncResult.Call(() -> {
			return new ResponseEntity<List<ClaveValor>>(((TipoListaDomain) domain).obtenerPreguntaEvaluacion1(), HttpStatus.OK);
		});
	}
	
	@GetMapping("/question_otros")
	public DeferredResult<ResponseEntity<List<ClaveValor>>> obtenerEvalQuestionsOtros() {
		return AsyncResult.Call(() -> {
			return new ResponseEntity<List<ClaveValor>>(((TipoListaDomain) domain).obtenerPreguntaEvaluacionOtros(), HttpStatus.OK);
		});
	}
}
