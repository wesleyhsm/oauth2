
package br.com.car.rent.system.handler;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.car.rent.system.dto.Error;
import br.com.car.rent.system.dto.Response;
import br.com.car.rent.system.exception.CheckYearCarException;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(ParseException.class)
	public ResponseEntity<Response<Error>> handleReportParseException(final Exception e) {
		return new ResponseEntity<>(new Response<>(reportParseException(e)), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private Error reportParseException(final Exception e) {
		return new Error.Builder().setCode(99).setMessage(e.getMessage()).build();
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Response<Error>> handleReportDataIntegrityViolationException(final Exception e) {
		return new ResponseEntity<>(new Response<>(reportDataIntegrityViolationException(e)), HttpStatus.BAD_REQUEST);
	}

	private Error reportDataIntegrityViolationException(final Exception e) {
		return new Error.Builder().setCode(98).setMessage(e.getMessage()).build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response<Error>> handleReportMethodArgumentNotValidException(final Exception e) {
		return new ResponseEntity<>(new Response<>(reportMethodArgumentNotValidException(e)), HttpStatus.BAD_REQUEST);
	}

	private Error reportMethodArgumentNotValidException(final Exception e) {
		return new Error.Builder().setCode(97).setMessage(e.getMessage()).build();
	}

	@ExceptionHandler(NoSuchAlgorithmException.class)
	public ResponseEntity<Response<Error>> handleReportNoSuchAlgorithmException(final Exception e) {
		return new ResponseEntity<>(new Response<>(reportNoSuchAlgorithmException(e)), HttpStatus.BAD_REQUEST);
	}

	private Error reportNoSuchAlgorithmException(final Exception e) {
		return new Error.Builder().setCode(96).setMessage(e.getMessage()).build();
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Response<Error>> handleReportHttpMessageNotReadableException(final Exception e) {
		return new ResponseEntity<>(new Response<>(reportHttpMessageNotReadableException(e)), HttpStatus.BAD_REQUEST);
	}

	private Error reportHttpMessageNotReadableException(final Exception e) {
		return new Error.Builder().setCode(95).setMessage(e.getMessage()).build();
	}

	@ExceptionHandler(CheckYearCarException.class)
	public ResponseEntity<Response<Error>> handleReportCheckYearCarException(final Exception e) {
		return new ResponseEntity<>(new Response<>(reportCheckYearCarException(e)), HttpStatus.BAD_REQUEST);
	}

	private Error reportCheckYearCarException(final Exception e) {
		return new Error.Builder().setCode(94).setMessage(e.getMessage()).build();
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response<Error>> handleException(final Exception e) {
		return new ResponseEntity<>(new Response<>(reportException(e)), HttpStatus.BAD_REQUEST);
	}

	private Error reportException(final Exception e) {
		return new Error.Builder().setCode(91).setMessage(e.getMessage()).build();
	}
}
