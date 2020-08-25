package com.user.interfaces.incoming.errorhandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
public class MethodArgumentNotValidExceptionErrorHandler
		extends ExceptionErrorHandler<MethodArgumentNotValidException> {

	private MethodArgumentNotValidExceptionErrorHandler(final MessageSource messageSource, final Locale locale) {
		super(messageSource, locale);
	}

	public static ResponseEntity build(final MethodArgumentNotValidException exception,
			final MessageSource messageSource, final Locale locale) {
		return new MethodArgumentNotValidExceptionErrorHandler(messageSource, locale).build(exception);
	}

	@Override
	protected ResponseEntity build(final MethodArgumentNotValidException exception) {

		log.error("Validation error", exception);

		List<ErrorMessage> errorResponseList = new ArrayList<>();
		exception.getBindingResult().getFieldErrors()
				.forEach(fieldError -> errorResponseList.add(ErrorMessage.builder().code(fieldError.getDefaultMessage())
						.message(super.getMessage(fieldError.getDefaultMessage(), fieldError.getField()))
						.info(super.getInfoMessage(fieldError.getDefaultMessage(), fieldError.getField())).build()));
		return ResponseEntity.badRequest().body(new ErrorResponse(errorResponseList));
	}

}
