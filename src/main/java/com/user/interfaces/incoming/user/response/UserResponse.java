package com.user.interfaces.incoming.user.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {

	Long userId;

	Long companyId;

	String email;

	@JsonProperty("birthdate")
	@JsonFormat(pattern = "dd/MM/yyyy")
	LocalDate birthDate;

}
