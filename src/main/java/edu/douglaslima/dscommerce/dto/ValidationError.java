package edu.douglaslima.dscommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends CustomError {
	
	private List<InvalidFieldMessage> errors = new ArrayList<>();

	public ValidationError(Instant timestamp, Integer status, String error, String path) {
		super(timestamp, status, error, path);
	}
	
	public List<InvalidFieldMessage> getErrors() {
		return errors;
	}
	
	public void addError(InvalidFieldMessage error) {
		errors.add(error);
	}
	
}
