package com.MBD.CabBooking;

import javax.swing.text.PasswordView;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CabBookingApplication {

	public static void main(String[] args) {
		SpringApplication.run(CabBookingApplication.class, args);
	}
	
//	@Bean
//	public PasswordView passwordEncoder() {
//	    return new BCryptPasswordEncoder();
//	}

}
