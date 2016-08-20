package com.jedeft.attendence.junit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

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
import com.jedeft.attendence.data.Employee;
import com.jedeft.attendence.data.params.SignRecordParams;
import com.jedeft.attendence.service.IEmployeeService;


/**
 * 
 * @Description: 签到记录单元测试
 * @author Jedeft
 * @date 2016年8月20日 下午9:23:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration(value = "src/main/webapp")  
@ContextHierarchy({  
        @ContextConfiguration(name = "parent", locations = "classpath:spring-mybatis.xml"),  
        @ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml")  
})  
public class SignRecordTest {
	
	@Autowired  
    private WebApplicationContext wac;  

	@Autowired
	private IEmployeeService employeeService;
	
    private MockMvc mockMvc;
    
    @Before  
    public void setUp() {  
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  
    } 
    
    /**
     * url : /signRecord
     * method : POST
     * @throws Exception
     */
	@Test
	public void postTest() throws Exception{
		SignRecordParams signRecordParams = new SignRecordParams();
		List<Employee> list = employeeService.searchData();
		signRecordParams.setEmployee_id(list.get(0).getId());
		signRecordParams.setSign_time("2016-8-20 9:00:00");
		
		mockMvc.perform(post("/signRecord").contentType(MediaType.APPLICATION_JSON)
				   .content(JSONUtils.convertObject2Json(signRecordParams))
				   .characterEncoding(CharEncoding.UTF_8)
				   .accept(MediaType.APPLICATION_JSON)
				   .characterEncoding(CharEncoding.UTF_8))
			  .andExpect(jsonPath("$.errorCode").value(0))
			  .andDo(print());
	}
	
	/**
     * url : /signRecord/{employee_id}
     * method : POST
     * @throws Exception
     */
	@Test
	public void getTest() throws Exception{
		SignRecordParams signRecordParams = new SignRecordParams();
		List<Employee> list = employeeService.searchData();
		signRecordParams.setEmployee_id(list.get(0).getId());
		
		mockMvc.perform(get("/signRecord/" + list.get(0).getId() + "?month=2016-5").contentType(MediaType.TEXT_HTML)
				   .characterEncoding(CharEncoding.UTF_8)
				   .accept(MediaType.APPLICATION_JSON)
				   .characterEncoding(CharEncoding.UTF_8))
			  .andExpect(status().isOk())
			  .andDo(print());
	}
}
