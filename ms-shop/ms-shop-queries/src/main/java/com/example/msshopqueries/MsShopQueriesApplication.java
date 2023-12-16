package com.example.msshopqueries;

import com.example.coreapis.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConfig.class})
@EnableFeignClients
public class MsShopQueriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsShopQueriesApplication.class, args);
	}

}
