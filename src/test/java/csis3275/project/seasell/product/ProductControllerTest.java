package csis3275.project.seasell.product;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import csis3275.project.seasell.common.exception.ResourceNotFound;
import csis3275.project.seasell.product.dto.ProductDto;
import csis3275.project.seasell.product.service.ProductService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(useDefaultFilters = false, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
@Import(ProductController.class)
class ProductControllerTest {

    @MockBean
    ProductService productService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getProducts() throws Exception {
        when(productService.getProducts()).thenReturn(List.of(createProductDto()));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products")).andExpect(content().json(
                "[{\"id\":0,\"name\":\"Table\",\"description\":\"A good item\",\"condition\":\"90% new\",\"price\":100.24,\"images\":[\"item.jpg\"]}]"))
                .andExpect(status().isOk());
    }

    @Test
    public void getProducts_noProduct() throws Exception {
        when(productService.getProducts()).thenReturn(List.of());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products")).andExpect(content().json("[]"))
                .andExpect(status().isOk());
    }

    @Test
    public void getProduct() throws Exception {
        when(productService.getProduct(1)).thenReturn(createProductDto());
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1")).andExpect(content().json(
                "{\"id\":0,\"name\":\"Table\",\"description\":\"A good item\",\"condition\":\"90% new\",\"price\":100.24,\"images\":[\"item.jpg\"]}"))
                .andExpect(status().isOk());

    }

    @Test
    public void getProduct_notFound() throws Exception {
        when(productService.getProduct(1)).thenThrow(ResourceNotFound.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1")).andExpect(status().reason("Resource not found"))
                .andExpect(status().isNotFound());
    }

    private ProductDto createProductDto() {
        return ProductDto.builder().price(100.24).description("A good item").condition("90% new")
                .images(List.of("item.jpg")).name("Table").build();
    }

}