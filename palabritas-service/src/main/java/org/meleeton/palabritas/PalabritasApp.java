package org.meleeton.palabritas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@EnableConfigurationProperties
@ComponentScan({"org.meleeton.palabritas"})
public class PalabritasApp {
	/**
	 * Startup method to run the service. This method starts up embedded Tomcat and publishes services.
	 * @param args
	 */
    public static void main(String[] args) {
        SpringApplication.run(PalabritasApp.class, args);
    }
}
