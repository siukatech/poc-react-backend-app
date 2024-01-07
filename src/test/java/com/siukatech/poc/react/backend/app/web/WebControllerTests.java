package com.siukatech.poc.react.backend.app.web;

import com.siukatech.poc.react.backend.app.AbstractUnitTests;
import com.siukatech.poc.react.backend.app.AbstractWebTests;
import com.siukatech.poc.react.backend.app.web.controller.WebController;
import com.siukatech.poc.react.backend.parent.web.annotation.v1.PublicApiV1Controller;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = WebController.class)
@AutoConfigureMockMvc(addFilters = false)
public class WebControllerTests extends AbstractWebTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void index_basic() throws Exception {
        // given


        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(new URI(PublicApiV1Controller.REQUEST_MAPPING_URI_PREFIX))
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON);

        // then
        MvcResult mvcResult = this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("external")))
                .andReturn();
    }
}