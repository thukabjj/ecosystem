package br.com.ecosystem.configuration.validation;

import org.springframework.http.HttpStatus;

public class CustomValidationException extends RuntimeException {

	private static final long serialVersionUID = 379337860307596129L;

	private Integer erroCode;
	private HttpStatus erroResponseStatus;
	private String objecName;
	private String message;
	private HttpStatus responseStatus;

	public CustomValidationException() {
		super();
	}

	public CustomValidationException(String message) {
		super(message);
		this.message = message;
	}

	public CustomValidationException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}

	public CustomValidationException(CustomValidationException ex) {
		super(ex);
		this.responseStatus = ex.getResponseStatus();
		this.erroCode = ex.getErroCode();
		this.erroResponseStatus = ex.getErroResponseStatus();
		this.message = ex.getMessage();
		this.objecName = ex.getObjecName();

	}

	public String getObjecName() {
		return objecName;
	}

	public void setObjecName(String objecName) {
		this.objecName = objecName;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getErroCode() {
		return erroCode;
	}

	public void setErroCode(Integer erroCode) {
		this.erroCode = erroCode;
	}

	public HttpStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(HttpStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public HttpStatus getErroResponseStatus() {
		return erroResponseStatus;
	}

	public void setErroResponseStatus(HttpStatus erroResponseStatus) {
		this.erroResponseStatus = erroResponseStatus;
	}
}
