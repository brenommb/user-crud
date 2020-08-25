package com.user;

import com.user.infrastructure.config.Profile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import java.net.InetAddress;

@SpringBootApplication
@Slf4j
public class UserApplication {

	private static final String ACCESS_URLS_MESSAGE_LOG = "\n\n Access URLs:\n----------------------------------------------------------\n\t External: \thttp://{}:{}/swagger-ui.html# Profiles: {}\n----------------------------------------------------------\n";

	public static void main(final String[] args) {
		try {
			System.setProperty("spring.devtools.restart.enabled", "false");
			final SpringApplication app = new SpringApplication(UserApplication.class);
			final SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
			addDefaultProfile(app, source);
			final Environment env = app.run(args).getEnvironment();
			log.info(ACCESS_URLS_MESSAGE_LOG, InetAddress.getLocalHost().getHostAddress(),
					env.getProperty("server.port"), env.getActiveProfiles());

		}
		catch (Exception e) {
			log.error("Start Error.", e);
		}

	}

	private static void addDefaultProfile(final SpringApplication app, final SimpleCommandLinePropertySource source) {
		if (!source.containsProperty("spring.profiles.active")
				&& !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {
			app.setAdditionalProfiles(Profile.SPRING_PROFILE_DEFAULT);
		}
	}

}
