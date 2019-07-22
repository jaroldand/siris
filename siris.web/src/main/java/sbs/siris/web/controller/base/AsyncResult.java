package sbs.siris.web.controller.base;

import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;

import org.springframework.web.context.request.async.DeferredResult;

import sbs.cross.util.exception.UncaughtException;

public class AsyncResult {

	public static <T> DeferredResult<T> Call(Callable<T> arg) {
		
		DeferredResult<T> response = new DeferredResult<>();
		ForkJoinPool.commonPool().submit(() -> {
			try {
				response.setResult(arg.call());
			} catch (Exception e) {
				UncaughtException ex = new UncaughtException();
				ex.setOriginalException(e);
				ex.setCodUser(HelperController.getCodUser());
				response.setErrorResult(ex);
			} catch (Throwable e) {
				Exception ex = new Exception(e);
				UncaughtException et = new UncaughtException();
				et.setOriginalException(ex);
				et.setCodUser(HelperController.getCodUser());
				response.setErrorResult(et);
			}
		});
		return response;
	}

}