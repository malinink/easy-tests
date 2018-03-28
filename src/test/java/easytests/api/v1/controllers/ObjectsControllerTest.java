package easytests.api.v1.controllers;

import easytests.api.v1.mappers.ObjectsMapper;
import easytests.config.SwaggerRequestValidationConfig;
import easytests.core.models.UserModelInterface;
import easytests.core.options.UsersOptions;
import easytests.core.options.UsersOptionsInterface;
import easytests.core.options.builder.UsersOptionsBuilder;
import easytests.core.services.UsersService;
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
 * @author malinink
 */
@Import({ObjectsMapper.class, SwaggerRequestValidationConfig.class})
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ObjectsController.class, secure = false)
public class ObjectsControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsersService usersService;

    @MockBean
    private UsersOptionsBuilder usersOptionsBuilder;

    private UsersSupport usersSupport = new UsersSupport();

    @Test
    public void testViewReturnsData() throws Exception {
        final UserModelInterface userModel = this.usersSupport.getModelFixtureMock(0);
        when(this.usersOptionsBuilder.forAuth()).thenReturn(new UsersOptions());
        when(this.usersService.find(any(Integer.class), any(UsersOptionsInterface.class))).thenReturn(userModel);

        mvc.perform(get("/v1/objects/1/")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andReturn();
    }
}
