package cn.com.goolife.controller.test;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import cn.com.goolife.controller.PageController;

public class PageControllerTest {

	@Test
	public void handleRequest() throws Exception {
		PageController controller = new PageController();
		MockMvc mock = MockMvcBuilders.standaloneSetup(controller).build();
		mock.perform(MockMvcRequestBuilders.get("/back")).
		andExpect(MockMvcResultMatchers.view().name("modelViewBack"));
	}
}
