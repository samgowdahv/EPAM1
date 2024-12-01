package com.portal.backend.clientinterviewtracker.exceptionhandler;

import com.portal.backend.clientinterviewtracker.exception.DetailsNotFoundException;
import com.portal.backend.clientinterviewtracker.exception.TokenNotFoundException;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(DetailsNotFoundException.class)
	public ResponseEntity<ErrorResponse> detailsNotFoundHandler(RuntimeException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getMessage()));
	}

	@ExceptionHandler(InvalidUserException.class)
	public ResponseEntity<ErrorResponse> handleInvalidUserException(InvalidUserException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ex.getMessage()));
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ProblemDetail handleUserNameNotFoundException(Exception ex) {
		return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ProblemDetail handleSecurityException(Exception exception){

		ProblemDetail problemDetail = null;

		if(exception instanceof BadCredentialsException){
			problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(403), exception.getMessage());
		}
		if(exception instanceof ExpiredJwtException){
			problemDetail = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(401), exception.getMessage());
		}

		return problemDetail;
	}
	@ExceptionHandler(TokenNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleJwtTokenNotFoundException(Exception exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getMessage()));
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleEntityNotFoundException(Exception exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getMessage()));
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorResponse> handleNoSuchElementException(Exception exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(exception.getMessage()));
	}
}
