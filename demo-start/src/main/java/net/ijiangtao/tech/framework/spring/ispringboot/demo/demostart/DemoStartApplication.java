package net.ijiangtao.tech.framework.spring.ispringboot.demo.demostart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;

@SpringBootApplication(exclude = {RedisAutoConfiguration.class, SolrAutoConfiguration.class})
public class DemoStartApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoStartApplication.class, args);
	}

}

