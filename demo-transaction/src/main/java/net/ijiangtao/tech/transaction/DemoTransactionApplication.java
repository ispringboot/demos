package net.ijiangtao.tech.transaction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("net.ijiangtao.tech.transaction.mybatis.mapper")
public class DemoTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoTransactionApplication.class, args);
	}

}
