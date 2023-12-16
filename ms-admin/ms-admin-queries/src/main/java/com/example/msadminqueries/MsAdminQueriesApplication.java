package com.example.msadminqueries;

import com.example.coreapic.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConfig.class})
@EnableFeignClients

public class MsAdminQueriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAdminQueriesApplication.class, args);
	}

}
