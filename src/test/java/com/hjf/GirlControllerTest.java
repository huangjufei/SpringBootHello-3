package com.hjf;

import javax.servlet.ServletContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.hjf.controller.GirlController;
import com.hjf.domain.Girl;

/**
 * 知识点
 * 
 * @AutoConfigureMockMvc 可以模拟postMain 发起get或post ,让测试在controller层
 * @WebMvcTest(controllers = GirlController.class)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class GirlControllerTest {

	@Autowired
	private GirlController girlController;

	private MockMvc mockMvc;

	@Autowired
	ServletContext context;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(girlController).build();
	}

	@Test
	public void girlList1() throws Exception {
		girlController.getGirlList();
	}

	@Test
	public void addGirls() throws Exception {
		//mockMvc.perform(MockMvcRequestBuilders.post("/girls"));
		
		Girl girl = new Girl();
		girl.setAge(11);
		mockMvc.perform(MockMvcRequestBuilders.post("/girls", girl));
	
	}
	
	@Test
	public void girlList() throws Exception {

		mockMvc.perform(MockMvcRequestBuilders.get("/girls")).andExpect(MockMvcResultMatchers.status().isOk());
		// .andExpect(MockMvcResultMatchers.content().string("abc")) 希望返回值是 abc
	}
}