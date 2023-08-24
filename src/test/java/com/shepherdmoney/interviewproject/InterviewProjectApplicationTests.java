package com.shepherdmoney.interviewproject;

import com.shepherdmoney.interviewproject.controller.CreditCardController;
import com.shepherdmoney.interviewproject.controller.UserController;
import com.shepherdmoney.interviewproject.repository.CreditCardRepository;
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
    private CreditCardController cc;

    @Autowired
    private UserRepository ur;

    @Autowired
    private CreditCardRepository cr;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void deleteAllBeforeTests() throws Exception {
        ur.deleteAll();
        cr.deleteAll();
    }
    @Test
    public void contextLoads() throws Exception {
        assertThat(ur).isNotNull();
        assertThat(uc).isNotNull();
    }

    /**
     * Create User John. The user repo should then contain John.
     */
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

    /**
     * Create user A and user B. Then delete user A. Should return 200 ok.
     */
    @Test
    public void testDeleteUser() throws Exception {

    }

    /**
     * Create user A. Then delete user B. Should return 400.
     */
    @Test
    public void testDeleteNonExistingUser() throws Exception {

    }

    /**
     * Creates a credit card entity, and then associate that credit card with user with given userId
     * @param payload
     * @return A ResponseEntity with an appropriate HTTP status code and response body.
     *          - 200 OK: If the user exists and the credit card is successfully associated with the user.
     *          - 404 Not Found: If the user with the given userId does not exist.
     *          - 500 Internal Server Error: If an unexpected error occurs during the process.
     */


    /**
     * Configuration:
     * User A: no credit cards
     */
    @Test
    public void testAddCreditCardToExistingUser() throws Exception {

    }

    @Test
    public void testAddCreditCardToNonExistingUser() throws Exception {

    }


}
