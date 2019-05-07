package br.com.ecosystem.infra.validation;

import org.springframework.http.HttpStatus;

import br.com.ecosystem.infra.validation.exception.DefaultGlobalException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomValidationException extends DefaultGlobalException {

	private static final long serialVersionUID = -8219027264325938406L;

	private HttpStatus httpStatus;
	private String message;

	public CustomValidationException() {
		super();
	}
	public CustomValidationException(String message) {
		super(message);
		this.message = message;
		this.httpStatus = HttpStatus.BAD_REQUEST;
	}
	
	public CustomValidationException(String message, HttpStatus httpStatus) {
		super(message);
		this.message = message;
		this.httpStatus = httpStatus;
	}

	public CustomValidationException(String message, Throwable cause, HttpStatus httpStatus) {
		super(message, cause);
		this.message = message;
		this.httpStatus = httpStatus;
	}
}
