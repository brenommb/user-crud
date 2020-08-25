package com.user.utils;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;

public class JsonUtils {

	private static final String FILE_NAME_TEMPLATE = "classpath:json/%s.json";

	public static String readJson(String fileName) {
		File file;
		try {
			file = ResourceUtils.getFile(String.format(FILE_NAME_TEMPLATE, fileName));
			return new String(Files.readAllBytes(file.toPath()));
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return "";
	}

}
