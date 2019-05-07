package br.com.ecosystem.infra.validation.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.ecosystem.infra.validation.CustomValidationException;



@RestControllerAdvice
public class DefaultExceptionTranslator extends ResponseEntityExceptionHandler {
	private static final Logger log = LoggerFactory.getLogger(DefaultExceptionTranslator.class);

	@ExceptionHandler({ Exception.class })
	public ResponseEntity<ErrorResponse> interceptorGlobalException(Exception ex) {
		String message = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
		log.error("error caught: {}", message, ex);
		ErrorResponse errorResponse = new ErrorResponse(message, HttpStatus.BAD_REQUEST.value(),
				HttpStatus.BAD_REQUEST.getReasonPhrase());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ HttpClientErrorException.class})
	public ResponseEntity<ErrorResponse> interceptorHttpClientErrorException(HttpClientErrorException ex) {
		String message = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
		log.error("error caught: {}", message, ex);
		ErrorResponse errorResponse = new ErrorResponse(message, ex.getStatusCode().value(),
				ex.getStatusCode().getReasonPhrase());
		return new ResponseEntity<ErrorResponse>(errorResponse, ex.getStatusCode());
	}
	
	@ExceptionHandler({HttpServerErrorException.class })
	public ResponseEntity<ErrorResponse> interceptorHttpServerErrorException(HttpClientErrorException ex) {
		String message = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
		log.error("error caught: {}", message, ex);
		ErrorResponse errorResponse = new ErrorResponse(message, ex.getStatusCode().value(),
				ex.getStatusCode().getReasonPhrase());
		return new ResponseEntity<ErrorResponse>(errorResponse, ex.getStatusCode());
	}
	

	@ExceptionHandler(CustomValidationException.class)
	public final ResponseEntity<ErrorResponse> handleExceptionCustom(CustomValidationException ex, WebRequest request) {
		String message = ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage();
		log.error("error caught: {}", message, ex);
		ErrorResponse errorResponse = new ErrorResponse(message, ex.getHttpStatus().value(),
				ex.getHttpStatus().getReasonPhrase());
		return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
	}
}
