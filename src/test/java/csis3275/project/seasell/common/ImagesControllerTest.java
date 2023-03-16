package csis3275.project.seasell.common;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import csis3275.project.seasell.common.dto.FileWrapper;
import csis3275.project.seasell.common.service.LocalStorageFileService;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/*
 * @WebMvcTest allows testing of web layer (Controllers) by spinning up a minimal spring context.
 * *
 * Reference: https://spring.io/guides/gs/testing-web/
 */
@WebMvcTest(useDefaultFilters = false, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
@Import(ImagesController.class)
class ImagesControllerTest {

    @Autowired
    MockMvc mockMvc;

    // The actual behaviour of fileService it out-of-scope of this unit-test. Therefore, it is mocked.
    @MockBean
    LocalStorageFileService fileService;

    @Test
    public void getProductImage() throws Exception {
        final byte[] fileBytes = { 1 };
        when(fileService.retrieve(anyString())).thenReturn(new FileWrapper(new Date(), fileBytes));
        mockMvc.perform(MockMvcRequestBuilders.get("/images/my-picture.jpg")).andExpect(status().isOk())
                .andExpect(content().bytes(fileBytes));
        verify(fileService).retrieve("my-picture.jpg");
    }
}
