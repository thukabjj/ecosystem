package br.com.ecosystem.infra.validation.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class DefaultGlobalException extends RuntimeException {
	
	private static final long serialVersionUID = 7860313102665016732L;
	private HttpStatus httpStatus;
	private String message;
	
	public DefaultGlobalException() {
		super();
	}
	
	public DefaultGlobalException(String message) {
		super(message);
		this.message = message;
	}

	public DefaultGlobalException(String message, Throwable cause) {
		super(message, cause);
		this.message = message;
	}
}
