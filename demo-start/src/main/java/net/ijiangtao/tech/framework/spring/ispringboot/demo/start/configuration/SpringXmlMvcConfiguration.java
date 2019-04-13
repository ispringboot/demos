package net.ijiangtao.tech.framework.spring.ispringboot.demo.start.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(locations={"classpath:config/springmvc.xml"})
public class SpringXmlMvcConfiguration {
}
