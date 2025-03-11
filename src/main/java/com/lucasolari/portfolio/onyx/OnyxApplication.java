package com.lucasolari.portfolio.onyx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OnyxApplication {

	public static void main(String[] args) {

		ApplicationContext context = (ApplicationContext)SpringApplication.run(OnyxApplication.class, args);
	}
}

