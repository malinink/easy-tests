package easytests.api.v1.controllers;

import easytests.api.v1.mappers.SubjectsMapper;
import easytests.auth.services.AccessControlLayerServiceInterface;
import easytests.config.SwaggerRequestValidationConfig;
import easytests.core.models.*;
import easytests.core.models.empty.UserModelEmpty;
import easytests.core.options.builder.SubjectsOptionsBuilder;
import easytests.core.services.SubjectsServiceInterface;
import easytests.core.services.UsersServiceInterface;
import easytests.support.SubjectsSupport;
import easytests.support.JsonSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author VeronikaRevjakina
 */
@Import({SubjectsMapper.class, SwaggerRequestValidationConfig.class})
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SubjectsController.class, secure = false)
public class SubjectsControllerTest {
    private static final String id = "id";
    private static final String name = "name";
    private static final String description = "description";
    private static final String user = "user";
    private static final String userIdParamValue = "2";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SubjectsServiceInterface subjectsService;

    @MockBean
    private UsersServiceInterface usersService;


    @Autowired
    @Qualifier("SubjectsMapperV1")
    private SubjectsMapper subjectsMapper;

    @MockBean
    private AccessControlLayerServiceInterface acl;

    @MockBean
    SubjectsOptionsBuilder subjectsOptionsBuilder;

    private SubjectsSupport subjectsSupport = new SubjectsSupport();

    @Test
    public void testListSuccess() throws Exception {
        final List<SubjectModelInterface> subjectsModels = new ArrayList<>();
        IntStream.range(0, 2).forEach(idx -> {
            final SubjectModel subjectModel = new SubjectModel();
            subjectModel.map(this.subjectsSupport.getEntityFixtureMock(idx));
            subjectsModels.add(subjectModel);
        });


        int userIdParamValue = 1;

        when(this.usersService.find(userIdParamValue))
                .thenReturn(new UserModelEmpty(userIdParamValue));
        when(this.subjectsService.findByUser(new UserModelEmpty(userIdParamValue)))
                .thenReturn(subjectsModels);
        when(this.acl.hasAccess(any(UserModelInterface.class))).thenReturn(true);

        mvc.perform(get("/v1/subjects?userId={userIdParamValue}", userIdParamValue)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new JsonSupport()
                        .with(new JsonSupport()
                                .with(id, subjectsModels.get(0).getId())
                                .with(name, subjectsModels.get(0).getName())
                                .with(description,subjectsModels.get(0).getDescription())
                                .with(user, new JsonSupport().with(id, subjectsModels.get(0).getUser().getId())))
                        .with(new JsonSupport()
                                .with(id, subjectsModels.get(1).getId())
                                .with(name, subjectsModels.get(1).getName())
                                .with(description,subjectsModels.get(1).getDescription())
                                .with(user, new JsonSupport().with(id, subjectsModels.get(1).getUser().getId())))
                        .build()
                ))
                .andReturn();

    }

    @Test
    public void testListNotFound() throws Exception {
        int userIdParamValue = 5;

        this.mvc.perform(get("/v1/subjects?userId={userIdParamValue}", userIdParamValue)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testListForbidden() throws Exception {
        int userIdParamValue = 1;

        when(this.usersService.find(userIdParamValue))
                .thenReturn(new UserModelEmpty(userIdParamValue));
        when(this.acl.hasAccess(any(UserModelInterface.class))).thenReturn(false);

        this.mvc.perform(get("/v1/subjects?userId={userIdParamValue}", userIdParamValue)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();

    }
    /**
     * create
     */
    /**
     * update
     */
    /**
     * show(subjectId)
     */
    /**
     * delete(subjectId)
     */
    
}
