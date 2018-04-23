package easytests.api.v1.controllers;

import easytests.api.v1.mappers.UsersMapper;
import easytests.config.SwaggerRequestValidationConfig;
import easytests.core.options.builder.UsersOptionsBuilder;
import easytests.core.services.UsersService;
import easytests.support.SubjectsSupport;
import easytests.support.UsersSupport;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


/**
 * @author SvetlanaTselikova
 */
@Import({UsersMapper.class, SwaggerRequestValidationConfig.class})
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UsersController.class, secure = false)

public class UsersControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsersService usersService;

    @MockBean
    private UsersOptionsBuilder usersOptionsBuilder;

    private UsersSupport usersSupport = new UsersSupport();

    private SubjectsSupport subjectsSupport = new SubjectsSupport();

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
