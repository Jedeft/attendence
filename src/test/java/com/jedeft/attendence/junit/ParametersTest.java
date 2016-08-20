package com.jedeft.attendence.junit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.codec.CharEncoding;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jedeft.attendence.base.utils.JSONUtils;
import com.jedeft.attendence.data.params.ParametersParams;


/**
 * 
 * @Description: 考勤参数单元测试
 * @author Jedeft
 * @date 2016年8月20日 下午8:19:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")  
@ContextHierarchy({  
        @ContextConfiguration(name = "parent", locations = "classpath:spring-mybatis.xml"),  
        @ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml")  
})  
public class ParametersTest {
	
	@Autowired  
    private WebApplicationContext wac;  
	
    private MockMvc mockMvc;
    
    @Before  
    public void setUp() {  
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  
    } 
	
    
    /**
     * url : /parameters
     * method : GET
     * @throws Exception
     */
	@Test
	public void insertTest() throws Exception{
		
		mockMvc.perform(get("/parameters").contentType(MediaType.TEXT_HTML)
				   .characterEncoding(CharEncoding.UTF_8)
				   .accept(MediaType.APPLICATION_JSON)
				   .characterEncoding(CharEncoding.UTF_8))
			  .andExpect(status().isOk())
			  .andDo(print());
	}
	
	/**
	 * url : /params
	 * method : PUT
	 * @throws Exception
	 */
	@Test
	public void selectOneTest() throws Exception{
		ParametersParams parametersParams = new ParametersParams();
		parametersParams.setStart_time("9:00:00");
		parametersParams.setEnd_time("18:00:00");
		parametersParams.setLate_time(15);
		parametersParams.setEarly_time(15);
		parametersParams.setMin_work_time(5);
		
		mockMvc.perform(put("/parameters").contentType(MediaType.APPLICATION_JSON)
				   .content(JSONUtils.convertObject2Json(parametersParams))
				   .characterEncoding(CharEncoding.UTF_8)
				   .accept(MediaType.APPLICATION_JSON)
				   .characterEncoding(CharEncoding.UTF_8))
			  .andExpect(jsonPath("$.errorCode").value(0))
			  .andDo(print());
	}
}
