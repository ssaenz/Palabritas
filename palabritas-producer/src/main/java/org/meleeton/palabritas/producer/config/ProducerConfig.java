package org.meleeton.palabritas.producer.config;

import java.util.Arrays;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.Interceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.common.gzip.GZIPInInterceptor;
import org.apache.cxf.transport.common.gzip.GZIPOutInterceptor;
import org.meleeton.palabritas.producer.resources.HelloResource;
import org.meleeton.palabritas.producer.resources.WordResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Configuration
@ComponentScan({"org.meleeton.palabritas.producer.impl"})
public class ProducerConfig {

	@Autowired
    private Bus bus;
	
	@Autowired
	private HelloResource helloResource;
	
	@Autowired
	private WordResource wordResource;
	
    @Bean
    public Server rsServer() {
        JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
        endpoint.setBus(bus);
        endpoint.setServiceBeans(Arrays.<Object>asList(helloResource, wordResource));
        
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(Include.NON_NULL);

        endpoint.setProvider(new JacksonJsonProvider(mapper));
        endpoint.setInInterceptors(Arrays.<Interceptor<? extends Message>>asList(new GZIPInInterceptor()));
        endpoint.setOutInterceptors(Arrays.<Interceptor<? extends Message>>asList(new GZIPOutInterceptor(0)));
        return endpoint.create();
    }
    
}
