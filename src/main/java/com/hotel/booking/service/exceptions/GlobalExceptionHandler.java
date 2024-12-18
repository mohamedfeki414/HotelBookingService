package com.hotel.booking.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	 @ExceptionHandler(NoSuchElementFoundException.class)
	    @ResponseStatus(HttpStatus.NOT_FOUND)
	    public ResponseEntity<String> handleNoSuchElementFoundException(NoSuchElementFoundException exception) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	    }

	    @ExceptionHandler(InvalidInputException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)
	    public ResponseEntity<String> handleInvalidInputException(InvalidInputException ex) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	    }

}
