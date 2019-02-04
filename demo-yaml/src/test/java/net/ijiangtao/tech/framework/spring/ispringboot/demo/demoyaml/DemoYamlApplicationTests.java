package net.ijiangtao.tech.framework.spring.ispringboot.demo.demoyaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.internal.JsonFormatter;
import net.ijiangtao.tech.framework.spring.ispringboot.demo.demoyaml.info.InterfaceInformation;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoYamlApplicationTests {

	private static final Logger LOGGER= LoggerFactory.getLogger(DemoYamlApplication.class);

	@Autowired
	private InterfaceInformation interfaceInformation;

	ObjectMapper mapper=new ObjectMapper();

	@Test
	public void testInterfaceInformation() throws Exception{

		LOGGER.info(mapper.writeValueAsString(interfaceInformation));

	}

	@Test
	public void contextLoads() {

	}

}

