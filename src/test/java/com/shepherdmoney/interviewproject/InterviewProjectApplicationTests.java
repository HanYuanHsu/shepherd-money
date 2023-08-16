package com.shepherdmoney.interviewproject;

import com.shepherdmoney.interviewproject.controller.UserController;
import com.shepherdmoney.interviewproject.repository.UserRepository;
import org.junit.jupiter.api.*;
import static org.assertj.core.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.argThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class InterviewProjectApplicationTests {

    @Autowired
    private UserController uc;

    @Autowired
    private UserRepository ur;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void deleteAllBeforeTests() throws Exception {
        ur.deleteAll();
    }
    @Test
    public void contextLoads() throws Exception {
        assertThat(ur).isNotNull();
        assertThat(uc).isNotNull();
    }

    @Test
    public void shouldCreateUser() throws Exception {
        mockMvc.perform(put("/user")
                        .content("{\"name\": \"John\", \"email\": \"john@example.org\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk()
                );

        verify(ur).save(argThat(user -> user.getName().equals("John") &&
                user.getEmail().equals("john@example.org")));
    }

}
