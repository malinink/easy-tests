package easytests.controllers;

import easytests.auth.controllers.AuthController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

/**
 * /**
 *
 * @author fortyways
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration

public class AuthControllerTest {

    MockMvc mockMvc;
    @InjectMocks
    AuthController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testSignInWithoutSession() throws Exception {

        mockMvc.perform(get("/auth/sign-in"))
                .andExpect(status().isOk())
                .andExpect(view().name("auth/sign-in"))
                .andExpect(model().attributeDoesNotExist("login"));


    }

    @Test
    public void testSignInWithSession() throws Exception {

        MockHttpSession session = new MockHttpSession();

        session.setAttribute("LAST_LOGIN", "sup");

        mockMvc.perform(get("/auth/sign-in").session(session))

                .andExpect(status().isOk())
                .andExpect(view().name("auth/sign-in"))
                .andExpect(model().attributeExists("login"))
                .andExpect(model().attribute("login", "sup"));
    }

}
