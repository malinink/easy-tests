package easytests.integration.services;

import easytests.models.*;
import easytests.models.empty.IssueStandardModelEmpty;
import easytests.models.empty.ModelsListEmpty;
import easytests.models.empty.UserModelEmpty;
import easytests.services.SubjectsService;
import easytests.services.UsersService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author vkpankov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class SubjectsServiceTest {
    @Autowired
    private SubjectsService subjectsService;

    private SubjectModelInterface createSubjectModel(Integer id, String name) {

        final SubjectModelInterface subjectModel = new SubjectModel();
        subjectModel.setId(id);
        subjectModel.setName(name);

        return subjectModel;
    }

    @Test
    public void testSaveModel() throws Exception {

        final SubjectModelInterface subjectModel = new SubjectModel();
        subjectModel.setName("test111");

        this.subjectsService.save(subjectModel);

        final SubjectModelInterface foundedSubjectModel = this.subjectsService.find(subjectModel.getId());

        Assert.assertEquals(subjectModel, foundedSubjectModel);
    }


    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final SubjectModelInterface subjectModel = this.createSubjectModel(id, "test1" );


        final SubjectModelInterface foundedSubjectModel = this.subjectsService.find(id);

        Assert.assertEquals(subjectModel, foundedSubjectModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        final SubjectModelInterface subjectModel = this.subjectsService.find(id);

        Assert.assertEquals(null, subjectModel);
    }
}
