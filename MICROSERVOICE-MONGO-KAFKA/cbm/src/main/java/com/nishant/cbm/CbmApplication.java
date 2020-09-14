package com.nishant.cbm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.nishant")
public class CbmApplication {

	public static void main(String[] args) {
		SpringApplication.run(CbmApplication.class, args);
	}

}
