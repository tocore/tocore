/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package oasis.test.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.support.WebApplicationContextUtils;

import lombok.extern.slf4j.Slf4j;
import oasis.core.annotation.DescriptionParametes;
import oasis.member.service.MemberService;
import oasis.test.service.SqlsessionTestService;
/**
 * @Class Name : EgovSampleController.java
 * @Description : EgovSample Controller Class
 * @Modification Information
 * @
 * @  수정일      수정자              수정내용
 * @ ---------   ---------   -------------------------------
 * @ 2009.03.16           최초생성
 *
 * @author 개발프레임웍크 실행환경 개발팀
 * @since 2009. 03.16
 * @version 1.0
 * @see
 *
 *  Copyright (C) by MOPAS All right reserved.
 */

@Slf4j
@Controller
@RequestMapping("/test")
@DescriptionParametes
public class TestController {
	
	@Resource(name="mongoTemplate")	
	MongoTemplate mongoTemplate;
	
//	@Resource
	private ZSetOperations<String, String> zSetOperations;
	
	@Autowired
	private ApplicationContext appContext;	
	
	@Resource(name = "sqlsessionTestService")
	private SqlsessionTestService sqlsessionTestService;
	
	@RequestMapping(value = "/putRedis", method = {RequestMethod.POST, RequestMethod.GET})
	public void exportExcelFromRedis() throws Exception {		
		try {
			zSetOperations.add("test:user:kingbbode:wish", "배포한 것에 장애없길", 1); 
			zSetOperations.add("test:user:kingbbode:wish", "배포한거 아니여도 장애없길", 2); 
			zSetOperations.add("test:user:kingbbode:wish", "경력직 채용", 3); 
			zSetOperations.add("test:user:kingbbode:wish", "잘자기", 4);			
		} catch (Exception e) {
			log.debug("redis 오류 ", e);
		}		
	}
	
	@RequestMapping(value = "/getRedis", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Map<String, Object> getRedis(@RequestBody Map<String, Object> params) throws Exception {		
		StringBuilder stringBuilder = new StringBuilder();		
		try {
			Set<String> wishes = zSetOperations.range("test:user:kingbbode:wish", 0, 2); 
			
			stringBuilder.append("\n******소망소개******"); 
			int rank = 1; 
			for (String wish : wishes){ 
				stringBuilder.append("\n"); 
				stringBuilder.append(rank); 
				stringBuilder.append("등 "); 
				stringBuilder.append(wish); 
				rank++; 
			}
		} catch (Exception e) {
			log.debug("redis 오류 ", e);
		}
		
		params.put("reulst", stringBuilder.toString());
		return params;
	}
	
	
	@RequestMapping(value = "/getResttemplate", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Map<String, Object> getResttemplate(@RequestBody Map<String, Object> params) throws Exception {	
		
		String url = "http://www.me.go.kr/web/linkAir.do?rowCnt=3&gubun=53";
		
		RestTemplate restTemplate = new RestTemplate();
		PublishOrgInfo publishOrgInfo = restTemplate.getForObject(url, PublishOrgInfo.class);
				
		List<PublishOrgResult> resultlist= publishOrgInfo.getPublishOrgResultInfo().getPublishOrgResult();
		
		System.out.println(resultlist.get(0).getContentId());
        
        params.put("result", resultlist);
		return params;
	}
		
	@RequestMapping(value = "/getRestSample/{id}", method = {RequestMethod.POST, RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public SampleJsonVO getRestSample(@PathVariable String id) {
		
		System.err.println("id >>> " + id);
		
		SampleJsonVO sample = new SampleJsonVO();
		sample.setId(id);		
		
		/*
		Employee employee = Employee.builder()
	         .id(id)
	         .name("Frank Oh")
	         .gender(Gender.FEMALE)
	         .address(Address.builder()
	               .street("123동 135호 파란아마트")
	               .city("Seoul")
	               .postalCode("1234")
	               .build())
	         .build();
	   	*/		
		return sample;
	}
	
	@RequestMapping(value = "/getRestSamplelist", method = {RequestMethod.POST, RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Map<String, Object>> getRestSamplelist() {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> m1 = new HashMap<String, Object>();
		m1.put("id", "id test1");
		
		Map<String, Object> m2 = new HashMap<String, Object>();
		m2.put("id", "id test2");		
		
		list.add(m1);
		list.add(m2);
		
		return list;
	}
	
	
	// factory 생성 하기
	public void createSqlsessionFactory () {
//		@Resource(name="sqlSession")	
//		protected SqlSessionFactory sessionid;

//		SqlSession session = sessionid.openSession(ExecutorType.SIMPLE);
//		session.selectOne("MemberMapper.exceldownload");		
		
	}
	
	
	@RequestMapping(value = "/getMongoSamplelist", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Map<String , Object> getMongoSamplelist() throws Exception {	
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("list", mongoTemplate.findAll(Fruit.class, "fruit"));
		map.put("result", "ok");		
		return map;
	}

	@RequestMapping(value = "/appendMongo", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Map<String , String> appendMongo() throws Exception {
				
		Fruit fruit = new Fruit();
		fruit.setName("Jamong");
		fruit.setPrice(5000);
		
		Taste taste = new Taste();
		ArrayList<String> tastes = new ArrayList<String>();
		tastes.add("bitter");
		tastes.add("sweet");
		taste.setTastes(tastes);
		
		String[] testing = {"testing1", "testing2"};
		taste.setTesting(testing);			
		fruit.setTaste(taste);			
		mongoTemplate.insert(fruit, "fruit");
	
		Map<String , String> map = new HashMap<String , String>();
		map.put("result", "ok");
		return map;		
	}
	
	@RequestMapping(value = "/modifyMongo", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Map<String , String> modifyMongo() throws Exception {		

		String id = "6073aa0632bc955b20eb3410";
        Query query = new Query(new Criteria("_id").is(id));
      
        Update update = new Update();
        update.set("name", "홍길동");
        update.set("price", 2500);
      
        mongoTemplate.updateMulti(query, update, "fruit");
        
		Map<String , String> map = new HashMap<String , String>();
		map.put("result", "ok");
		return map;		
	}
	
	@RequestMapping(value = "/removeMongo", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Map<String , String> removeMongo() throws Exception {
		String id = "6073fac532bc9527c878e29a";		
		Query query = new Query(new Criteria("_id").is(id));
		mongoTemplate.remove(query,"fruit");
		
		Map<String , String> map = new HashMap<String , String>();
		map.put("result", "ok");
		return map;		
	}
		
	/**
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getContext")
	public @ResponseBody Map<String , String> getContext() throws Exception {	
		MemberService mb = (MemberService) appContext.getBean("memberService");
		System.out.println("getContext : " + mb);
		
		Map<String , String> map = new HashMap<String , String>();
		map.put("result", "ok");
		return map;	
	}
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getContext2")
	public @ResponseBody Map<String , String> getContext2(HttpServletRequest request) throws Exception {
		
		ApplicationContext applicationContext = null;	
		
	    if (applicationContext == null){	    	
	        applicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
			MemberService mb = (MemberService) applicationContext.getBean("memberService");
			System.out.println("getContext : " + mb);			
	    }
	    
		Map<String , String> map = new HashMap<String , String>();
		map.put("result", "ok");
		return map;	
	}
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/getResource")
	public @ResponseBody Map<String , String> getResource(HttpServletRequest request) throws Exception {
		
		ApplicationContext ctx = null;	
		Resource resource = null;
		MessageSource messageSource = null;
		
	    if (ctx == null) {	    	
	    	ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());	    
	        try {				
	        	resource = (Resource) ctx.getResource("classpath:egovframework/properties/globals.properties");	        	
	        
	        	System.out.println("getEnvironment : " + ctx.getEnvironment());
	        	System.out.println("getMessage : " + ctx.getMessage((MessageSourceResolvable) messageSource, Locale.KOREA));
	        	System.out.println("getEnvironment : " + ctx.getEnvironment());
	        	System.out.println("getEnvironment : " + ctx.getEnvironment());	        	
	        	System.out.println("getResource : " + resource.description());
	        	System.out.println("getResource : " + resource.toString());			

	        } catch (Exception e) {
				System.out.println(e.getLocalizedMessage());
			}	        
	    }
	    
		Map<String , String> map = new HashMap<String , String>();
		map.put("result", "ok");
		return map;	
	}
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/drawCanvas")
	public String drawCanvas(HttpServletRequest request) throws Exception {		
		return "example/canvas/canvasView";	
	}
	
	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/testSqlSession")
	public @ResponseBody Map<String , String> testSqlSession(HttpServletRequest request) throws Exception {
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();		
		try {
			sqlsessionTestService.createData(list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		Map<String , String> map = new HashMap<String , String>();
		map.put("result", "ok");
		return map;	
	}

}
