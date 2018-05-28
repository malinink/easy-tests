package easytests.api.v1.controllers;

import easytests.api.v1.mappers.UsersMapper;
import easytests.auth.services.AccessControlLayerServiceInterface;
import easytests.auth.services.SessionServiceInterface;
import easytests.config.SwaggerRequestValidationConfig;
import easytests.core.entities.UserEntity;
import easytests.core.models.*;
import easytests.core.options.UsersOptions;
import easytests.core.options.UsersOptionsInterface;
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
import org.mockito.ArgumentCaptor;
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
    private AccessControlLayerServiceInterface acl;

    @MockBean
    private UsersOptionsBuilder usersOptionsBuilder;

    private UsersSupport usersSupport = new UsersSupport();

    private SubjectsSupport subjectsSupport = new SubjectsSupport();

    @MockBean
    private SessionServiceInterface sessionService;

    @Test
    public void testListSuccess() throws Exception {
        final UserModelInterface userAdminModel = new UserModel();
        userAdminModel.map(this.usersSupport.getAdminUser());
        when(this.sessionService.isUser()).thenReturn(true);
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
        when(this.sessionService.isUser()).thenReturn(true);
        when(this.sessionService.getUserModel()).thenReturn(userNotAdminModel);

        mvc.perform(get("/v1/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testCreateSuccess() throws Exception {
        final UserModelInterface userAdminModel = new UserModel();
        userAdminModel.map(this.usersSupport.getAdminUser());
        when(this.sessionService.isUser()).thenReturn(true);
        when(this.sessionService.getUserModel()).thenReturn(userAdminModel);

        final UserModelInterface userAdditionalModel = this.usersSupport.getModelAdditionalMock(0);
        doAnswer(invocation -> {
            final UserModel userModel = (UserModel) invocation.getArguments()[0];
            userModel.setId(5);
            return null;
        }).when(this.usersService).save(any(UserModelInterface.class));
        final ArgumentCaptor<UserModelInterface> userCaptor = ArgumentCaptor.forClass(UserModelInterface.class);
        mvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(firstName, userAdditionalModel.getFirstName())
                        .with(lastName, userAdditionalModel.getLastName())
                        .with(surname, userAdditionalModel.getSurname())
                        .with(email, userAdditionalModel.getEmail())
                        .with(isAdmin, userAdditionalModel.getIsAdmin())
                        .with(state, userAdditionalModel.getState())
                        .build()
                ))
                .andExpect(status().is(201))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        new JsonSupport()
                                .with(id, 5)
                                .build()
                ))
                .andReturn();
        verify(this.usersService, times(1)).save(userCaptor.capture());
        when(userAdditionalModel.getPassword()).thenReturn(userCaptor.getValue().getPassword()); // When, because userAdditionalModel is a MOCK
        this.usersSupport.assertModelsEqualsWithoutIdandSubjects(userCaptor.getValue(), userAdditionalModel);
    }

    @Test
    public void testCreateWithIdFailed() throws Exception {
        final UserModelInterface userAdminModel = new UserModel();
        userAdminModel.map(this.usersSupport.getAdminUser());
        when(this.sessionService.isUser()).thenReturn(true);
        when(this.sessionService.getUserModel()).thenReturn(userAdminModel);

        mvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(id, 2)
                        .with(firstName, "firstName")
                        .with(lastName, "lastName")
                        .with(surname, "surname")
                        .with(email, "mail@mail.com")
                        .with(isAdmin, true)
                        .with(state, 0)
                        .build()
                ))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testCreateNotAdminForbidden() throws Exception {
        final UserModelInterface userNotAdminModel = new UserModel();
        userNotAdminModel.map(this.usersSupport.getNotAdminUser());
        when(this.sessionService.isUser()).thenReturn(true);
        when(this.sessionService.getUserModel()).thenReturn(userNotAdminModel);

        mvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(firstName, "firstName")
                        .with(lastName, "lastName")
                        .with(surname, "surname")
                        .with(email, "mail@mail.com")
                        .with(isAdmin, true)
                        .with(state, 0)
                        .build()
                ))
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testCreateWithEmailFailed() throws Exception {
        final UserModelInterface userAdminModel = new UserModel();
        userAdminModel.map(this.usersSupport.getAdminUser());
        when(this.sessionService.isUser()).thenReturn(true);
        when(this.sessionService.getUserModel()).thenReturn(userAdminModel);
        final UserModelInterface userModel = this.usersSupport.getModelFixtureMock(0);
        when(this.usersService.findByEmail(userModel.getEmail())).thenReturn(userModel); // Because service cant find user by email.
        mvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(firstName, "firstName")
                        .with(lastName, "lastName")
                        .with(surname, "surname")
                        .with(email, userModel.getEmail())
                        .with(isAdmin, true)
                        .with(state, 0)
                        .build()
                ))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testCreateNotAUserForbidden() throws Exception {
        final UserModelInterface userNotAdminModel = new UserModel();
        userNotAdminModel.map(this.usersSupport.getNotAdminUser());
        when(this.sessionService.isUser()).thenReturn(false);
        when(this.sessionService.getUserModel()).thenReturn(userNotAdminModel);

        mvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(firstName, "firstName")
                        .with(lastName, "lastName")
                        .with(surname, "surname")
                        .with(email, "mail@mail.com")
                        .with(isAdmin, true)
                        .with(state, 0)
                        .build()
                ))
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testShowSuccess() throws Exception {
        final UserModelInterface userAdminModel = new UserModel();
        userAdminModel.map(this.usersSupport.getAdminUser());
        when(this.sessionService.isUser()).thenReturn(true);
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
        when(this.sessionService.isUser()).thenReturn(true);
        when(this.sessionService.getUserModel()).thenReturn(userAdminModel);

        when(this.usersService.find(any(Integer.class))).thenReturn(null);

        mvc.perform(get("/v1/users/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testUpdateSuccess() throws Exception {
        final UserModelInterface userModelforUpdate = this.usersSupport.getModelAdditionalMock(1);
        final UserEntity userEntity = this.usersSupport.getEntityFixtureMock(0);
        final UserModelInterface userModel = new UserModel();
        userModel.map(userEntity);
        userModel.setPassword(userModelforUpdate.getPassword());

        when(this.usersService.find(any())).thenReturn(userModel);
        final UserModelInterface userAdminModel = new UserModel();
        userAdminModel.map(this.usersSupport.getAdminUser());
        when(this.sessionService.isUser()).thenReturn(true);
        when(this.sessionService.getUserModel()).thenReturn(userAdminModel);

        final ArgumentCaptor<UserModelInterface> userModelCaptor = ArgumentCaptor.forClass(UserModelInterface.class);
        mvc.perform(put("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(id, userModelforUpdate.getId())
                        .with(firstName, userModelforUpdate.getFirstName())
                        .with(lastName, userModelforUpdate.getLastName())
                        .with(surname, userModelforUpdate.getSurname())
                        .with(email, userModelforUpdate.getEmail())
                        .with(isAdmin, userModelforUpdate.getIsAdmin())
                        .with(state, userModelforUpdate.getState())
                        .build()
                ))
                .andExpect(status().is(200))
                .andExpect(content().string(""))
                .andReturn();

        verify(this.usersService, times(1)).save(userModelCaptor.capture());
        this.usersSupport.assertEquals(userModelforUpdate, userModelCaptor.getValue());
    }
    /**
     * testUpdateWithoutIdFailed
     */

    @Test
    public void testUpdateWithoutIdFailed() throws Exception {
        final UserModelInterface userAdminModel = new UserModel();
        userAdminModel.map(this.usersSupport.getAdminUser());
        when(this.sessionService.isUser()).thenReturn(true);
        when(this.sessionService.getUserModel()).thenReturn(userAdminModel);

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

    @Test
    public void testUpdateForbidden() throws Exception {
        final UserModelInterface userNotAdminModel = new UserModel();
        userNotAdminModel.map(this.usersSupport.getNotAdminUser());
        when(this.sessionService.getUserModel()).thenReturn(userNotAdminModel);

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
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testUpdateNotFound() throws Exception {
        when(this.usersService.find(any(Integer.class))).thenReturn(null);
        final UserModelInterface userAdminModel = new UserModel();
        userAdminModel.map(this.usersSupport.getAdminUser());
        when(this.sessionService.isUser()).thenReturn(true);
        when(this.sessionService.getUserModel()).thenReturn(userAdminModel);

        mvc.perform(put("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(id, 7)
                        .with(firstName, "firstName")
                        .with(lastName, "lastName")
                        .with(surname, "surname")
                        .with(email, "mail@fmail.com")
                        .with(isAdmin, true)
                        .with(state, 0)
                        .build()
                ))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testDeleteSuccess() throws Exception {
        final UserModelInterface userAdminModel = new UserModel();
        userAdminModel.map(this.usersSupport.getAdminUser());
        when(this.sessionService.isUser()).thenReturn(true);
        when(this.sessionService.getUserModel()).thenReturn(userAdminModel);

        final UserModelInterface userModel = new UserModel();
        userModel.map(this.usersSupport.getEntityFixtureMock(0));
        final UsersOptionsInterface usersOptionsForDelete = new UsersOptions();
        when(this.usersOptionsBuilder.forDelete()).thenReturn(usersOptionsForDelete);
        when(this.usersService.find(any(Integer.class), eq(usersOptionsForDelete))).thenReturn(userModel);

        mvc.perform(delete("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andReturn();
        verify(this.usersService, times(1)).delete(userModel, usersOptionsForDelete);
    }

    @Test
    public void testDeleteForbidden() throws Exception {
        final UserModelInterface userNotAdminModel = new UserModel();
        userNotAdminModel.map(this.usersSupport.getNotAdminUser());
        when(this.sessionService.getUserModel()).thenReturn(userNotAdminModel);

        mvc.perform(delete("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        final UserModelInterface userAdminModel = new UserModel();
        userAdminModel.map(this.usersSupport.getAdminUser());
        when(this.sessionService.isUser()).thenReturn(true);
        when(this.sessionService.getUserModel()).thenReturn(userAdminModel);

        final UsersOptionsInterface usersOptionsForDelete = new UsersOptions();
        when(this.usersOptionsBuilder.forDelete()).thenReturn(usersOptionsForDelete);
        when(this.usersService.find(any(Integer.class), eq(usersOptionsForDelete))).thenReturn(null);

        mvc.perform(delete("/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andReturn();
    }

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

    @Test
    public void testShowMeForbidden() throws Exception {
        when(this.sessionService.isUser()).thenReturn(false);


        mvc.perform(get("/v1/users/me")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andReturn();
    }

}
