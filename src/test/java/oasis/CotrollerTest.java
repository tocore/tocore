package oasis;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import lombok.extern.slf4j.Slf4j;
import oasis.test.web.TestController;

@Slf4j
@RunWith(MockitoJUnitRunner.class)
public class CotrollerTest {
	
	@InjectMocks
	private TestController controller;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test	
	public void loginTest() throws Exception {
		// controller 가 상속을 받을시 hashcode, equals() 구현필요.
		MvcResult result = mockMvc.perform(get("/test/testmethode").accept(MediaType.TEXT_HTML))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().isOk())	// HTTP Response status 200 체크
			.andReturn();	
			// .andExpect(view().name("loginform"));
		String body = result.getResponse().getContentAsString();
		log.debug(body);
	}
}
