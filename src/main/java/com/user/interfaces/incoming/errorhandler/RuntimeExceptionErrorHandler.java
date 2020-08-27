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
public class RuntimeExceptionErrorHandler extends ExceptionErrorHandler<RuntimeException> {

	private RuntimeExceptionErrorHandler(final MessageSource messageSource, final Locale locale) {
		super(messageSource, locale);
	}

	public static ResponseEntity build(final RuntimeException exception, final MessageSource messageSource,
			final Locale locale) {
		return new RuntimeExceptionErrorHandler(messageSource, locale).build(exception);
	}

	@Override
	protected ResponseEntity build(final RuntimeException exception) {
		final String errorMessage = super.getMessage(MessageErrorCode.UNEXPECTED_INTERNAL);
		log.error(errorMessage, exception);
		final List<ErrorMessage> errorMessages = Collections
				.singletonList(new ErrorMessage(MessageErrorCode.UNEXPECTED_INTERNAL, errorMessage, exception.getLocalizedMessage()));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(errorMessages));
	}

}
