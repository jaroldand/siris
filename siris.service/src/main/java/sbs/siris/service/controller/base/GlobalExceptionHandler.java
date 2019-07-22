package sbs.siris.service.controller.base;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import sbs.cross.util.exception.BusinessAsyncException;
import sbs.cross.util.exception.BusinessException;
import sbs.cross.util.exception.LevelType;
import sbs.cross.util.exception.StatusCode;
import sbs.cross.util.exception.UncaughtException;
import sbs.siris.cross.resources.MensajesDB;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessAsyncException.class)
	public ResponseEntity<BaseException> handledBusinessAsyncException(BusinessAsyncException exception, HttpServletRequest request) {
		BaseException response = new BaseException();
		response.setMessage(exception.getMessage());
		response.setLevelType(exception.getLevelType());
		response.setStatusCode(exception.getStatusCode());
		response.setLogCode(HelperController.inserLog(exception, request, exception.getCodUser(), 1));
		return new ResponseEntity<BaseException>(response, HttpStatus.resolve(exception.getStatusCode().getValue()));
	}

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<BaseException> handledBusinessException(BusinessException exception, HttpServletRequest request) {
		BaseException response = new BaseException();
		response.setMessage(exception.getMessage());
		response.setLevelType(exception.getLevelType());
		response.setStatusCode(exception.getStatusCode());
		response.setLogCode(HelperController.inserLog(exception, request, HelperController.getCodUser(), 1));
		return new ResponseEntity<BaseException>(response, HttpStatus.resolve(exception.getStatusCode().getValue()));
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<BaseException> handleUncaughtThrowable(final Throwable throwable, HttpServletRequest request) {
		return unCaughtException((Exception) throwable, request, HelperController.getCodUser());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseException> handleUncaughtException(Exception exception, HttpServletRequest request) {
		return unCaughtException(exception, request, HelperController.getCodUser());
	}

	@ExceptionHandler(UncaughtException.class)
	public ResponseEntity<BaseException> handleCustomUncaughtException(UncaughtException exception, HttpServletRequest request) {

		return unCaughtException(exception.getOriginalException(), request, exception.getCodUser());
	}

	private ResponseEntity<BaseException> unCaughtException(Exception exception, HttpServletRequest request, String codUser) {
		StatusCode sc = StatusCode.NO_FOUND;
		String msg = exception.getMessage();
		LevelType level = LevelType.ERROR;
		
		if (exception instanceof HttpRequestMethodNotSupportedException) {
			sc = StatusCode.NOT_ALLOWED;
		}
		else if (exception instanceof NoHandlerFoundException) {
			sc = StatusCode.NOT_ACCEPTABLE;
		}
		else if (exception instanceof UncategorizedSQLException || exception instanceof SQLException) {
			if(exception.getMessage().indexOf("-20300") >= 0) {//validaciones controladas
				msg = MensajesDB.ERROR_20300;
				level = LevelType.WARNING;
			}
		}
		else
			msg = "Error controlado, por favor contáctese con la SBS";
		BaseException response = new BaseException();
		response.setMessage(msg);
		response.setLevelType(level);
		response.setStatusCode(sc);
		if(LevelType.ERROR.equals(level)) {
			response.setLogCode(HelperController.inserLog(exception, request, codUser, 2));
		}
		return new ResponseEntity<BaseException>(response, HttpStatus.resolve(sc.getValue()));
	}

}