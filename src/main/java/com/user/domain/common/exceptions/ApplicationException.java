package com.user.domain.common.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@ToString
public class ApplicationException extends RuntimeException {

	private final String code;

	private final HttpStatus statusCode;

	private final List<String> args;

	public ApplicationException(final String code, final HttpStatus statusCode) {
		super(code);
		this.code = code;
		this.statusCode = statusCode;
		this.args = null;
	}

	public ApplicationException(final String code, final HttpStatus statusCode, final List<String> args) {
		super(code);
		this.code = code;
		this.statusCode = statusCode;
		this.args = args;
	}

}
