package com.PurchaseOrder.PurchaseOrderApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.PurchaseOrder.PurchaseOrderApi.model.entity"})
public class PurchaseOrderApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PurchaseOrderApiApplication.class, args);
	}

}
