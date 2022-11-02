package com.capmtraining;

import org.apache.olingo.odata2.api.ODataServiceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import com.capmtraining.annotation.processor.MyODataServiceFactory;
import com.capmtraining.entities.VendorODataAgent;

@SpringBootApplication(scanBasePackages = "com.capmtraining")
@EnableJpaRepositories(basePackages = "com.capmtraining")
@EntityScan(basePackages = "com.capmtraining") 
@ServletComponentScan(basePackages = "com.capmtraining")
@EnableAsync
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean(name="com.capmtraining.processor.MyODataServiceFactory")
	public ODataServiceFactory getServiceFactory(){
		return new MyODataServiceFactory("com.capmtraining");
	}
	
	@Bean(name="com.capmtraining.entities.VendorODataAgent")
	public VendorODataAgent vendorODataAgent(){
		return new VendorODataAgent();
	}
}
