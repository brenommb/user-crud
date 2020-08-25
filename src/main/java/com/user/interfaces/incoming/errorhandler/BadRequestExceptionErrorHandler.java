package com.user.interfaces.incoming.errorhandler;

import com.user.domain.common.exceptions.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Locale;

@Slf4j
public class BadRequestExceptionErrorHandler extends ExceptionErrorHandler<BadRequestException> {

	private BadRequestExceptionErrorHandler(final MessageSource messageSource, final Locale locale) {
		super(messageSource, locale);
	}

	public static ResponseEntity build(final BadRequestException exception, final MessageSource messageSource,
			final Locale locale) {
		return new BadRequestExceptionErrorHandler(messageSource, locale).build(exception);
	}

	@Override
	protected ResponseEntity build(final BadRequestException exception) {
		final List<ErrorMessage> errorMessages = getErrorMessages(exception.getCode(), exception.getArgs());
		ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<>(new ErrorResponse(errorMessages),
				BadRequestException.statusCode);
		log.error("Bad request: {}", responseEntity);
		log.error("Error detail", exception);
		return responseEntity;
	}

}
