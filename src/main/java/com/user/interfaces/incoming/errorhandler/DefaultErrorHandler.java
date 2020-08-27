package com.user.interfaces.incoming.errorhandler;

import com.user.domain.common.exceptions.ApplicationException;
import com.user.domain.common.exceptions.BadRequestException;
import com.user.domain.common.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
public class DefaultErrorHandler {

	private final MessageSource messageSource;

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity handleServerWebInputException(final MethodArgumentNotValidException exception,
			final Locale locale) {
		return MethodArgumentNotValidExceptionErrorHandler.build(exception, messageSource, locale);
	}

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity handleApplicationException(final ApplicationException exception, final Locale locale) {
		return ApplicationExceptionErrorHandler.build(exception, messageSource, locale);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity handleConstraintViolationException(final DataIntegrityViolationException exception,
			final Locale locale) {
		return DataIntegrityViolationExceptionErrorHandler.build(exception, messageSource, locale);
	}

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity handleNotFoundException(final NotFoundException exception, final Locale locale) {
		return NotFoundExceptionErrorHandler.build(exception, messageSource, locale);
	}

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity handleBadRequestException(final BadRequestException exception, final Locale locale) {
		return BadRequestExceptionErrorHandler.build(exception, messageSource, locale);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity handleIllegalArgsException(final IllegalArgumentException exception, final Locale locale) {
		return IllegalArgumentExceptionErrorHandler.build(exception, messageSource, locale);
	}

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity handleRuntimeException(final RuntimeException exception, final Locale locale) {
		return RuntimeExceptionErrorHandler.build(exception, messageSource, locale);
	}

}