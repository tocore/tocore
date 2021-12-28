package oasis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
		"file:src/main/resources/egovframework/sqlmap/config/mysql/sql-mapper-config.xml",		
		"file:src/main/webapp/WEB-INF/config/egovframework/springmvc/dispatcher-servlet.xml"
	}
)
@RunWith(SpringJUnit4ClassRunner.class)
public class sqlSsessionTest {
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Test
	public void mybatisSqlsessionTest2() {		
		BasicDataSource jdbc = new BasicDataSource();
		jdbc.setDriverClassName("net.sf.log4jdbc.DriverSpy");
		jdbc.setUrl("jdbc:mysql://localhost:3306/dev_test");
		jdbc.setUsername("root");
		jdbc.setPassword("autoset");
		jdbc.setValidationQuery("select 1 from dual");
		jdbc.setTestWhileIdle(true);
		jdbc.setTimeBetweenEvictionRunsMillis(600000);
		    
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(jdbc);
		
		try {
			sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:mybatis/mapper/**/*.xml"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//		sqlSessionFactoryBean.setTypeAliasesPackage("**.vo");
				
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession2 = null;
        List<Map> list = new ArrayList<Map>();
		
        try {		
			sqlSessionFactory = sqlSessionFactoryBean.getObject();			
			sqlSession2 = sqlSessionFactory.openSession();
			list = sqlSession2.selectList("memberMapper.exceldownload");

			System.out.println("list >> " + list);
			
		} catch (Exception e) {			
			e.printStackTrace();
	    } finally {	    	
	    	sqlSession2.flushStatements();
	    	sqlSession2.clearCache();
			sqlSession2.close();
	    }	
	}
}
