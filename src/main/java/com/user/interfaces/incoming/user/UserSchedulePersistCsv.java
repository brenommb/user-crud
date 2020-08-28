package com.user.interfaces.incoming.user;

import com.user.domain.user.UserService;
import com.user.infrastructure.CsvAdapter;
import com.user.interfaces.incoming.user.mapping.UserMapper;
import com.user.interfaces.incoming.user.request.UserCsv;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class UserSchedulePersistCsv {

	private static final long FIXED_DELAY = 30000;

	private final UserService userService;

	private final UserMapper userMapper;

	@Scheduled(fixedDelay = FIXED_DELAY)
	public void scheduleUserInsert() {
		for (String filename : getCsvFileNameList()) {
			List<UserCsv> usersCsv = getUsersCsv(filename);
			usersCsv.forEach(csv -> {
				try {
					userService.persistUser(userMapper.mapCsvToEntity(csv));
				}
				catch (Exception ex) {
					log.error(ex.getMessage());
				}
			});
			CsvAdapter.deleteFile(filename);
		}

	}

	private static List<String> getCsvFileNameList() {
		Set<String> resources = new Reflections(new ResourcesScanner()).getResources(Pattern.compile(".*\\.csv"));
		return new ArrayList<>(resources);
	}

	private static List<UserCsv> getUsersCsv(final String filename) {
		try {
			return CsvAdapter.loadObjectList(UserCsv.class, filename);
		} catch (Exception e) {
			log.error("Error occurred while loading object list from file " + filename + "msg: " + e.getMessage());
		}
		return Collections.emptyList();
	}

}
