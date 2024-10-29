package com.myprojects;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import com.myprojects.enums.Category;
import com.myprojects.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Arrays;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class CatalogControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getProduct() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/catalog/products/P002").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code"). value("P002"));
    }

    @Test
    public void getProducts_category() throws Exception{

        mvc.perform(MockMvcRequestBuilders.get("/catalog/products-by-category/PRINTERS").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].price").isNumber())
                .andExpect(jsonPath("$.code", is("P001")))
                .andExpect(jsonPath("$[0].description").exists())
                .andExpect(jsonPath("$[0].dummy").doesNotExist());
    }

    @Test
    public void getProducts() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Product[] products;
        Product product1 = new Product("P001", "Printer P001", Category.PRINTERS, 321.37);
        Product product2 = new Product("C001", "Computer A001", Category.COMPUTERS, 925.4);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                        .get("/catalog/products")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        products = mapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<Product[]>() {});

        assertThat(products, arrayWithSize(4));
        assertThat(Arrays.stream(products).toList(), hasItem(product1));
        assertThat(products[0], is(product2));
        assertThat(products[0].getCode(), is("C001"));
    }



}
