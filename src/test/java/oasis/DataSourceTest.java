package oasis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.mybatis.spring.SqlSessionFactoryBean;

@RunWith(MockitoJUnitRunner.class)
public class DataSourceTest {
	
	@Test
	public void mybatisSqlsessionTest1() {		
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
//		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:mybatis/mapper/**/*.xml"));
//		sqlSessionFactoryBean.setTypeAliasesPackage("**.vo");
		SqlSessionFactory sqlSessionFactory = null;
		SqlSession sqlSession = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
        ResultSet rs = null;   
        List<Map> list = new ArrayList<Map>();
		try {		
			sqlSessionFactory = sqlSessionFactoryBean.getObject();			
			sqlSession = sqlSessionFactory.openSession();
			conn = sqlSession.getConnection();
			
			StringBuilder sql = new StringBuilder("");
			sql.append("select * from cm_user");			   
			pstmt = conn.prepareStatement(sql.toString());			
			rs = pstmt.executeQuery();
			
			while(rs.next()) { 
            	Map<String, String> item = new HashMap();
                item.put("USER_ID", rs.getString(1));
                item.put("USER_NM", rs.getString(2));            	
                list.add(item);
            }			
			System.out.println("list >> " + list);
			
		} catch (Exception e) {			
			e.printStackTrace();
	    } finally {	    	
			if (rs != null) {
				try {rs.close();} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {pstmt.close();} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {conn.close();} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			sqlSession.flushStatements();
			sqlSession.clearCache();
			sqlSession.close();
	    }	
	}
}
