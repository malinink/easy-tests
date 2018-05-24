package easytests.api.v1.controllers;
import easytests.api.v1.mappers.UsersMapper;
import easytests.auth.services.AccessControlLayerServiceInterface;
import easytests.config.SwaggerRequestValidationConfig;
import easytests.core.models.*;
import easytests.core.services.UsersService;
import easytests.support.JsonSupport;
import easytests.support.UsersSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author SvetlanaTselikova
 */
@Import({UsersMapper.class, SwaggerRequestValidationConfig.class})
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UsersController.class, secure = false)
public class UsersControllerTest {
    private static String id = "id";
    private static String firstName = "firstName";
    private static String lastName = "lastName";
    private static String surname = "surname";
    private static String email = "email";
    private static String isAdmin = "isAdmin";
    private static String state = "state";
    private static String subjects = "subjects";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsersService usersService;



    private UsersSupport usersSupport = new UsersSupport();

    /**
     * testListSuccess
     */
    /**
     * testCreateSuccess
     */
    /**
     * testCreateWithIdFailed
     */
    /**
     * testCreateWithSubjectsFailed
     */
    /**
     * testUpdateSuccess
     */
    @Test
    public void testUpdateSuccess() throws Exception {
        final UserModelInterface userModel = this.usersSupport.getModelFixtureMock(0);
        when(this.usersService.find(any(), any())).thenReturn(userModel);

        mvc.perform(put("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(id, 1)
                        .with(firstName, "firstName")
                        .with(lastName, "lastName")
                        .with(surname, "surname")
                        .with(email, "mail@fmail.com")
                        .with(isAdmin, true)
                        .with(state, 0)
                        .build()
                ))
                .andExpect(status().is(200))
                .andExpect(content().string(""))
                .andReturn();

        verify(this.usersService, times(1)).save(userModel);
    }
    /**
     * testUpdateWithoutIdFailed
     */
    @Test
    public void testUpdateWithoutIdFailed() throws Exception {
        mvc.perform(put("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(firstName, "firstName")
                        .with(lastName, "lastName")
                        .with(surname, "surname")
                        .with(email, "mail@fmail.com")
                        .with(isAdmin, true)
                        .with(state, 0)
                        .build()
                ))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""))
                .andReturn();
    }
    /**
     * testUpdateWithSubjectsFailed
     */
    @Test
    public void testUpdateWithSubjectsFailed() throws Exception {
        mvc.perform(put("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(id, 1)
                        .with(firstName, "firstName")
                        .with(lastName, "lastName")
                        .with(surname, "surname")
                        .with(email, "mail@fmail.com")
                        .with(isAdmin, true)
                        .with(state, 0)
                        .with(subjects, new JsonSupport()
                                .withArray()
                                .withNotNull())
                        .build()
                ))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""))
                .andReturn();
    }

    /**
     *
     * testShowSuccess
     */
    /**
     * testShowFailed
     */
    /**
     * testShowWithSubjectsSuccess
     */
    /**
     * testDeleteSuccess
     */
    /**
     * testDeleteFailed
     */
    /**
     * testShowMeSuccess
     */
    /**
     * testShowMeFailed
     */
    /**
     * testShowMeWithSubjectsSuccess
     */
}
