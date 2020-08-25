package com.user.domain.common.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonUtils {

	public static void verifyNullArgs(final Object... objects) {
		for (Object obj : objects) {
			if (null == obj) {
				throw new IllegalArgumentException();
			}
		}
	}

}
