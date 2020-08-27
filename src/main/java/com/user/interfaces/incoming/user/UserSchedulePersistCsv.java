package com.user.interfaces.incoming.user;

import com.user.domain.user.UserService;
import com.user.infrastructure.ReaderCsv;
import com.user.interfaces.incoming.user.mapping.UserMapper;
import com.user.interfaces.incoming.user.request.UserCsv;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class UserSchedulePersistCsv {

    private static final long FIXED_DELAY = 30000;

    private static final String USERS_FILENAME = "insert_users.csv";

    private final UserService userService;

    private final UserMapper userMapper;


    @Scheduled(fixedDelay = FIXED_DELAY)
    public void scheduleUserInsert() {
        List<UserCsv> usersCsv = getUsersCsv();
        usersCsv.forEach(csv ->
                userService.persistUser(userMapper.mapCsvToEntity(csv))
        );
    }

    private static List<UserCsv> getUsersCsv() {
        return ReaderCsv.loadObjectList(UserCsv.class, USERS_FILENAME);
    }
}
