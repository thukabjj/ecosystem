package br.com.ecosystem.infra.validation.exception;

import java.io.Serializable;
import java.util.Date;


public class ErrorResponse implements Serializable {
	
	private static final long serialVersionUID = 6024645565050373863L;
	private String message;
    private int code;
    private String status;
    private Date timestamp;
    
    public ErrorResponse(){}
    
	public ErrorResponse(String message, int code, String status) {
	
		this.message = message;
		this.code = code;
		this.status = status;
		this.timestamp = new Date();
	}
	
	public String getMessage() {
		return message;
	}
	public int getCode() {
		return code;
	}
	public String getStatus() {
		return status;
	}

	public long getTimestamp() {
		return timestamp.getTime();
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
