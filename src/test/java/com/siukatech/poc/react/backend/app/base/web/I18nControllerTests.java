package com.siukatech.poc.react.backend.app.base.web;

import com.siukatech.poc.react.backend.app.base.business.dto.I18nDto;
import com.siukatech.poc.react.backend.app.base.business.form.I18nForm;
import com.siukatech.poc.react.backend.app.base.business.service.I18nService;
import com.siukatech.poc.react.backend.app.base.web.controller.I18nController;
import com.siukatech.poc.react.backend.core.AbstractUnitTests;
import com.siukatech.poc.react.backend.core.AbstractWebTests;
import com.siukatech.poc.react.backend.core.web.annotation.v1.PublicApiV1Controller;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Locale;
import java.util.Map;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(
        controllers = {I18nController.class}
//        ,
//        properties = {"logging.level.org.springframework.web=TRACE"
//        , "logging.level.com.siukatech.poc.react.backend.parent=DEBUG"
//        , "client-id=xxx"
//        , "client-secret=xxx"
//        , "client-realm=xxx"
//        , "oauth2-client-redirect-uri=xxx"
//        , "oauth2-client-keycloak=xxx"
//        }
//        , useDefaultFilters = false
)
@AutoConfigureMockMvc(addFilters = false)
//@Import(I18nController.class)
//@ContextConfiguration(classes = {WebSecurityConfig.class})
public class I18nControllerTests extends AbstractWebTests {

//    @MockBean
//    @Autowired
//    public I18nRepository i18nRepository;

////    @Autowired
//    private WebApplicationContext webApplicationContext;

//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;

////    @MockBean
//    private ModelMapper modelMapper;

    @MockBean
    private I18nService i18nService;
//    @MockBean
//    private UserService userService;
//    @MockBean
//    private UserRepository userRepository;
//    @SpyBean
//    private EncryptedBodyContext encryptedBodyContext;
//    @MockBean
//    private EncryptedBodyAdviceHelper encryptedBodyAdviceHelper;
//    @MockBean
//    private InMemoryClientRegistrationRepository clientRegistrationRepository;


    @BeforeAll
    public static void init() {
        AbstractUnitTests.init();
    }

//    private UsernamePasswordAuthenticationToken prepareAuthenticationToken_basic() {
//        List<GrantedAuthority> convertedAuthorities = new ArrayList<GrantedAuthority>();
//        UserDetails userDetails = new User("app-user-01", "", convertedAuthorities);
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//        return authenticationToken;
//    }

    @BeforeEach
    public void setup(TestInfo testInfo) {
//        I18nEntity i18nEntity = new I18nEntity();
//        i18nEntity.setId(1L);
//        i18nEntity.setMessageKey("testing.title");
//        i18nEntity.setMessageEn("Testing title En");
//        i18nEntity.setMessageZh("Testing title Tc");
//        i18nEntity.setMessageCn("Testing title Sc");
//        i18nEntity.setVersionNo(1L);
//        this.i18nRepository.save(i18nEntity);
        //
//        UsernamePasswordAuthenticationToken authenticationToken = prepareAuthenticationToken_basic();
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        //

//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                .apply(springSecurity())
//                .build();
    }

    @AfterEach
    public void teardown(TestInfo testInfo) {
//        I18nEntity i18nEntity = new I18nEntity();
//        i18nEntity.setId(1L);
//        i18nEntity.setMessageKey("testing.title");
//        i18nEntity.setMessageEn("Testing title En");
//        i18nEntity.setMessageZh("Testing title Tc");
//        i18nEntity.setMessageCn("Testing title Sc");
//        i18nEntity.setVersionNo(1L);
//        this.i18nRepository.delete(i18nEntity);
    }

    private I18nForm prepareI18nForm() {
        I18nForm i18nForm = new I18nForm();
//        i18nForm.setId(2L);
//        i18nForm.setMessageKey("testing.title.2");
        i18nForm.setId("testing.title.2");
        i18nForm.setMessageEn("Testing");
        i18nForm.setMessageEn("Testing title En");
        i18nForm.setMessageTc("Testing title Tc");
        i18nForm.setMessageSc("Testing title Sc");
        return i18nForm;
    }

    private I18nDto convertToI18nDto(I18nForm i18nForm, String langTag) {
//        ModelMapper mapper = new ModelMapper();
        I18nDto i18nDto = new I18nDto();
//        i18nDto.setKey(i18nForm.getMessageKey());
        i18nDto.setKey(i18nForm.getId());
        switch (langTag) {
            case "zh-TW":
                i18nDto.setMessage(i18nForm.getMessageTc());
            case "zh-CN":
                i18nDto.setMessage(i18nForm.getMessageSc());
            default:
                i18nDto.setMessage(i18nForm.getMessageEn());
        }
        return i18nDto;
    }

    @Test
    public void listI18ns() throws Exception {
        // given
        Map<String, String> i18nMapEn = Map.of("testing.title", "Testing title En");
        Map<String, String> i18nMapZh = Map.of("testing.title", "Testing title Tc");
        Map<String, String> i18nMapCn = Map.of("testing.title", "Testing title Sc");
        String langTagEn = Locale.ENGLISH.toLanguageTag();
        String langTagZh = Locale.TRADITIONAL_CHINESE.toLanguageTag();
        String langTagCn = Locale.SIMPLIFIED_CHINESE.toLanguageTag();
        Map<String, Map<String, String>> i18nMap = Map.of(
                langTagEn, i18nMapEn
                , langTagZh, i18nMapZh
                , langTagCn, i18nMapCn
        );
        when(i18nService.findI18nMap()).thenReturn(i18nMap);

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .request(HttpMethod.GET.name()
//                        , new URI(PublicApiV1Controller.REQUEST_MAPPING_URI_PREFIX + "/i18n/en"))
                .get(PublicApiV1Controller.REQUEST_MAPPING_URI_PREFIX
                        + "/i18n")
//                .contentType(MediaType.APPLICATION_JSON)
//                .headers(httpHeaders)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON)
                ;

        // then
        MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(" En")))
                .andExpect(content().string(containsString(" Tc")))
                .andExpect(content().string(containsString(" Sc")))
                .andReturn();

    }

    @Test
    public void listI18ns_en() throws Exception {
        // given
        String langTag = Locale.ENGLISH.toLanguageTag();
        when(i18nService.findI18nMap(anyString())).thenReturn(Map.of("testing.title", "Testing title En"));

        // when
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.set(HttpHeaders.ACCEPT_LANGUAGE, langTag);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
//                .request(HttpMethod.GET.name()
//                        , new URI(PublicApiV1Controller.REQUEST_MAPPING_URI_PREFIX + "/i18n/en"))
                .get(PublicApiV1Controller.REQUEST_MAPPING_URI_PREFIX
                        + "/i18n/{langTag}", langTag)
//                .contentType(MediaType.APPLICATION_JSON)
//                .headers(httpHeaders)
                .with(csrf())
                .accept(MediaType.APPLICATION_JSON)
                ;

        // then
        MvcResult mvcResult = this.mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(" En")))
                .andReturn();

        // result
//        log.debug("listI18ns_en - mvcResult.getResponse.getContentAsString: [" + mvcResult.getResponse().getContentAsString() + "]");

    }

    @Test
    public void createI18n_en() throws Exception {
        // given
        I18nForm i18nForm = this.prepareI18nForm();
        String i18nFormStr = this.objectMapper.writeValueAsString(i18nForm);
        log.debug("createI18n_en - i18nFormStr: [{}]", i18nFormStr);
        when(this.i18nService.createI18n(i18nForm))
                .thenReturn(this.convertToI18nDto(
                        i18nForm, Locale.ENGLISH.toLanguageTag()));

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(PublicApiV1Controller.REQUEST_MAPPING_URI_PREFIX
                        + "/i18n")
                .with(csrf())
                .content(i18nFormStr)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                ;

        // then
        MvcResult mvcResult = this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn()
                ;
    }

    @Test
    public void updateI18n_en() throws Exception {
        // given
        I18nForm i18nForm = this.prepareI18nForm();
        i18nForm.setMessageEn(i18nForm.getMessageEn() + ", updated");
        String i18nFormStr = this.objectMapper.writeValueAsString(i18nForm);
        log.debug("updateI18n_en - i18nFormStr: [{}]", i18nFormStr);
        when(this.i18nService.updateI18n(i18nForm, i18nForm.getId()))
                .thenReturn(this.convertToI18nDto(
                        i18nForm, Locale.ENGLISH.toLanguageTag()));

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put(PublicApiV1Controller.REQUEST_MAPPING_URI_PREFIX
                        + "/i18n/{targetI18nId}", i18nForm.getId())
                .with(csrf())
                .content(i18nFormStr)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                ;

        // then
        MvcResult mvcResult = this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                ;
    }

    /**
     * void handling is different with return
     * Reference
     * https://baeldung.com/mockito-void-methods
     *
     * @throws Exception
     */
    @Test
    public void deleteI18n_en() throws Exception {
        // given
        // doNothing
        doNothing().when(this.i18nService).deleteI18n(anyString());

        // when
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(PublicApiV1Controller.REQUEST_MAPPING_URI_PREFIX
                        + "/i18n/{targetI18nId}", 1L)
                .with(csrf())
//                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                ;

        // then
        MvcResult mvcResult = this.mockMvc
                .perform(requestBuilder)
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                ;
    }


}
