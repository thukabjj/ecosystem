package br.com.ecosystem.infra.validation.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.ecosystem.infra.validation.CustomValidationException;



public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler({Exception.class})
	public ResponseEntity<ErrorResponse> handleAnyException(Exception ex) {
		String message = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
		log.error("error caught: {}", message, ex);
		HttpStatus code = HttpStatus.BAD_REQUEST;
		ErrorResponse errorResponse = getErrorResponse(code, message);
		return new ResponseEntity<>(errorResponse, code);
	}
	
	@ExceptionHandler(CustomValidationException.class)
	public final ResponseEntity<ErrorResponse> handleExceptionCustom(CustomValidationException ex, WebRequest request) {
		String message = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
		log.error("error caught: {}", message, ex);
		ErrorResponse errorResponse = getErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	private ErrorResponse getErrorResponse(HttpStatus status, String message) {
		return new ErrorResponse(message, status.value(), status.getReasonPhrase());
	}


}
