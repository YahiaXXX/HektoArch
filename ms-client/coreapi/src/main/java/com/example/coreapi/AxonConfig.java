package com.example.coreapi;

import com.thoughtworks.xstream.XStream;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration

public class AxonConfig {
    @Bean
    public XStream xStream() {
        XStream xStream = new XStream();

        xStream.allowTypesByWildcard(new String [] {
                "com.example.coreapi.**"
        });
        return xStream;


    }

//@Bean
//public XStreamConfigurer xStreamConfigurer() {
//    return new XStreamConfigurer() {
//        @Override
//        public void configure(XStream xStream) {
//            xStream.allowTypesByWildcard(new String[]{
//                    "com.example.coreapi.**"
//            });
//        }
//    };
//}
}
