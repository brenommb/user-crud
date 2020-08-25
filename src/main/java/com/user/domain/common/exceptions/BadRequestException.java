package com.user.domain.common.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@ToString
public class BadRequestException extends RuntimeException {

	public static final HttpStatus statusCode = HttpStatus.BAD_REQUEST;

	private final String code;

	private final List<String> args;

	public BadRequestException(final String code) {
		this.code = code;
		this.args = null;
	}

	public BadRequestException(final String code, final List<String> args) {
		this.code = code;
		this.args = args;
	}

}
