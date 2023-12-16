package com.example.msclientcommands;

import com.example.coreapi.AxonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({AxonConfig.class})
@EnableFeignClients

public class MsClientCommandsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsClientCommandsApplication.class, args);
	}

}
