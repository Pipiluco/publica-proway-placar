package life.pifrans.exceptions.treatment;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import life.pifrans.exceptions.errors.DataIntegrityException;
import life.pifrans.exceptions.errors.ObjectNotFoundException;

/*
 * Classe para tratar erros gerenciados por classes do pacote exceptions.errors
 */
@ControllerAdvice
public class HandlerError {

	/**
	 * Trata erro do tipo {@link ObjectNotFoundException}
	 */
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException exception, HttpServletRequest request) {
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
	
	/**
	 * Classe para tratar erros de {@link DataIntegrityException}
	 */
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException exception, HttpServletRequest request) {
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
