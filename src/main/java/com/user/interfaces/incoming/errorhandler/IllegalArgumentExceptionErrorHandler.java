package com.user.interfaces.incoming.errorhandler;

import com.user.infrastructure.MessageErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Slf4j
public class IllegalArgumentExceptionErrorHandler extends ExceptionErrorHandler<IllegalArgumentException> {

	private IllegalArgumentExceptionErrorHandler(final MessageSource messageSource, final Locale locale) {
		super(messageSource, locale);
	}

	public static ResponseEntity build(final IllegalArgumentException exception, final MessageSource messageSource,
			final Locale locale) {
		return new IllegalArgumentExceptionErrorHandler(messageSource, locale).build(exception);
	}

	@Override
	protected ResponseEntity build(final IllegalArgumentException exception) {
		final String errorMessage = super.getMessage(MessageErrorCode.INVALID_ARGUMENT);
		log.error(errorMessage, exception);
		final List<ErrorMessage> errorMessages = Collections.singletonList(
				new ErrorMessage(MessageErrorCode.INVALID_ARGUMENT, errorMessage, exception.getLocalizedMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errorMessages));
	}

}
