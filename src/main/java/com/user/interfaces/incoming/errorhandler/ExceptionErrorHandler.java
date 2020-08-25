package com.user.interfaces.incoming.errorhandler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static com.user.infrastructure.MessageCode.UNEXPECTED_INTERNAL;

@RequiredArgsConstructor
public abstract class ExceptionErrorHandler<T> {

	private static final String INFO = "_info";

	private final MessageSource messageSource;

	private final Locale locale;

	protected abstract ResponseEntity build(final T exception);

	String getMessage(final String key, final String... args) {
		return messageSource.getMessage(Optional.ofNullable(key).orElse(UNEXPECTED_INTERNAL), args, locale);
	}

	String getInfoMessage(final String key, final String... args) {
		return messageSource.getMessage(Optional.ofNullable(key).orElse(UNEXPECTED_INTERNAL).concat(INFO), args,
				locale);
	}

	List<ErrorMessage> getErrorMessages(final String key, final List<String> args) {
		return Collections.singletonList(ErrorMessage.builder().code(key)
				.message(messageSource.getMessage(key,
						Optional.ofNullable(args).orElse(Collections.emptyList()).toArray(), locale))
				.info(messageSource.getMessage(key.concat(INFO),
						Optional.ofNullable(args).orElse(Collections.emptyList()).toArray(), locale))
				.build());
	}

}
