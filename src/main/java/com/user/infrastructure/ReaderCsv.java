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
import java.util.Collections;
import java.util.List;

@Slf4j
@UtilityClass
public class ReaderCsv {

	private static final char SEMICOLON = ';';

	private static final String TEMP = "tempFile.csv";

	public static <T> List<T> loadObjectList(Class<T> type, String fileName) {
		String lineError = "";
		CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator(SEMICOLON);
		CsvMapper mapper = new CsvMapper();
		mapper.enable(CsvParser.Feature.SKIP_EMPTY_LINES);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

		try {
			File file = new ClassPathResource(fileName).getFile();
			MappingIterator<T> readValues = mapper.readerFor(type).with(bootstrapSchema).readValues(file);
			return readValues.readAll();
		}
		catch (InvalidFormatException e) {
			log.error("Error occurred by invalid format from file " + fileName + "msg: " + e.getMessage());
			lineError = e.getValue().toString();

		}
		catch (Exception e) {
			log.error("Error occurred while loading object list from file " + fileName + "msg: " + e.getMessage());
			return Collections.emptyList();
		}

		try {
			File file = new ClassPathResource(fileName).getFile();
			File tempFile = new File(TEMP);

			// Try writing it back out as CSV to the console
			CSVReader reader2 = new CSVReader(new FileReader(file));
			List<String[]> allElements = reader2.readAll();
			allElements.remove(7);

			FileOutputStream fileOutputStream = new FileOutputStream(file);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);

			CSVWriter writer = new CSVWriter(outputStreamWriter);
			writer.writeAll(allElements);
			writer.close();

		}
		catch (Exception e) {
			log.error("Error occurred while deleting object list from file " + fileName + "msg: " + e.getMessage());
		}
		return Collections.emptyList();
	}

}
