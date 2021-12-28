package oasis;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import egovframework.sample.service.SampleDefaultVO;
import egovframework.sample.service.impl.SampleMapper;

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
// @TestPropertySource("classpath:egovframework/egovProps/globals.properties")
public class EgovSampleServiceImplTest {

	@Autowired
	private ApplicationContext context;
	
	@Resource(name="sampleMapper")
	private SampleMapper sampleDAO;
	
	@Before
	public void setUp() {
		System.out.println(this.context);
		System.out.println(this);        
	}	
	
	@Test
	public void testSelectSampleList() {
		SampleDefaultVO searchVO = new SampleDefaultVO();
		try {
			sampleDAO.selectSampleList(searchVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
