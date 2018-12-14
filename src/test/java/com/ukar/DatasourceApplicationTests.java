package com.ukar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.ukar.*"})
public class DatasourceApplicationTests {

	public static void main(String[] args) {
		SpringApplication.run(DatasourceApplicationTests.class, args);
	}

}
