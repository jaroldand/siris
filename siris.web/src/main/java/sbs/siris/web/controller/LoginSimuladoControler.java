package sbs.siris.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.ModelAndView;

import sbs.cross.config.Setup;
import sbs.siris.web.controller.base.AsyncResult;

@Controller
@RequestMapping("/login")
public class LoginSimuladoControler {

	public final static Long TIMEOUT_TOKEN = Setup.getValueKeySys("KeyTimeoutToken", Long.class);
	public final static String FORMAT_DATE = Setup.getValueKeySys("KeyFormatDate");

	@GetMapping
	public DeferredResult<ModelAndView> login() {

		return AsyncResult.Call(() -> {
			ModelAndView model = new ModelAndView("pub/login.html");
			return model;
		});

	}

}
