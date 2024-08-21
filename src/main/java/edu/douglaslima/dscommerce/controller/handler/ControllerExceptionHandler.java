package edu.douglaslima.dscommerce.controller.handler;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import edu.douglaslima.dscommerce.dto.CustomError;
import edu.douglaslima.dscommerce.dto.InvalidFieldMessage;
import edu.douglaslima.dscommerce.dto.ValidationError;
import edu.douglaslima.dscommerce.service.exception.DatabaseException;
import edu.douglaslima.dscommerce.service.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<CustomError> handleResouceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
		CustomError error = new CustomError(Instant.now(), HttpStatus.NOT_FOUND.value(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	

	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<CustomError> handleDatabaseException(DatabaseException e, HttpServletRequest request) {
		CustomError error = new CustomError(Instant.now(), HttpStatus.BAD_REQUEST.value(), e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<CustomError> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidationError error = new ValidationError(Instant.now(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Dados inválidos", request.getRequestURI());
		e.getFieldErrors().forEach(fieldError -> {
			error.addError(new InvalidFieldMessage(fieldError.getField(), fieldError.getDefaultMessage()));
		});
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
	}

}
