package com.quakeparser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.quakeparser.service.GameService;

@SpringBootApplication
public class QuakeParserApplication {

	public static void main(String[] args) {
		// SpringApplication.run(ParserQuakeApplication.class, args);
		GameService.readFile();
	}

}
