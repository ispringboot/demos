package net.ijiangtao.tech.framework.spring.ispringboot.demo.retryer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DemoRetryerApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoRetryerApplication.class, args);
	}
}
