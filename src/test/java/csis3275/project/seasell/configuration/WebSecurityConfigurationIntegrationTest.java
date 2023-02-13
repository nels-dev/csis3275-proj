package csis3275.project.seasell.configuration;

import static org.hamcrest.core.Is.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class WebSecurityConfigurationIntegrationTest {

    public static final String LOGIN_REQUEST_BODY = "{\"email\":\"cliff@test.io\", \"password\":\"123\"}";
    public static final String LOGIN_REQUEST_ADMIN_BODY = "{\"email\":\"adam@test.io\", \"password\":\"123\"}";
    public static final String LOGIN_REQUEST_INCORRECT_BODY = "{\"email\":\"cliff@test.io\", \"password\":\"321\"}";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getRoot_shouldSucceed() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    void getCurrentUser_unauthorized() throws Exception {
        // In order not to expose the available endpoints to unauthenticated user,
        // visitors should only see Http 401 even when accessing an unmapped path
        mockMvc.perform(get("/api/client/user")).andExpect(status().isUnauthorized());
    }

    @Test
    void getNotExistEndpoint_unauthorized() throws Exception {
        // In order not to expose the available endpoints to unauthenticated user,
        // visitors should only see Http 401 even when accessing an unmapped path
        mockMvc.perform(get("/not-exist")).andExpect(status().isUnauthorized());
    }

    @Test
    void login_unauthorized_incorrectCredentials() throws Exception {
        mockMvc.perform(post("/api/login").content(LOGIN_REQUEST_INCORRECT_BODY)).andExpect(status().isUnauthorized());
    }

    @Test
    void login_shouldSucceed() throws Exception {
        mockMvc.perform(post("/api/login").content(LOGIN_REQUEST_BODY))
                .andExpect(jsonPath("$.user.email", is("cliff@test.io")))
                .andExpect(jsonPath("$.user.role", is("CLIENT")))
                .andExpect(jsonPath("$.user.preferredUserName", is("Cliff Chan"))).andExpect(status().isOk());
    }

    @Test
    void getCurrentClientUser_shouldSucceed_afterLogin() throws Exception {
        String responseBody = mockMvc.perform(post("/api/login").content(LOGIN_REQUEST_BODY)).andReturn().getResponse()
                .getContentAsString();
        String token = JsonPath.read(responseBody, "$.token");
        mockMvc.perform(get("/api/client/user").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk()).andExpect(jsonPath("$.email", is("cliff@test.io")))
                .andExpect(jsonPath("$.role", is("CLIENT")))
                .andExpect(jsonPath("$.preferredUserName", is("Cliff Chan")));
    }

    @Test
    void getCurrentClientUser_shouldFail_adminLogin() throws Exception {
        String responseBody = mockMvc.perform(post("/api/login").content(LOGIN_REQUEST_ADMIN_BODY)).andReturn()
                .getResponse().getContentAsString();
        String token = JsonPath.read(responseBody, "$.token");
        mockMvc.perform(get("/api/client/user").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isForbidden());
    }
}
