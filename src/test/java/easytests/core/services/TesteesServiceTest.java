package easytests.core.services;

import easytests.core.entities.TesteeEntity;
import easytests.core.mappers.TesteesMapper;
import easytests.core.models.TesteeModel;
import easytests.core.models.TesteeModelInterface;
import easytests.core.models.QuizModelInterface;
import easytests.core.options.TesteesOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import easytests.support.Entities;
import easytests.support.Models;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DoZor-80
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TesteesServiceTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private TesteesMapper testeesMapper;

    @Autowired
    private TesteesService testeesService;


    private TesteeModelInterface mapTesteeModel(TesteeEntity testeeEntity) {
        final TesteeModelInterface testeeModel = new TesteeModel();
        testeeModel.map(testeeEntity);
        return testeeModel;
    }

    private TesteeEntity mapTesteeEntity(TesteeModelInterface testeeModel) {
        final TesteeEntity testeeEntity = new TesteeEntity();
        testeeEntity.map(testeeModel);
        return testeeEntity;
    }

    private List<TesteeEntity> getTesteesEntities() {
        final List<TesteeEntity> testeesEntities = new ArrayList<>(2);
        final TesteeEntity testeeEntityFirst = Entities.createTesteeEntityMock(
                1,
                "FirstName1",
                "LastName1",
                "Surname1",
                301,
                1
        );
        final TesteeEntity testeeEntitySecond = Entities.createTesteeEntityMock(
                2,
                "FirstName2",
                "LastName2",
                "Surname2",
                302,
                2
        );
        testeesEntities.add(testeeEntityFirst);
        testeesEntities.add(testeeEntitySecond);
        return testeesEntities;
    }

    private List<TesteeModelInterface> getTesteesModels() {
        final List<TesteeModelInterface> testeesModels = new ArrayList<>(2);
        for (TesteeEntity testeeEntity: this.getTesteesEntities()) {
            testeesModels.add(this.mapTesteeModel(testeeEntity));
        }
        return testeesModels;
    }

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<TesteeEntity> testeesEntities = this.getTesteesEntities();
        given(this.testeesMapper.findAll()).willReturn(testeesEntities);

        final List<TesteeModelInterface> testeesModels = this.testeesService.findAll();

        Assert.assertEquals(this.getTesteesModels(), testeesModels);
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        given(this.testeesMapper.findAll()).willReturn(new ArrayList<>(0));

        final List<TesteeModelInterface> testeesModels = this.testeesService.findAll();

        Assert.assertEquals(0, testeesModels.size());
    }

    @Test
    public void testFindAllWithOptions() throws Exception {
        final List<TesteeEntity> testeesEntities = this.getTesteesEntities();
        final List<TesteeModelInterface> testeesModels = this.getTesteesModels();
        final TesteesOptionsInterface testeesOptions = Mockito.mock(TesteesOptionsInterface.class);
        given(this.testeesMapper.findAll()).willReturn(testeesEntities);
        given(testeesOptions.withRelations(Mockito.anyList())).willReturn(testeesModels);

        final List<TesteeModelInterface> foundedTesteesModels = this.testeesService.findAll(testeesOptions);

        verify(testeesOptions).withRelations(testeesModels);
        Assert.assertEquals(testeesModels, foundedTesteesModels);
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final TesteeEntity testeeEntity = Entities.createTesteeEntityMock(
                id,
                "NewFirstName",
                "NewLastName1",
                "NewSurname1",
                307,
                7
        );
        given(this.testeesMapper.find(id)).willReturn(testeeEntity);

        final TesteeModelInterface testeeModel = this.testeesService.find(id);

        Assert.assertEquals(this.mapTesteeModel(testeeEntity), testeeModel);
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        given(this.testeesMapper.find(id)).willReturn(null);

        final TesteeModelInterface testeeModel = this.testeesService.find(id);

        Assert.assertEquals(null, testeeModel);
    }

    @Test
    public void testFindWithOptions() throws Exception {
        final Integer id = 1;
        final TesteeEntity testeeEntity = Entities.createTesteeEntityMock(
                id,
                "NewFirstName",
                "NewLastName1",
                "NewSurname1",
                307,
                7
        );
        final TesteeModelInterface testeeModel = this.mapTesteeModel(testeeEntity);
        final TesteesOptionsInterface testeesOptions = Mockito.mock(TesteesOptionsInterface.class);
        given(this.testeesMapper.find(id)).willReturn(testeeEntity);
        given(testeesOptions.withRelations(testeeModel)).willReturn(testeeModel);

        final TesteeModelInterface foundedTesteeModel = this.testeesService.find(id, testeesOptions);

        Assert.assertEquals(testeeModel, foundedTesteeModel);
        verify(testeesOptions).withRelations(testeeModel);
    }

    @Test
    public void testFindByQuizPresentModel() throws Exception {
        final Integer quizId = 3;
        final TesteeEntity testeeEntity = Entities.createTesteeEntityMock(
                3,
                "FirstName",
                "LastName",
                "Surname",
                301,
                quizId);
        given(this.testeesMapper.findByQuizId(quizId)).willReturn(testeeEntity);

        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);
        Mockito.when(quizModel.getId()).thenReturn(quizId);

        final TesteeModelInterface testeeModel = this.testeesService.findByQuiz(quizModel);
        Assert.assertNotNull(testeeModel);
        Assert.assertEquals(this.mapTesteeModel(testeeEntity), testeeModel);
    }

    @Test
    public void testFindByQuizWithOptions() throws Exception {
        final Integer quizId = 3;
        final TesteeEntity testeeEntity = Entities.createTesteeEntityMock(
                3,
                "FirstName",
                "LastName",
                "Surname",
                301,
                quizId);
        final TesteeModelInterface testeeModel = this.mapTesteeModel(testeeEntity);

        final TesteesOptionsInterface testeesOptions = Mockito.mock(TesteesOptionsInterface.class);
        given(this.testeesMapper.findByQuizId(quizId)).willReturn(testeeEntity);
        given(testeesOptions.withRelations(testeeModel)).willReturn(testeeModel);

        final TesteeModelInterface foundedTesteeModel
                = this.testeesService.findByQuiz(testeeModel.getQuiz(), testeesOptions);

        verify(testeesOptions).withRelations(testeeModel);
        Assert.assertNotNull(foundedTesteeModel);
        Assert.assertEquals(testeeModel, foundedTesteeModel);
    }

    @Test
    public void testFindByQuizAbsentModel() throws Exception {
        final Integer quizId = 5;
        given(this.testeesMapper.findByQuizId(quizId)).willReturn(null);

        final QuizModelInterface quizModel = Mockito.mock(QuizModelInterface.class);
        Mockito.when(quizModel.getId()).thenReturn(quizId);

        final TesteeModelInterface testeeModel = this.testeesService.findByQuiz(quizModel);
        Assert.assertNull(testeeModel);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {
        final TesteeModelInterface testeeModel = Models.createTesteeModel(
                null,
                "FirstName",
                "LastName",
                "Surname",
                301,
                1
        );
        doAnswer(invocation -> {
            final TesteeEntity testeeEntity = (TesteeEntity) invocation.getArguments()[0];
            testeeEntity.setId(5);
            return null;
        }).when(this.testeesMapper).insert(Mockito.any(TesteeEntity.class));

        this.testeesService.save(testeeModel);

        // TODO verify(this.testeesMapper, times(1)).insert(this.mapTesteeEntity(testeeModel));
        Assert.assertEquals((Integer) 5, testeeModel.getId());
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final TesteeModelInterface testeeModel = Models.createTesteeModel(
                1,
                "FirstName",
                "LastName",
                "Surname",
                301,
                1
        );

        this.testeesService.save(testeeModel);

        verify(this.testeesMapper, times(1)).update(this.mapTesteeEntity(testeeModel));
    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final TesteeModelInterface testeeModel = Models.createTesteeModel(
                null,
                "FirstName",
                "LastName",
                "Surname",
                301,
                1
        );
        final TesteesOptionsInterface testeesOptions = Mockito.mock(TesteesOptionsInterface.class);

        this.testeesService.save(testeeModel, testeesOptions);

        verify(testeesOptions).saveWithRelations(testeeModel);
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final TesteeModelInterface testeeModel = Models.createTesteeModel(
                1,
                "FirstName",
                "LastName",
                "Surname",
                301,
                1
        );

        this.testeesService.delete(testeeModel);

        verify(this.testeesMapper, times(1)).delete(this.mapTesteeEntity(testeeModel));
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final TesteeModelInterface testeeModel = Models.createTesteeModel(
                null,
                "FirstName",
                "LastName",
                "Surname",
                301,
                1
        );

        exception.expect(DeleteUnidentifiedModelException.class);
        this.testeesService.delete(testeeModel);
    }

    @Test
    public void testDeleteWithOptions() throws Exception {
        final TesteeModelInterface testeeModel = Models.createTesteeModel(
                1,
                "FirstName",
                "LastName",
                "Surname",
                301,
                1
        );
        final TesteesOptionsInterface testeesOptions = Mockito.mock(TesteesOptionsInterface.class);

        this.testeesService.delete(testeeModel, testeesOptions);

        verify(testeesOptions).deleteWithRelations(testeeModel);
    }
}
