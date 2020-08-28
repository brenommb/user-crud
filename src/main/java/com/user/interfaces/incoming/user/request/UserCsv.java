package com.user.interfaces.incoming.user.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCsv {

	private Long companyId;

	private String email;

	private String birthdate;

}
