package oasis;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import oasis.aws.API;
import oasis.test.web.SampleJsonVO;

@Slf4j
public class RestTemplateTest {

	@Test
	public void test() {
/*		
		String url = "https://www.daum.net";
		RestTemplate restTemplate = new RestTemplate();
		
        HttpHeaders headers = new HttpHeaders();       
//        headers.add("Authentication", key);
        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
				
		System.out.println(result);	*/
	}
		
	
	@Test
	public void jsontest() {
        /*
         */
//		String url = "http://www.me.go.kr/web/linkAir.do?rowCnt=3&gubun=53";
		
		String url = "http://apis.data.go.kr/1360000/VilageFcstInfoService/getUltraSrtNcst?base_date=20211020&base_time=0900&nx=60&ny=127&ServiceKey=et6JUMCjenREtG%2Be3bdc5ee2UWgFH5m4%2FB%2FOKexqk4dMSgRVec2wewguK827Y3KfNWhExoUJROL3wviwB5yyFQ%3D%3D";
		
		RestTemplate restTemplate = new RestTemplate();
        		
        HttpHeaders headers = new HttpHeaders();       
        headers.setContentType(new MediaType("application","XML",Charset.forName("UTF-8")));    //Response Header to UTF-8
        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(headers);
        
//        Object response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, PublishOrgInfo.class);
        
        Object response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, API.class);
				
		System.out.println(response);
	}
	
	
	@Test
	public void xmltest() {
		
//		String url = "http://www.me.go.kr/web/linkAir.do?rowCnt=3&gubun=53";
		
		/* apache 4.3 이상 요구 - tomcat 7 사용 불가
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(10000); //타임아웃 설정 5초
        factory.setReadTimeout(10000);//타임아웃 설정 5초
        RestTemplate restTemplate = new RestTemplate(factory);
		 */
		/*
		RestTemplate restTemplate = new RestTemplate();
		PublishOrgInfo publishOrgInfo = restTemplate.getForObject(url, PublishOrgInfo.class);
		
		List<PublishOrgResult> result= publishOrgInfo.getPublishOrgResultInfo().getPublishOrgResult();
		
	    Assert.assertEquals("1429650", result.get(0).getContentId());
		 */ 
	}
	
	
	@Test
	public void restApiSample () {
		String BASE_URL = "http://localhost:28080/test/getRestSample/{id}";		
		RestTemplate restTemplate = new RestTemplate();		

		//1. GET을 수행하고 HTTP 응답을 객체 타입으로 변환해서 반환해주는 메서드 이용
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("id", "hgd");
		SampleJsonVO sampleJson = restTemplate.getForObject(BASE_URL, SampleJsonVO.class, uriVariables);
        log.info("sampleJson: {}", sampleJson);	
                
        //2. getForObject()와 달리 HTTP 응답에 대한 추가 정보를 담고 있어서 GET 요청에 대한 응답 코드, 실제 데이터를 확인할 수 있습니다
        //	 리턴형식이 문자열로 넘어온다.
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("id", "Frank Oh");    
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(BASE_URL, String.class, params);
        log.info("statusCode: {}", responseEntity.getStatusCode());
        log.info("getBody: {}", responseEntity.getBody());
		
        /*
        //3. POST 요청에 대해서 반환 값을 해당 객체로 반환해주는 메서드 
        HttpHeaders headers1 = new HttpHeaders();
        headers1.set("headerTest", "headerValue");
        HttpEntity<SampleJsonVO> request = new HttpEntity<>(new SampleJsonVO(), headers1);
        SampleJsonVO sampleJson2 = restTemplate.postForObject(BASE_URL, request, SampleJsonVO.class);
        log.info("sampleJson2 : {}", sampleJson2);          
        
        //4. ResponseEntity<T> 객체로 데이터를 받을 수 있습니다.
		ResponseEntity<String> responseEntity2 = restTemplate.postForEntity(BASE_URL, new SampleJsonVO(), String.class);
		log.info("statusCode: {}", responseEntity2.getStatusCode());
		log.info("getBody: {}", responseEntity2.getBody());
        */  
        
        
        /*
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>("Hello World!", headers);
        log.info("request: {}", request);
      
        ResponseEntity<SampleJsonVO> empEntity = restTemplate.exchange(BASE_URL, HttpMethod.GET, request, SampleJsonVO.class);
        log.info("empEntity: {}", empEntity);
        */
                
        
        //5. head 수정이 필요시 exchange 이용
        HttpHeaders headers = new HttpHeaders();
        // headers.add("AUTHKEY", "myKey");
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));    //Response Header to UTF-8                
        HttpEntity<Map<String, String>> httpEntity = new HttpEntity<>(headers);
        Object response = restTemplate.exchange(BASE_URL, HttpMethod.GET, httpEntity, SampleJsonVO.class, "kms");		
        log.info("response: {}", response);
        
                
        // 리스트 형태의 객체 목록을 얻으려면 ResponseEntity와 ParameterizedTypeReference 객체를 사용하면 됩니다. 
        // ParameterizedTypeReference 객체를 사용하면 응답을 Class 대신 제네릭한 타입을 지정할 수 있습니다
        ResponseEntity<List<Map<String, Object>>> resEntity 
        		= restTemplate.exchange("http://localhost:28080/test/getRestSamplelist", 
        								HttpMethod.GET, null, 
        								new ParameterizedTypeReference<List<Map<String, Object>>>(){});
        
        log.info("responseEntity: {}", resEntity);        
        List<Map<String, Object>> list = resEntity.getBody();        
        log.info("list : {}", list);		
	}
	
}
