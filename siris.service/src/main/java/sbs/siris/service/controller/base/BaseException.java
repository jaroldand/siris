package sbs.siris.service.controller.base;

import com.fasterxml.jackson.annotation.JsonProperty;

import sbs.cross.util.exception.LevelType;
import sbs.cross.util.exception.StatusCode;

public class BaseException {
	private String logCode;
	private StatusCode statusCode;
	private LevelType levelType;
	private String message;

	@JsonProperty("log_code")
	public String getLogCode() {
		return logCode;
	}

	public void setLogCode(String logCode) {
		this.logCode = logCode;
	}

	@JsonProperty("status_code")
	public StatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}

	@JsonProperty("level_type")
	public LevelType getLevelType() {
		return levelType;
	}

	public void setLevelType(LevelType levelType) {
		this.levelType = levelType;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
