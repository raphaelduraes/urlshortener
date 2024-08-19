package com.practice.urlshortener.integration;

import com.google.gson.Gson;
import com.practice.urlshortener.model.Url;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UrlShortenerControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
        assertNotNull(mockMvc);
    }

    @Test
    public void getShortUrlTest() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("")
                .contentType(APPLICATION_JSON)
                .content(getStubJson());
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType("text/plain;charset=UTF-8"));
    }

    @Test
    public void getShortUrlTestException() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("")
                .contentType(APPLICATION_JSON)
                .content("{\"originalUrl\": \"googlecom\"}");
        mockMvc.perform(request)
                .andExpect(status().isBadRequest());
    }

    private String getStubJson() {
        return "{\"originalUrl\": \"http://www.google.com\"}";
    }
}
