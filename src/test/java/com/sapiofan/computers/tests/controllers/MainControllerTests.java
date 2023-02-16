package com.sapiofan.computers.tests.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class MainControllerTests {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mvc;

    private final MediaType mediaType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    @Test
    public void getComputers() throws Exception {
        this.mvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(mediaType))
                .andReturn();
    }

    @Test
    public void getComputerByName() throws Exception {
        this.mvc.perform(get("/computer").param("name", "Lenovo"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(mediaType))
                .andReturn();
    }

    @Test
    public void getComputerById() throws Exception {
        this.mvc.perform(get("/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$.id", is(3)))
                .andReturn();
    }

    @Test
    public void orderComputerSuccessful() throws Exception {
        this.mvc.perform(post("/order")
                        .param("phone", "+38 050 505 5505")
                        .param("address", "Lviv, Some Street, 45")
                        .param("device", "1")
                        .param("card", "xxxx-xxxx-xxxx-xxxx")
                        .param("date", "12/2027")
                        .param("cvv", "245"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(mediaType))
                .andExpect(jsonPath("$.phone", is("+38 050 505 5505")))
                .andReturn();
    }

    @Test
    public void orderComputerFailure() throws Exception {
        this.mvc.perform(post("/order")
                        .param("phone", "")
                        .param("address", "Lviv, Some Street, 45")
                        .param("device", "1")
                        .param("card", "xxxx-xxxx-xxxx-xxxx")
                        .param("date", "12/2027")
                        .param("cvv", "245"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

        this.mvc.perform(post("/order")
                        .param("phone", "+38 050 505 5505")
                        .param("address", "Lviv, Some Street, 45")
                        .param("device", "1")
                        .param("card", "xxxx-xxxx-xxxx-xxxx")
                        .param("date", "")
                        .param("cvv", "245"))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andReturn();
    }
}
