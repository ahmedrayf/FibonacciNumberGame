package com.Fibonacci.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class RestExceptionHandlerController {
	private CustomException customException;

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<Object> handleCustomException(BadRequestException ex) {
		customException = new CustomException(ex.getMessage());
		return new ResponseEntity<>(customException,HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handle(Exception ex,
										 HttpServletRequest request, HttpServletResponse response) {

		if (ex instanceof NullPointerException) {
			customException = new CustomException("Invalid Json format, please check your format");
			return new ResponseEntity<>(customException,HttpStatus.BAD_REQUEST);
		}
		customException = new CustomException(ex.getMessage());
		return new ResponseEntity<>(customException,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

