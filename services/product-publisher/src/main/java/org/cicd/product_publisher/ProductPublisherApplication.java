package org.cicd.product_publisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProductPublisherApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductPublisherApplication.class, args);
	}

}
