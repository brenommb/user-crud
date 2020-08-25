package com.user.infrastructure.config;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class Profile {

	public static final String SPRING_PROFILE_DEFAULT = Profile.SPRING_PROFILE_LOCAL;

	public static final String SPRING_PROFILE_DEV = "dev";

	public static final String SPRING_PROFILE_LOCAL = "local";

	public static final String SPRING_PROFILE_QA = "qa";

	public static final String SPRING_PROFILE_PROD = "prod";

	public static final String SPRING_PROFILE_CONTRACT_TEST = "contract-tests";

}
