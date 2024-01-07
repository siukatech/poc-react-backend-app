package com.siukatech.poc.react.backend.app.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siukatech.poc.react.backend.app.AbstractUnitTests;
import com.siukatech.poc.react.backend.app.AbstractWebTests;
import com.siukatech.poc.react.backend.app.business.dto.AttachmentDto;
import com.siukatech.poc.react.backend.app.business.form.AttachmentForm;
import com.siukatech.poc.react.backend.app.business.service.AttachmentService;
import com.siukatech.poc.react.backend.app.global.helper.AttachmentHelper;
import com.siukatech.poc.react.backend.app.web.controller.AttachmentController;
import com.siukatech.poc.react.backend.parent.security.authentication.MyAuthenticationToken;
import com.siukatech.poc.react.backend.parent.web.annotation.v1.ProtectedApiV1Controller;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {AttachmentController.class})
@AutoConfigureMockMvc(addFilters = false)
public class AttachmentControllerTests extends AbstractWebTests {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AttachmentService attachmentService;

    private AttachmentHelper attachmentHelper = new AttachmentHelper();


    @BeforeAll
    public static void init() {
        AbstractUnitTests.init();
    }

    @BeforeEach
    public void setup(TestInfo testInfo) {
        if (mockMvc == null) {
            mockMvc = MockMvcBuilders
                    .webAppContextSetup(webApplicationContext)
                    .apply(springSecurity())
                    .build();
        }
    }

    @BeforeEach
    public void teardown(TestInfo testInfo) {
    }


    protected MyAuthenticationToken prepareMyAuthenticationToken_basic() {
        return prepareMyAuthenticationToken("app-user-01", 1L);
    }

    @Test
    public void listAttachments_basic() throws Exception {
        // given
        AttachmentDto attachmentDto = this.attachmentHelper.convertToAttachmentDto(
                this.attachmentHelper.prepareAttachmentForm_basic());
        when(attachmentService.findAttachmentAllByUserId(anyLong())).thenReturn(List.of(attachmentDto));

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(ProtectedApiV1Controller.REQUEST_MAPPING_URI_PREFIX
                        + "/attachments")
                .with(authentication(prepareMyAuthenticationToken_basic()))
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON);

        // then
        MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(AttachmentHelper.RESOURCE_FILE_NAME)))
                .andReturn();

    }

    @Test
    public void getAttachmentById_basic() throws Exception {
        // given

        AttachmentForm attachmentForm = this.attachmentHelper.prepareAttachmentForm_basic();
        AttachmentDto attachmentDto = this.attachmentHelper.convertToAttachmentDto(attachmentForm);
        when(this.attachmentService.findAttachmentById(any(UUID.class))).thenReturn(attachmentDto);

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(ProtectedApiV1Controller.REQUEST_MAPPING_URI_PREFIX
                        + "/attachments/{id}", attachmentForm.getId())
                .with(authentication(prepareMyAuthenticationToken_basic()))
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON);

        // then
        MvcResult mvcResult = this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void uploadAttachment_basic() throws Exception {
        // given
        AttachmentForm attachmentForm = this.attachmentHelper.prepareAttachmentForm_basic();
        AttachmentDto attachmentDto = this.attachmentHelper.convertToAttachmentDto(attachmentForm);
        Map<String, String> attachmentDtoFieldMap = this.objectMapper.convertValue(
                attachmentDto, new TypeReference<Map<String, String>>() {
                });
        MultiValueMap<String, String> attachmentDtoValueMap = new LinkedMultiValueMap<>();
        attachmentDtoValueMap.setAll(attachmentDtoFieldMap);
        when(this.attachmentService.uploadAttachment(attachmentForm)).thenReturn(attachmentDto);

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .multipart(ProtectedApiV1Controller.REQUEST_MAPPING_URI_PREFIX
                        + "/attachments")
                .file((MockMultipartFile) attachmentForm.getMultipartFile())
                .params(attachmentDtoValueMap)
                .with(authentication(prepareMyAuthenticationToken_basic()))
                .with(csrf());

        // then
        MvcResult mvcResult = this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();
    }

    /**
     * void handling is different with return
     * Reference
     * https://baeldung.com/mockito-void-methods
     *
     * @throws Exception
     */
    @Test
    public void deleteAttachment_basic() throws Exception {
        // given
        // doNothing
        doNothing().when(this.attachmentService).deleteAttachment(any(UUID.class));

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(ProtectedApiV1Controller.REQUEST_MAPPING_URI_PREFIX
                        + "/attachments/{id}", UUID.randomUUID())
                .with(authentication(prepareMyAuthenticationToken_basic()))
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON);

        // then
        MvcResult mvcResult = this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }


}
