package easytests.integration.services;

import easytests.models.TesteeModelInterface;
import easytests.models.QuizModelInterface;
import easytests.options.TesteesOptions;
import easytests.options.QuizzesOptions;
import easytests.services.TesteesService;
import easytests.support.Models;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author DoZor-80
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class TesteesServiceTest {
    @Autowired
    private TesteesService testeesService;

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final TesteeModelInterface testeeModel = Models.createTesteeModel(
                id,
                "FirstName1",
                "LastName1",
                "Surname1",
                301,
                1
        );

        final TesteeModelInterface foundedTesteeModel = this.testeesService.find(id);

        Assert.assertEquals(testeeModel, foundedTesteeModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        final TesteeModelInterface testeeModel = this.testeesService.find(id);

        Assert.assertEquals(null, testeeModel);
    }
    @Test
    public void testFindWithOptions() throws Exception {
        final Integer id = 1;
        final Integer quizId = 1;

        final TesteeModelInterface testeeModel = Models.createTesteeModel(id, "FirstName1", "LastName1", "Surname1", 301, quizId);
        final QuizModelInterface quizModel = Models.createQuizModel(quizId, "testInvite", 2);
        testeeModel.setQuiz(quizModel);

        final TesteeModelInterface foundedTesteeModel
                = this.testeesService.find(id, new TesteesOptions().withQuiz(new QuizzesOptions()));

        Assert.assertEquals(testeeModel, foundedTesteeModel);
        Assert.assertEquals(quizModel, foundedTesteeModel.getQuiz());
    }

    @Test
    public void testSaveInsertsModel() throws Exception {
        final Integer id = this.testeesService.findAll().size() + 1;
        final TesteeModelInterface testeeModel = Models.createTesteeModel(null, "FirstName2", "LastName2", "Surname2", 302, 2);

        this.testeesService.save(testeeModel);
        TesteeModelInterface foundedTesteeModel = this.testeesService.find(id);

        Assert.assertEquals(testeeModel, foundedTesteeModel);
    }

    @Test
    public void testSaveUpdatesModel() throws Exception {
        final Integer id = 1;
        final TesteeModelInterface testeeModel = Models.createTesteeModel(id, "FirstName1", "LastName1", "Surname1", 301, 2);
        Assert.assertNotEquals(testeeModel, this.testeesService.find(id));

        this.testeesService.save(testeeModel);

        Assert.assertEquals(testeeModel, this.testeesService.find(id));
    }

}
