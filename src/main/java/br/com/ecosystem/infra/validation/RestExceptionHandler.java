package br.com.ecosystem.infra.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleAnyException(Exception e) {
		log.error("error caught: " + e.getMessage(), e);
		FieldError fieldError = new FieldError(null, null, e.getMessage());
		ErrorObject errorDetails = new ErrorObject(e.getMessage(), fieldError.getField(), null);
		List<ErrorObject> errors = new ArrayList<ErrorObject>();
		errors.add(errorDetails);
		HttpStatus code = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorResponse errorResponse = getErrorResponse(code, errors, e.getMessage());
		return new ResponseEntity<>(errorResponse, code);
	}
	
	@ExceptionHandler(CustomValidationException.class)
	public final ResponseEntity<Object> handleExceptionCustom(CustomValidationException ex, WebRequest request) {
		log.error("error caught: " + ex.getMessage(), ex);
		FieldError fieldError = new FieldError(ex.getClass().getName(), "", ex.getMessage());
		ErrorObject errorDetails = new ErrorObject(ex.getMessage(), fieldError.getField(), fieldError.getRejectedValue());
		List<ErrorObject> errors = new ArrayList<ErrorObject>();
		errors.add(errorDetails);
		HttpStatus code = ex.getResponseStatus() != null && ex.getResponseStatus().equals(HttpStatus.OK) ? ex.getResponseStatus() : HttpStatus.BAD_REQUEST;
		ErrorResponse errorResponse = getErrorResponse(code, errors, ex.getMessage());
		return new ResponseEntity<>(errorResponse, code);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ErrorObject> errors = getErrors(ex);
		ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
		return new ResponseEntity<>(errorResponse, status);
	}


	private ErrorResponse getErrorResponse(HttpStatus status, List<ErrorObject> errors, String message) {
		return new ErrorResponse(message, status.value(), status.getReasonPhrase(), "", errors);
	}

	private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status, List<ErrorObject> errors) {
		return new ErrorResponse("Requisição possui campos inválidos", status.value(), status.getReasonPhrase(), ex.getBindingResult().getObjectName(), errors);
	}

	private List<ErrorObject> getErrors(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getFieldErrors().stream().map(error -> new ErrorObject(error.getDefaultMessage(), error.getField(), error.getRejectedValue())).collect(Collectors.toList());
	}
}
