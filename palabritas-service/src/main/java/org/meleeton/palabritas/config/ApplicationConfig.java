package org.meleeton.palabritas.config;

import org.meleeton.palabritas.producer.config.ProducerConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ServletConfig.class, ProducerConfig.class})
public class ApplicationConfig {
	
}
