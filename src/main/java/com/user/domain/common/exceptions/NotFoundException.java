package com.user.domain.common.exceptions;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Setter
@ToString
public class NotFoundException extends RuntimeException {

	private final String code;

	public static final HttpStatus statusCode = HttpStatus.NOT_FOUND;

	private final List<String> args;

	public NotFoundException(final String code) {
		this.code = code;
		this.args = null;
	}

	public NotFoundException(final String code, final List<String> args) {
		super(args.toString());
		this.code = code;
		this.args = args;
	}

}
