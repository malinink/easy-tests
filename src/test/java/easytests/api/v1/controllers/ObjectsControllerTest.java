package easytests.api.v1.controllers;

import easytests.api.v1.mappers.ObjectsMapper;
import easytests.config.SwaggerRequestValidationConfig;
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
 * @author malinink
 */
@Import({ObjectsMapper.class, SwaggerRequestValidationConfig.class})
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ObjectsController.class, secure = false)
public class ObjectsControllerTest {
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
    private UsersOptionsBuilder usersOptionsBuilder;

    private UsersSupport usersSupport = new UsersSupport();

    private SubjectsSupport subjectsSupport = new SubjectsSupport();

    @Test
    public void testListSuccess() throws Exception {
        final List<UserModelInterface> usersModels = new ArrayList<>();
        IntStream.range(0, 2).forEach(idx -> {
            final UserModel userModel = new UserModel();
            userModel.map(this.usersSupport.getEntityFixtureMock(1));
            usersModels.add(userModel);
        });
        when(this.usersService.findAll()).thenReturn(usersModels);

        mvc.perform(get("/v1/objects")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void testCreateSuccess() throws Exception {
        doAnswer(invocation -> {
            final UserModel userModel = (UserModel) invocation.getArguments()[0];
            userModel.setId(5);
            return null;
        }).when(this.usersService).save(any(UserModelInterface.class));

        mvc.perform(post("/v1/objects")
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
                .andExpect(status().is(201))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        new JsonSupport()
                                .with(id, 5)
                                .build()
                ))
                .andReturn();
    }

    @Test
    public void testCreateWithIdFailed() throws Exception {
        mvc.perform(post("/v1/objects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(id, 2)
                        .with(firstName, "firstName")
                        .with(lastName, "lastName")
                        .with(surname, "surname")
                        .with(email, "mail@fmail.com")
                        .with(isAdmin, true)
                        .with(state, 0)
                        .build()
                ))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void testCreateWithSubjectsFailed() throws Exception {
        mvc.perform(post("/v1/objects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(firstName, "firstName")
                        .with(lastName, "lastName")
                        .with(surname, "surname")
                        .with(email, "mail@fmail.com")
                        .with(isAdmin, true)
                        .with(state, 0)
                        .with(subjects, "[]")
                        .build()
                ))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void testUpdateSuccess() throws Exception {
        final UserModelInterface userModel = this.usersSupport.getModelFixtureMock(0);
        when(this.usersService.find(any(), any())).thenReturn(userModel);

        mvc.perform(put("/v1/objects")
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

    @Test
    public void testUpdateWithoutIdFailed() throws Exception {
        mvc.perform(put("/v1/objects")
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
                .andReturn();
    }

    @Test
    public void testUpdateWithSubjectsFailed() throws Exception {
        mvc.perform(put("/v1/objects")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(id, 1)
                        .with(firstName, "firstName")
                        .with(lastName, "lastName")
                        .with(surname, "surname")
                        .with(email, "mail@fmail.com")
                        .with(isAdmin, true)
                        .with(state, 0)
                        .with(subjects, "[]")
                        .build()
                ))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void testShowSuccess() throws Exception {
        final UserModelInterface userModel = new UserModel();
        userModel.map(this.usersSupport.getEntityFixtureMock(0));
        when(this.usersOptionsBuilder.forAuth()).thenReturn(new UsersOptions());
        when(this.usersService.find(any(Integer.class), any(UsersOptionsInterface.class))).thenReturn(userModel);

        mvc.perform(get("/v1/objects/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
    }

    @Test
    public void testShowFailed() throws Exception {
        when(this.usersOptionsBuilder.forAuth()).thenReturn(new UsersOptions());
        when(this.usersService.find(any(Integer.class), any(UsersOptionsInterface.class))).thenReturn(null);

        mvc.perform(get("/v1/objects/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void testShowWithSubjectsSuccess() throws Exception {
        final UserModelInterface userModel = new UserModel();
        userModel.map(this.usersSupport.getEntityFixtureMock(0));
        final List<SubjectModelInterface> subjectsModels = new ArrayList<>();
        IntStream.range(0, 2).forEach(idx -> {
            final SubjectModel subjectModel = new SubjectModel();
            subjectModel.map(this.subjectsSupport.getEntityFixtureMock(0));
            subjectsModels.add(subjectModel);
        });
        userModel.setSubjects(subjectsModels);
        when(this.usersOptionsBuilder.forAuth()).thenReturn(new UsersOptions());
        when(this.usersService.find(any(Integer.class), any(UsersOptionsInterface.class))).thenReturn(userModel);

        mvc.perform(get("/v1/objects/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
    }
}
