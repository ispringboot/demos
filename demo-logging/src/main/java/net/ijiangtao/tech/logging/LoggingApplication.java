package net.ijiangtao.tech.logging;

import net.ijiangtao.tech.logging.api.httplog.aspect.HttpLogAspectV1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LoggingApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoggingApplication.class, args);
    }


    @Bean
    public HttpLogAspectV1 httpLogAspect() {
        return new HttpLogAspectV1();
    }

}

