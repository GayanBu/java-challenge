package jp.co.axa.apidemo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import jp.co.axa.apidemo.entities.Employee;

public class EmployeeRestControllerIntegrationTest extends ApiDemoApplicationTests {

	@Override
	@Before
	public void setUp() {
		super.setUp();
	}

	@Test
	public void getEmployeesList() throws Exception {
		String uri = "/api/v1/employees";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Employee[] productlist = super.mapFromJson(content, Employee[].class);
		assertTrue(productlist.length > 0);
	}

	@Test
	public void createEmployee() throws Exception {
		String uri = "/api/v1/employees";
		Employee employee = new Employee();
		employee.setId((long) 3);
		employee.setName("Ginger");
		String inputJson = super.mapToJson(employee);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Employee is created successfully");
	}

	@Test
	public void deleteEmployee() throws Exception {
		String uri = "/api/v1/employees/2";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Employee is deleted successsfully");
	}

	@Test
	public void updateEmployee() throws Exception {
		String uri = "/api/v1/employees/2";
		Employee employee = new Employee();
		employee.setName("Gayan");
		String inputJson = super.mapToJson(employee);
		MvcResult mvcResult = mvc.perform(
				MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		assertEquals(content, "Employee is updated successsfully");
	}

}
