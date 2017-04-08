package easytests.services;

import easytests.entities.TesteeEntity;
import easytests.mappers.TesteesMapper;
import easytests.models.TesteeModel;
import easytests.models.TesteeModelInterface;
import easytests.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.*;
import static org.mockito.BDDMockito.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

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

    private TesteeModelInterface createTesteeModel(Integer id, String firstName, String lastName, String surname, Integer groupNumber) {
        final TesteeModelInterface testeeModel = new TesteeModel();
        testeeModel.setId(id);
        testeeModel.setFirstName(firstName);
        testeeModel.setLastName(lastName);
        testeeModel.setSurname(surname);
        testeeModel.setGroupNumber(groupNumber);
        return testeeModel;
    }

    private TesteeEntity createTesteeEntityMock(Integer id, String firstName, String lastName, String surname, Integer groupNumber) {
        final TesteeEntity testeeEntity = Mockito.mock(TesteeEntity.class);
        Mockito.when(testeeEntity.getId()).thenReturn(id);
        Mockito.when(testeeEntity.getFirstName()).thenReturn(firstName);
        Mockito.when(testeeEntity.getLastName()).thenReturn(lastName);
        Mockito.when(testeeEntity.getSurname()).thenReturn(surname);
        Mockito.when(testeeEntity.getGroupNumber()).thenReturn(groupNumber);
        return testeeEntity;
    }

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

    @Test
    public void testFindAllPresentList() throws Exception {
        final List<TesteeEntity> testeesEntities = new ArrayList<>(2);
        final TesteeEntity testeeEntityFirst = this.createTesteeEntityMock(1, "FirstName1", "LastName1", "Surname1",307);
        final TesteeEntity testeeEntitySecond = this.createTesteeEntityMock(2, "FirstName2", "LastName2", "Surname2",308);
        testeesEntities.add(testeeEntityFirst);
        testeesEntities.add(testeeEntitySecond);
        given(this.testeesMapper.findAll()).willReturn(testeesEntities);

        final List<TesteeModelInterface> testeesModels = this.testeesService.findAll();

        Assert.assertEquals(2, testeesModels.size());
        Assert.assertEquals(testeesModels.get(0), this.mapTesteeModel(testeeEntityFirst));
        Assert.assertEquals(testeesModels.get(1), this.mapTesteeModel(testeeEntitySecond));
    }

    @Test
    public void testFindAllAbsentList() throws Exception {
        given(this.testeesMapper.findAll()).willReturn(new ArrayList<>(0));

        final List<TesteeModelInterface> testeesModels = this.testeesService.findAll();

        Assert.assertEquals(0, testeesModels.size());
    }

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final TesteeEntity testeeEntity = this.createTesteeEntityMock(id, "NewFirstName", "NewLastName", "NewSurname", 307);
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
    public void testSaveCreatesEntity() throws Exception {
        final TesteeModelInterface testeeModel = this.createTesteeModel(null, "FirstName", "LastName", "Surname", 307);

        this.testeesService.save(testeeModel);

        verify(this.testeesMapper, times(1)).insert(this.mapTesteeEntity(testeeModel));
    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {
        final TesteeModelInterface testeeModel = this.createTesteeModel(1, "FirstName", "LastName", "Surname", 307);

        this.testeesService.save(testeeModel);

        verify(this.testeesMapper, times(1)).update(this.mapTesteeEntity(testeeModel));
    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {
        final TesteeModelInterface testeeModel = this.createTesteeModel(1, "FirstName", "LastName", "Surname", 307);

        this.testeesService.delete(testeeModel);

        verify(this.testeesMapper, times(1)).delete(this.mapTesteeEntity(testeeModel));
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {
        final TesteeModelInterface testeeModel = this.createTesteeModel(null, "FirstName", "LastName", "Surname", 307);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.testeesService.delete(testeeModel);
    }
}
