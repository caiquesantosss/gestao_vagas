package br.com.caiquesantos.gestao_vagas.modules.company.controllers;

import br.com.caiquesantos.gestao_vagas.modules.company.Entities.*;
import br.com.caiquesantos.gestao_vagas.modules.company.Repositories.*;
import br.com.caiquesantos.gestao_vagas.modules.company.dto.*;
import br.com.caiquesantos.gestao_vagas.modules.utils.*;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.security.test.web.servlet.setup.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class CreateJobControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CompanyRepository companyRepository;

    @Value("${security.token.secret}")
    private String SecretTestToken;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity() )
                .build();
    }

    @Test
    public void shouldBeAble_createANewJob() throws Exception {

        var company = CompanyEntity.builder()
                .description("COMPANY_DESCRIPTION")
                .email("company@email.com")
                .password("1234567890")
                .username("COMPANY_USERNAME")
                .name("COMPANY_NAME")
                .build();

        company = companyRepository.saveAndFlush(company);

        /*
        *  SaveAndFlush, é uma estratégia utilizada para que algo seja salvo imeditamente,
        * sem a necessidade de esperar algumas requisições acontecerem.
        * */

        var createdJoDTO = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();

        var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectToString(createdJoDTO))
                        .header("Authorization",
                                TestUtils.generateToken(
                                     company.getId(),
                                        SecretTestToken
                        ))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldNotBeAbleToCreateAJob_ifCompanyIsNotFound() throws Exception {
        var createdJoDTO = CreateJobDTO.builder()
                .benefits("BENEFITS_TEST")
                .description("DESCRIPTION_TEST")
                .level("LEVEL_TEST")
                .build();

        mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToString(createdJoDTO))
                .header("Authorization",
                        TestUtils.generateToken(
                                UUID.randomUUID(),
                                SecretTestToken
                        ))
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
}
