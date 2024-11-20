package com.siukatech.poc.react.backend.app.base.web;

import com.siukatech.poc.react.backend.app.base.web.controller.ProtectedController;
import com.siukatech.poc.react.backend.core.AbstractWebTests;
import com.siukatech.poc.react.backend.core.web.annotation.v1.ProtectedApiV1Controller;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProtectedController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ProtectedControllerTests extends AbstractWebTests {

//    @Autowired
//    private MockMvc mockMvc;

    @Test
    public void test_basic() throws Exception {
        // given

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(ProtectedApiV1Controller.REQUEST_MAPPING_URI_PREFIX
                        + "/test/{test}", "Hello :)")
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON)
                ;

        // then
        MvcResult mvcResult = this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello")))
                .andReturn()
                ;

    }
}
