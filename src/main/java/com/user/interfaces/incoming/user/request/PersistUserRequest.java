package com.user.interfaces.incoming.user.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersistUserRequest {

	@NotNull(message = "mandatory_field")
	private Long companyId;

	@NotEmpty(message = "mandatory_field")
	private String email;

	@JsonProperty("birthdate")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "mandatory_field")
	private LocalDate birthDate;

}
