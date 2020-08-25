package com.user.interfaces.incoming.errorhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;

@Slf4j
public class ConstraintViolationExceptionErrorHandler extends ExceptionErrorHandler<ConstraintViolationException> {

	private ConstraintViolationExceptionErrorHandler(final MessageSource messageSource, final Locale locale) {
		super(messageSource, locale);
	}

	public static ResponseEntity build(final ConstraintViolationException exception, final MessageSource messageSource,
			final Locale locale) {
		return new ConstraintViolationExceptionErrorHandler(messageSource, locale).build(exception);
	}

	@Override
	protected ResponseEntity build(final ConstraintViolationException exception) {
		final List<ErrorMessage> errorMessages = exception.getConstraintViolations().stream()
				.map(e -> ErrorMessage.builder().code(e.getMessage()).message(super.getMessage(e.getMessage()))
						.info(super.getMessage(e.getPropertyPath().toString())).build())
				.collect(toList());
		ResponseEntity<ErrorResponse> responseEntity = ResponseEntity.badRequest()
				.body(new ErrorResponse(errorMessages));
		log.error("ConstraintViolationException: {}", responseEntity);
		log.error("Error detail", exception);
		return responseEntity;
	}

}
