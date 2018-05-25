package easytests.api.v1.controllers;

import easytests.api.v1.mappers.UsersMapper;
import easytests.auth.services.AccessControlLayerServiceInterface;
import easytests.config.SwaggerRequestValidationConfig;
import easytests.core.entities.UserEntity;
import easytests.auth.services.SessionServiceInterface;
import easytests.core.models.*;
import easytests.core.options.builder.UsersOptionsBuilder;
import easytests.core.services.UsersService;
import easytests.support.JsonSupport;
import easytests.support.SubjectsSupport;
import easytests.support.UsersSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.Assert;
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
    private static String subjects = "subjects";

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

    @Test
     public void testCreateSuccess() throws Exception {
         doAnswer(invocation -> {
             final UserModel userModel = (UserModel) invocation.getArguments()[0];
             userModel.setId(5);
             return null;
         }).when(this.usersService).save(any(UserModelInterface.class));
         final ArgumentCaptor<UserModelInterface> userCaptor = ArgumentCaptor.forClass(UserModelInterface.class);
         mvc.perform(post("/v1/users")
                 .contentType(MediaType.APPLICATION_JSON)
                 .content(new JsonSupport()
                         .with(firstName, "FirstName")
                         .with(lastName, "LastName")
                         .with(surname, "SurName")
                         .with(email, "mail@mail.ru")
                         .with(isAdmin, true)
                         .with(state, 1)
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
        Assert.assertEquals(userCaptor.getValue().getFirstName(), "FirstName");
        Assert.assertEquals(userCaptor.getValue().getLastName(), "LastName");
        Assert.assertEquals(userCaptor.getValue().getSurname(), "SurName");
        Assert.assertEquals(userCaptor.getValue().getState(), (Integer) 1);
        Assert.assertEquals(userCaptor.getValue().getIsAdmin(), true);
    }


    @Test
    public void testCreateWithIdFailed() throws Exception {
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
    public void testCreateWithSubjectsFailed() throws Exception {
        mvc.perform(post("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(firstName, "firstName")
                        .with(lastName, "lastName")
                        .with(surname, "surname")
                        .with(email, "mail@mail.com")
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
     * testUpdateSuccess
     */
    /**
     * testUpdateWithoutIdFailed
     */
    /**
     * testUpdateWithSubjectsFailed
     */
    /**
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
