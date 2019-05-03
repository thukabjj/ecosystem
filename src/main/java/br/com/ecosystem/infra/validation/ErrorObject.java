package br.com.ecosystem.infra.validation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorObject{
	
	
	@JsonProperty("message")
	private String message;
	@JsonProperty("field")
	private String field;
	@JsonProperty("parameter")
	private Object parameter;

	public ErrorObject() {
	}

	public ErrorObject(String message, String field, Object parameter) {

		this.message = message;
		this.field = field;
		this.parameter = parameter;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getParameter() {
		return parameter;
	}

	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}


	

}
