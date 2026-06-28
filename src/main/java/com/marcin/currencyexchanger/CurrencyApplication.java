package com.marcin.currencyexchanger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class CurrencyApplication {

	public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);

        SpringApplication.run(CurrencyApplication.class, args);
	}

}
