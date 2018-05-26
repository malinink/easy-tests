package easytests.api.v1.controllers;

import easytests.api.v1.mappers.UsersMapper;
import easytests.auth.services.AccessControlLayerServiceInterface;
import easytests.auth.services.SessionServiceInterface;
import easytests.config.SwaggerRequestValidationConfig;
import easytests.core.models.*;
import easytests.core.options.builder.UsersOptionsBuilder;
import easytests.core.services.UsersService;
import easytests.support.JsonSupport;
import easytests.support.SubjectsSupport;
import easytests.support.UsersSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
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

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsersService usersService;

    @MockBean
    private UsersOptionsBuilder usersOptionsBuilder;

    private UsersSupport usersSupport = new UsersSupport();


    @MockBean
    private AccessControlLayerServiceInterface acl;

    @MockBean
    private SessionServiceInterface sessionService;

    @Test
    public void testListSuccess() throws Exception {
        final UserModelInterface userAdminModel = new UserModel();
        userAdminModel.map(this.usersSupport.getAdminUser());
        when(this.sessionService.getUserModel()).thenReturn(userAdminModel);

        final List<UserModelInterface> usersModels = new ArrayList<>();
        IntStream.range(0, 2).forEach(idx -> {
            final UserModel userModel = new UserModel();
            userModel.map(this.usersSupport.getEntityFixtureMock(idx));
            usersModels.add(userModel);
        });
        when(this.usersService.findAll()).thenReturn(usersModels);

        mvc.perform(get("/v1/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new JsonSupport()
                        .with(new JsonSupport()
                                .with(id, usersModels.get(0).getId())
                                .with(firstName, usersModels.get(0).getFirstName())
                                .with(lastName, usersModels.get(0).getLastName())
                                .with(surname, usersModels.get(0).getSurname())
                                .with(email, usersModels.get(0).getEmail())
                                .with(isAdmin, usersModels.get(0).getIsAdmin())
                                .with(state, usersModels.get(0).getState()))
                        .with(new JsonSupport()
                                .with(id, usersModels.get(1).getId())
                                .with(firstName, usersModels.get(1).getFirstName())
                                .with(lastName, usersModels.get(1).getLastName())
                                .with(surname, usersModels.get(1).getSurname())
                                .with(email, usersModels.get(1).getEmail())
                                .with(isAdmin, usersModels.get(1).getIsAdmin())
                                .with(state, usersModels.get(1).getState()))
                        .build()
                ))
                .andReturn();
    }

    @Test
    public void testListForbidden() throws Exception {
        final UserModelInterface userNotAdminModel = new UserModel();
        userNotAdminModel.map(this.usersSupport.getNotAdminUser());
        when(this.sessionService.getUserModel()).thenReturn(userNotAdminModel);

        mvc.perform(get("/v1/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();
    }

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
    /**
     * testUpdateWithoutIdFailed
     */
    /**
     * testUpdateWithSubjectsFailed
     */
    @Test
    public void testShowSuccess() throws Exception {
        final UserModelInterface userAdminModel = new UserModel();
        userAdminModel.map(this.usersSupport.getAdminUser());
        when(this.sessionService.getUserModel()).thenReturn(userAdminModel);

        final UserModelInterface userModel = new UserModel();
        userModel.map(this.usersSupport.getEntityFixtureMock(0));
        when(this.usersService.find(any(Integer.class))).thenReturn(userModel);

        mvc.perform(get("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new JsonSupport()
                                .with(id, userModel.getId())
                                .with(firstName, userModel.getFirstName())
                                .with(lastName, userModel.getLastName())
                                .with(surname, userModel.getSurname())
                                .with(email, userModel.getEmail())
                                .with(isAdmin, userModel.getIsAdmin())
                                .with(state, userModel.getState())
                        .build()
                ))
                .andReturn();
    }

    @Test
    public void testShowForbidden() throws Exception {
        final UserModelInterface userModel = new UserModel();
        userModel.map(this.usersSupport.getEntityFixtureMock(0));
        when(this.usersService.find(any(Integer.class))).thenReturn(userModel);

        final UserModelInterface userNotAdminModel = new UserModel();
        userNotAdminModel.map(this.usersSupport.getNotAdminUser());
        when(this.sessionService.getUserModel()).thenReturn(userNotAdminModel);

        mvc.perform(get("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testShowNotFound() throws Exception {
        final UserModelInterface userAdminModel = new UserModel();
        userAdminModel.map(this.usersSupport.getAdminUser());
        when(this.sessionService.getUserModel()).thenReturn(userAdminModel);

        when(this.usersService.find(any(Integer.class))).thenReturn(null);

        mvc.perform(get("/v1/users/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andReturn();
    }

    /**
     * testDeleteSuccess
     */
    /**
     * testDeleteFailed
     */
    /**
     * testShowMeSuccess
     */
    @Test
    public void testShowMeSuccess() throws Exception {
        final UserModelInterface userModel = new UserModel();
        userModel.map(this.usersSupport.getAdminUser());
        when(this.sessionService.getUserModel()).thenReturn(userModel);
        when(this.sessionService.isUser()).thenReturn(true);

        mvc.perform(get("/v1/users/me")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new JsonSupport()
                        .with(id, userModel.getId())
                        .with(firstName, userModel.getFirstName())
                        .with(lastName, userModel.getLastName())
                        .with(surname, userModel.getSurname())
                        .with(email, userModel.getEmail())
                        .with(isAdmin, userModel.getIsAdmin())
                        .with(state, userModel.getState())
                        .build()
                ))
                .andReturn();
    }
    /**
     * testShowMeFailed
     */
    @Test
    public void testShowMeForbidden() throws Exception {
        when(this.sessionService.isUser()).thenReturn(false);


        mvc.perform(get("/v1/users/me")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();
    }
    /**
     * testShowMeWithSubjectsSuccess
     */

}
