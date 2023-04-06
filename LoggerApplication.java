package com.example.demo;

import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.google.common.flogger.FluentLogger;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LoggerApplication {

	private static final FluentLogger logger = FluentLogger.forEnclosingClass();

	public static void main(String[] args) {

		System.setProperty(
				"flogger.backend_factory", "com.google.common.flogger.backend.log4j.Log4jBackendFactory#getInstance");

		SpringApplication.run(LoggerApplication.class, args);
		logger.atInfo().log("Log message with: %s", "service started...");

	}

	@RequestMapping("/log")
	public String log(@RequestHeader("X-Log-Level") String logLevel){

		ThreadContext.put("X-Log-Level", logLevel);
		logger.atInfo().log("Info Message");
		logger.atWarning().log("Warning Message");
		logger.atSevere().log("Severe Message");
		logger.atFine().log("Fine Message");
		logger.atFiner().log("Finer Message");
		logger.atFinest().log("Finest Message");
		logger.atConfig().log("Config Message");
		return "logged";
	}

}
