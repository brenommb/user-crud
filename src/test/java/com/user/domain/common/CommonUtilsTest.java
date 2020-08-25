package com.user.domain.common;

import com.user.domain.common.utils.CommonUtils;
import org.junit.Test;

public class CommonUtilsTest {

	@Test
	public void should_be_verify_args_success() {
		CommonUtils.verifyNullArgs("teste");
	}

	@Test(expected = IllegalArgumentException.class)
	public void should_throw_args_null() {
		CommonUtils.verifyNullArgs(null, null);
	}

}
