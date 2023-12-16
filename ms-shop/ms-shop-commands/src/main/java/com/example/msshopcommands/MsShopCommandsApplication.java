package com.example.msshopcommands;

import com.example.coreapis.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConfig.class})
@EnableFeignClients
public class MsShopCommandsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsShopCommandsApplication.class, args);
	}

}
