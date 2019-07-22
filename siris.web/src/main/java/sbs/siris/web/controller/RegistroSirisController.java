package sbs.siris.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;

import sbs.siris.web.controller.base.AsyncResult;

@Controller
@RequestMapping("/registro_siris")
public class RegistroSirisController {
	
	/*@Autowired
	public SecurityService securityService;*/
	
	@GetMapping(value = "/updateSessionWeb")
	public DeferredResult<String> updateSessionWeb(HttpServletRequest request) {
		//securityService.autologin(HelperController.getCodUser() , request);
		return AsyncResult.Call(() -> {
			return "app/registro_datos/inicio.html";
		});
	}

	@GetMapping("/inicio")
	public DeferredResult<String> tableroCoopac(HttpSession session) {
		return AsyncResult.Call(() -> {
			return "app/registro_datos/inicio.html";
		});
	}

	@GetMapping("/tablero_registro")
	public DeferredResult<String> tableroRegistro(HttpSession session) {
		return AsyncResult.Call(() -> {
			return "app/registro_datos/tablero_registro.html";
		});
	}

	@GetMapping("/tablero_final")
	public DeferredResult<String> tableroFinal(HttpSession session) {
		return AsyncResult.Call(() -> {
			return "app/registro_datos/tablero_final.html";
		});
	}

	@GetMapping("/datos_coopac")
	public DeferredResult<String> datosCoopac(HttpSession session) {
		return AsyncResult.Call(() -> {
			return "app/registro_datos/datos_coopac.html";
		});
	}

	@GetMapping("/representante_coopac")
	public DeferredResult<String> representanteCoopac(HttpSession session) {
		return AsyncResult.Call(() -> {
			return "app/registro_datos/datos_representante.html";
		});
	}

	@GetMapping("/directivos_coopac")
	public DeferredResult<String> directivosCoopac(HttpSession session) {
		return AsyncResult.Call(() -> {
			return "app/registro_datos/datos_directivos.html";
		});
	}

	@GetMapping("/declaracion_coopac")
	public DeferredResult<String> declaracionCoopac(HttpSession session) {
		return AsyncResult.Call(() -> {
			return "app/registro_datos/declaracion_jurada.html";
		});
	}

	@GetMapping("/enviar_solicitud_coopac")
	public DeferredResult<String> enviarSolicitudCoopac(HttpSession session) {
		return AsyncResult.Call(() -> {
			return "app/registro_datos/enviar_solicitud.html";
		});
	}
	
	@GetMapping("/module/{module_form}")
	public DeferredResult<String> obtenerModuloFormulario(HttpSession session, @PathVariable String module_form) {
		return AsyncResult.Call(() -> {
			return "app/registro_datos/"+module_form+".html";
		});
	}
	
	@GetMapping("/module/{module_form}/{id_evento}")
	public DeferredResult<String> obtenerModuloFormularioParam(HttpSession session, @PathVariable String module_form, @PathVariable String id_evento, Model model) {
		return AsyncResult.Call(() -> {
			model.addAttribute("idEvento", id_evento);
			return "app/registro_datos/"+module_form+".html";
		});
	}

}