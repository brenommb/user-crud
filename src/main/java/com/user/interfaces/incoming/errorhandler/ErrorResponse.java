package com.user.interfaces.incoming.errorhandler;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ErrorResponse {

	@JsonProperty("errors")
	private List<ErrorMessage> errorMessages;

	public ErrorResponse(List<ErrorMessage> errorMessages) {
		this.errorMessages = errorMessages;
	}

}
