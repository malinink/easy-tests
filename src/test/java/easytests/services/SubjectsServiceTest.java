package easytests.services;

import easytests.entities.SubjectEntity;
import easytests.mappers.SubjectsMapper;
import easytests.models.IssueStandardModelInterface;
import easytests.models.SubjectModel;
import easytests.models.SubjectModelInterface;
import easytests.models.UserModelInterface;
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
 * @author vkpankov
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SubjectsServiceTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @MockBean
    private SubjectsMapper subjectsMapper;

    @Autowired
    private SubjectsService subjectsService;

    private SubjectModelInterface createSubjectModel(Integer id, String name, Integer userId, Integer issueStandardId) {

        final SubjectModelInterface subjectModel = new SubjectModel();
        subjectModel.setId(id);
        subjectModel.setName("test");

        final UserModelInterface userModel = Mockito.mock(UserModelInterface.class);
        Mockito.when(userModel.getId()).thenReturn(userId);

        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(issueStandardId);

        subjectModel.setUser(userModel);
        subjectModel.setIssueStandard(issueStandardModel);

        return subjectModel;

    }

    private SubjectEntity createSubjectEntityMock(Integer id, String name, Integer userId, Integer issueStandardId) {

        final SubjectEntity subjectEntity = Mockito.mock(SubjectEntity.class);

        Mockito.when(subjectEntity.getId()).thenReturn(id);
        Mockito.when(subjectEntity.getName()).thenReturn(name);
        Mockito.when(subjectEntity.getUserId()).thenReturn(userId);
        Mockito.when(subjectEntity.getIssueStandardId()).thenReturn(issueStandardId);

        return subjectEntity;

    }

    private SubjectModelInterface mapSubjectModel(SubjectEntity subjectEntity) {

        final SubjectModelInterface subjectModel = new SubjectModel();
        subjectModel.map(subjectEntity);
        return subjectModel;

    }

    private SubjectEntity mapSubjectEntity(SubjectModelInterface subjectModel) {

        final SubjectEntity subjectEntity = new SubjectEntity();
        subjectEntity.map(subjectModel);
        return subjectEntity;

    }

    @Test
    public void testFindAllPresentList() throws Exception {

        final List<SubjectEntity> subjectsEntities = new ArrayList<>(2);
        final SubjectEntity subjectEntityFirst = this.createSubjectEntityMock(1, "test1",1,1);
        final SubjectEntity subjectEntitySecond = this.createSubjectEntityMock(2, "test2",2,1);

        subjectsEntities.add(subjectEntityFirst);
        subjectsEntities.add(subjectEntitySecond);

        given(this.subjectsMapper.findAll()).willReturn(subjectsEntities);


        final List<SubjectModelInterface> subjectsModels = this.subjectsService.findAll();

        Assert.assertEquals(2, subjectsModels.size());
        Assert.assertEquals(subjectsModels.get(0), this.mapSubjectModel(subjectEntityFirst));
        Assert.assertEquals(subjectsModels.get(1), this.mapSubjectModel(subjectEntitySecond));

    }

    @Test
    public void testFindAllAbsentList() throws Exception {

        given(this.subjectsMapper.findAll()).willReturn(new ArrayList<>(0));

        final List<SubjectModelInterface> subjectsModels = this.subjectsService.findAll();

        Assert.assertEquals(0, subjectsModels.size());

    }

    @Test
    public void testFindPresentModel() throws Exception {

        final Integer id = 1;
        final SubjectEntity subjectEntity = this.createSubjectEntityMock(id, "test",1,1);
        given(this.subjectsMapper.find(id)).willReturn(subjectEntity);

        final SubjectModelInterface subjectModel = this.subjectsService.find(id);

        Assert.assertEquals(this.mapSubjectModel(subjectEntity), subjectModel);

    }

    @Test
    public void testFindAbsentModel() throws Exception {

        final Integer id = 10;
        given(this.subjectsMapper.find(id)).willReturn(null);

        final SubjectModelInterface subjectModel = this.subjectsService.find(id);

        Assert.assertEquals(null, subjectModel);

    }

    @Test
    public void testSaveCreatesEntity() throws Exception {

        final SubjectModelInterface subjectModel = this.createSubjectModel(null, "test",1,1);

        this.subjectsService.save(subjectModel);

        verify(this.subjectsMapper, times(1)).insert(this.mapSubjectEntity(subjectModel));

    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {

        final SubjectModelInterface userModel = this.createSubjectModel(1, "test",1,1);

        this.subjectsService.save(userModel);

        verify(this.subjectsMapper, times(1)).update(this.mapSubjectEntity(userModel));

    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {

        final SubjectModelInterface subjectModel = this.createSubjectModel(1, "test",1,1);

        this.subjectsService.delete(subjectModel);

        verify(this.subjectsMapper, times(1)).delete(this.mapSubjectEntity(subjectModel));

    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {

        final SubjectModelInterface subjectModel = this.createSubjectModel(null, "test",1,1);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.subjectsService.delete(subjectModel);

    }
}
