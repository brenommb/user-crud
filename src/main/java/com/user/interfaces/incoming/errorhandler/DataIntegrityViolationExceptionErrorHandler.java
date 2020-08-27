package com.user.interfaces.incoming.errorhandler;

import com.user.infrastructure.MessageErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Slf4j
public class DataIntegrityViolationExceptionErrorHandler
		extends ExceptionErrorHandler<DataIntegrityViolationException> {

	private DataIntegrityViolationExceptionErrorHandler(final MessageSource messageSource, final Locale locale) {
		super(messageSource, locale);
	}

	public static ResponseEntity build(final DataIntegrityViolationException exception,
			final MessageSource messageSource, final Locale locale) {
		return new DataIntegrityViolationExceptionErrorHandler(messageSource, locale).build(exception);
	}

	@Override
	protected ResponseEntity build(final DataIntegrityViolationException exception) {
		List<ErrorMessage> errorMessages = getErrorMessages(MessageErrorCode.DATABASE_ERROR,
				Collections.singletonList(exception.getMessage()));
		ResponseEntity<ErrorResponse> responseEntity = ResponseEntity.badRequest()
				.body(new ErrorResponse(errorMessages));
		log.error("ConstraintViolationException: {}", responseEntity);
		log.error("Error detail", exception);
		return responseEntity;
	}

}
