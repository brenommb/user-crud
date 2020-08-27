package com.user.infrastructure;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.util.Collections;
import java.util.List;

@Slf4j
@UtilityClass
public class ReaderCsv {

    private static char SEMICOLON = ';';

    public static <T> List<T> loadObjectList(Class<T> type, String fileName) {
        try {
            CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator(SEMICOLON);
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<T> readValues =
                    mapper.readerFor(type).with(bootstrapSchema).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            log.error("Error occurred while loading object list from file " + fileName, e);
            return Collections.emptyList();
        }
    }
}
