package com.user.infrastructure;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageErrorCode {

	public static final String USER_NOT_FOUND = "user_not_found";

	public static final String MANDATORY_FIELD = "mandatory_field";

	public static final String UNEXPECTED_INTERNAL = "unexpected_internal_error";

	public static final String EXTERNAL_INTEGRATION = "external_integration_error";

	public static final String EXCEPTION_NOT_FOUND = "exception_not_found_error";

	public static final String INTEGRATION_FAIL = "integration_fail";

	public static final String INVALID_ARGUMENT = "invalid_argument";

	public static final String DATABASE_ERROR = "database_error";

}