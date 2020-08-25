package com.user.interfaces.incoming.errorhandler;

import com.user.domain.common.exceptions.ApplicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Locale;

@Slf4j
public class ApplicationExceptionErrorHandler extends ExceptionErrorHandler<ApplicationException> {

	private ApplicationExceptionErrorHandler(final MessageSource messageSource, final Locale locale) {
		super(messageSource, locale);
	}

	public static ResponseEntity build(final ApplicationException exception, final MessageSource messageSource,
			final Locale locale) {
		return new ApplicationExceptionErrorHandler(messageSource, locale).build(exception);
	}

	@Override
	protected ResponseEntity build(final ApplicationException exception) {
		final List<ErrorMessage> errorMessages = getErrorMessages(exception.getCode(), exception.getArgs());
		ResponseEntity<ErrorResponse> responseEntity = ResponseEntity.status(exception.getStatusCode())
				.body(new ErrorResponse(errorMessages));
		log.error("ApplicationException error: {}", responseEntity);
		log.error("Error detail", exception);
		return responseEntity;
	}

}
