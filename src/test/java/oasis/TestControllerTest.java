package oasis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import oasis.test.service.SqlsessionTestService;

@ContextConfiguration
(
	{		
		"file:src/main/resources/egovframework/spring/context-aspect.xml",
		"file:src/main/resources/egovframework/spring/context-common.xml",
		"file:src/main/resources/egovframework/spring/context-datasource.xml",	
		"file:src/main/resources/egovframework/spring/context-idgen.xml",
		"file:src/main/resources/egovframework/spring/context-mapper.xml",		
		"file:src/main/resources/egovframework/spring/context-properties.xml",
		"file:src/main/resources/egovframework/spring/context-security.xml",
		"file:src/main/resources/egovframework/spring/context-mongodb.xml",
		"file:src/main/resources/egovframework/sqlmap/config/tibero/sql-mapper-config.xml",		
		"file:src/main/webapp/WEB-INF/config/egovframework/springmvc/dispatcher-servlet.xml"
	}
)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestControllerTest {
	
	@Resource(name = "sqlsessionTestService")
	private SqlsessionTestService sqlsessionTestService;
	
	@Test
	public void testTestSqlSession() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
		try {
			sqlsessionTestService.createData(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	@Test
	public void testTestSqlSessionBatch() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
		try {
			sqlsessionTestService.createDataBatch(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
