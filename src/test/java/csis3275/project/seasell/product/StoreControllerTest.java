package csis3275.project.seasell.product;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import csis3275.project.seasell.product.dto.ProductDto;
import csis3275.project.seasell.product.model.ProductStatus;
import csis3275.project.seasell.product.service.ProductService;

@WebMvcTest(useDefaultFilters = false, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
@Import(ProductController.class)
public class StoreControllerTest {

    @MockBean
    ProductService productService;

    @Autowired
    MockMvc mockMvc;
    
    
    @Test
    public void getUserProductsByStatus() throws Exception {
        when(productService.getUserProductsByStatus(ProductStatus.LISTED)).thenReturn(List.of(createProductDto()));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/client/store/products")).andExpect(content().json(
                "[{\"id\":0,\"name\":\"Table\",\"description\":\"A good item\",\"condition\":\"90% new\",\"price\":100.24,\"images\":[\"item.jpg\"]}]"))
                .andExpect(status().isOk());
    }
    
    private ProductDto createProductDto() {
        return ProductDto.builder().price(100.24).description("A good item").condition("90% new")
                .images(List.of("item.jpg")).name("Table").build();
    }
}
