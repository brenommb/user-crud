package com.user.interfaces.incoming.errorhandler;

import com.user.domain.common.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Locale;

@Slf4j
public class NotFoundExceptionErrorHandler extends ExceptionErrorHandler<NotFoundException> {

	private NotFoundExceptionErrorHandler(final MessageSource messageSource, final Locale locale) {
		super(messageSource, locale);
	}

	public static ResponseEntity build(final NotFoundException exception, final MessageSource messageSource,
			final Locale locale) {
		return new NotFoundExceptionErrorHandler(messageSource, locale).build(exception);
	}

	@Override
	protected ResponseEntity build(final NotFoundException exception) {
		final List<ErrorMessage> errorMessages = getErrorMessages(exception.getCode(), exception.getArgs());
		ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<>(new ErrorResponse(errorMessages),
				NotFoundException.statusCode);
		log.error("Not found error: {}", responseEntity);
		log.error("Error detail", exception);
		return responseEntity;
	}

}
