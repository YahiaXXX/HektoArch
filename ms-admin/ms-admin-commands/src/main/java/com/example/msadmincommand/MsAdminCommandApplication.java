package com.example.msadmincommand;

import com.example.coreapic.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConfig.class})
@EnableFeignClients

public class MsAdminCommandApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAdminCommandApplication.class, args);
	}

}
