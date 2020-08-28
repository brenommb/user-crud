package com.user.infrastructure;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@UtilityClass
public class CsvAdapter {

	private static final char SEMICOLON = ';';

	private static final String TEMP_PATH = "src/main/resources/tmp";

	private static final String RESOURCES_PATH = "src/main/resources/";

	public static <T> List<T> loadObjectList(Class<T> type, String fileName) throws IOException {
		CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator(SEMICOLON);
		CsvMapper mapper = new CsvMapper();
		mapper.enable(CsvParser.Feature.SKIP_EMPTY_LINES);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		try {
			File file = new File(RESOURCES_PATH.concat(fileName));
			MappingIterator<T> readValues = mapper.readerFor(type).with(bootstrapSchema).readValues(file);
			List<T> objectList = readValues.readAll();
			return objectList;
		}
		catch (InvalidFormatException e) {
			log.error("Error occurred by invalid format from file " + fileName + "msg: " + e.getMessage());
			removeInvalidLine(fileName, e.getValue().toString());
			return loadObjectList(type, fileName);
		}
	}

	private static void removeInvalidLine(String fileName, String lineError) throws IOException {
		File file = new File(RESOURCES_PATH.concat(fileName));
		File tempFile = new File(TEMP_PATH.concat(fileName));

		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

		String currentLine;

		while((currentLine = reader.readLine()) != null) {
			String trimmedLine = currentLine.trim();
			if(trimmedLine.equals(lineError)) continue;
			writer.write(currentLine + System.getProperty("line.separator"));
		}
		writer.close();
		reader.close();

		tempFile.renameTo(file);
	}

	public static void deleteFile(String filename) {
		File file = new File(RESOURCES_PATH.concat(filename));
		file.delete();
	}
}
