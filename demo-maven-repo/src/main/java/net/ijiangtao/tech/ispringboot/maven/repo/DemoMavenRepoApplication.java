package net.ijiangtao.tech.ispringboot.maven.repo;

import net.ijiangtao.tech.util.json.ResourceJsonUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoMavenRepoApplication {

	public static void main(String[] args) {

		//application.properties
		String applicationInfo=ResourceJsonUtil.getPrettyJsonStringFromPropertyFileByName("application");

		System.out.println(applicationInfo);

		SpringApplication.run(DemoMavenRepoApplication.class, args);
	}

}
