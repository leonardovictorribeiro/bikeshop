package dev.leonardovictor.bikeshop;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import dev.leonardovictor.bikeshop.model.Availability;
import dev.leonardovictor.bikeshop.model.Tool;
import dev.leonardovictor.bikeshop.service.ToolService;
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class BikeShopApplicationTests {

	@Autowired
	private ToolService toolService;

	@Autowired
	private MockMvc mockMvc;

	private HttpHeaders httpHeaders;
	private Tool tool;

	@BeforeEach
	public void setup() {
		tool = Tool.builder()
		.name("Motoserra")
		.brand("Bosh").build();

		httpHeaders = new HttpHeaders();
		httpHeaders.add("X-API-VERSION", "v1");
	}

	@Test
	public void testPostTool() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		MockHttpServletResponse response = mockMvc.perform(post("/tools/")
		.contentType("application/json")
		.headers(httpHeaders)
		.content(objectMapper.writeValueAsString(tool)))
		.andDo(print())
		.andExpect(jsonPath("$.id", greaterThan(0)))
		.andExpect(jsonPath("$.name").value("Motoserra"))
		.andExpect(jsonPath("$.brand").value("Bosh"))
		.andExpect(jsonPath("$.availability").value(Availability.UNAVAILABLE.getStatus()))
		.andExpect(jsonPath("$.version").value(1))
		.andExpect(status().isCreated()).andReturn().getResponse();

		Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");
		assertNotNull(toolService.getToolById(id));
	}


	@Test
	public void testRetrieveTool() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		MockHttpServletResponse response = mockMvc.perform(post("/tools/")
		.contentType("application/json")
		.headers(httpHeaders)
		.content(objectMapper.writeValueAsString(tool)))
		.andDo(print())
		.andExpect(jsonPath("$.id", greaterThan(0)))
		.andExpect(jsonPath("$.name").value("Motoserra"))
		.andExpect(jsonPath("$.brand").value("Bosh"))
		.andExpect(jsonPath("$.availability").value(Availability.UNAVAILABLE.getStatus()))
		.andExpect(jsonPath("$.version").value(1))
		.andExpect(status().isCreated()).andReturn().getResponse();

		Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");
		
		mockMvc.perform(get("/tools/{id}", id)
		.headers(httpHeaders))
		.andDo(print())
		.andExpect(jsonPath("$.id", greaterThan(0)))
		.andExpect(jsonPath("$.name").value("Motoserra"))
		.andExpect(jsonPath("$.brand").value("Bosh"))
		.andExpect(jsonPath("$.availability").value(Availability.UNAVAILABLE.getStatus()))
		.andExpect(jsonPath("$.version").value(1))
		.andExpect(status().isOk());
	}

	@Test
	public void testInvalidUserId() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();

		MockHttpServletResponse response = mockMvc.perform(post("/tools/")
		.contentType("application/json")
		.headers(httpHeaders)
		.content(objectMapper.writeValueAsString(tool)))
		.andDo(print())
		.andExpect(jsonPath("$.id", greaterThan(0)))
		.andExpect(jsonPath("$.name").value("Motoserra"))
		.andExpect(jsonPath("$.brand").value("Bosh"))
		.andExpect(jsonPath("$.availability").value(Availability.UNAVAILABLE.getStatus()))
		.andExpect(jsonPath("$.version").value(1))
		.andExpect(status().isCreated()).andReturn().getResponse();

		Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");
		id += 100;

		mockMvc.perform(get("/tools/{id}", id))
        .andDo(print())
        .andExpect(status().isNotFound());
	}

	@Test
    public void testUpdateTool() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

		MockHttpServletResponse response = mockMvc.perform(post("/tools/")
		.contentType("application/json")
		.content(objectMapper.writeValueAsString(tool))
		.headers(httpHeaders))
		.andDo(print())
		.andExpect(jsonPath("$.id", greaterThan(0)))
		.andExpect(jsonPath("$.name").value("Motoserra"))
		.andExpect(jsonPath("$.brand").value("Bosh"))
		.andExpect(jsonPath("$.availability").value(Availability.UNAVAILABLE.getStatus()))
		.andExpect(jsonPath("$.version").value(1))
		.andExpect(status().isCreated()).andReturn().getResponse();
		Integer id = JsonPath.parse(response.getContentAsString()).read("$.id");		

        Tool updatedTool = Tool.builder()
								.name("Motoserra")
								.brand("Dewalt")
								.availability(Availability.AVAILABLE).build();
        
		mockMvc.perform(put("/tools/{id}", id)
		.contentType("application/json")
		.content(objectMapper.writeValueAsString(updatedTool))
		.headers(httpHeaders))
		.andDo(print())
		.andExpect(jsonPath("$.id").value(10))
		.andExpect(jsonPath("$.name").value("Motoserra"))
		.andExpect(jsonPath("$.brand").value("Dewalt"))
		.andExpect(jsonPath("$.availability").value(Availability.AVAILABLE.getStatus()))
		.andExpect(jsonPath("$.version").value(2))
		.andExpect(status().isNoContent());
    }
}
