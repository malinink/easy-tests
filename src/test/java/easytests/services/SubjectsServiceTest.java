package easytests.services;

import easytests.entities.SubjectEntity;
import easytests.mappers.SubjectsMapper;
import easytests.models.IssueStandardModelInterface;
import easytests.models.SubjectModel;
import easytests.models.SubjectModelInterface;
import easytests.models.UserModelInterface;
import easytests.options.SubjectsOptionsInterface;
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

    private SubjectModelInterface createSubjectModel(Integer id, String name,
                                                     String description, Integer userId, Integer issueStandardId) {

        final SubjectModelInterface subjectModel = new SubjectModel();
        subjectModel.setId(id);
        subjectModel.setName(name);
        subjectModel.setDescription(description);

        final UserModelInterface userModel = Mockito.mock(UserModelInterface.class);
        Mockito.when(userModel.getId()).thenReturn(userId);

        final IssueStandardModelInterface issueStandardModel = Mockito.mock(IssueStandardModelInterface.class);
        Mockito.when(issueStandardModel.getId()).thenReturn(issueStandardId);

        subjectModel.setUser(userModel);
        subjectModel.setIssueStandard(issueStandardModel);

        return subjectModel;

    }

    private SubjectEntity createSubjectEntityMock(Integer id, String name, String description, Integer userId) {

        final SubjectEntity subjectEntity = Mockito.mock(SubjectEntity.class);

        Mockito.when(subjectEntity.getId()).thenReturn(id);
        Mockito.when(subjectEntity.getName()).thenReturn(name);
        Mockito.when(subjectEntity.getDescription()).thenReturn(description);
        Mockito.when(subjectEntity.getUserId()).thenReturn(userId);

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

    private List<SubjectEntity> getSubjectsEntities() {
        final List<SubjectEntity> subjectsEntities = new ArrayList<>(2);
        final SubjectEntity subjectEntityFirst = this.createSubjectEntityMock(1, "test1", "description1", 1);
        final SubjectEntity subjectEntitySecond = this.createSubjectEntityMock(2,  "test2", "description2", 2);
        subjectsEntities.add(subjectEntityFirst);
        subjectsEntities.add(subjectEntitySecond);
        return subjectsEntities;
    }

    private List<SubjectModelInterface> getUsersModels() {
        final List<SubjectModelInterface> subjectsModels = new ArrayList<>(2);
        for (SubjectEntity subjectEntity : this.getSubjectsEntities()) {
            subjectsModels.add(this.mapSubjectModel(subjectEntity));
        }
        return subjectsModels;
    }

    @Test
    public void testFindAllPresentList() throws Exception {

        final List<SubjectEntity> subjectsEntities = getSubjectsEntities();

        given(this.subjectsMapper.findAll()).willReturn(subjectsEntities);

        final List<SubjectModelInterface> subjectsModels = this.subjectsService.findAll();

        Assert.assertEquals(2, subjectsModels.size());
        Assert.assertEquals(subjectsModels.get(0), this.mapSubjectModel(subjectsEntities.get(0)));
        Assert.assertEquals(subjectsModels.get(1), this.mapSubjectModel(subjectsEntities.get(1)));

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
        final SubjectEntity subjectEntity = this.createSubjectEntityMock(id, "test", "description", 1);
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
    public void testFindByUser() throws Exception {
        final Integer userId = 7;
        final UserModelInterface userModel = Mockito.mock(UserModelInterface.class);
        final SubjectEntity subjectEntityFirst = this.createSubjectEntityMock(3, "test3", "description3", userId);
        final SubjectEntity subjectEntitySecond = this.createSubjectEntityMock(12, "test12", "description12", userId);
        final List<SubjectEntity> subjectsEntities = new ArrayList<>();
        subjectsEntities.add(subjectEntityFirst);
        subjectsEntities.add(subjectEntitySecond);
        Mockito.when(userModel.getId()).thenReturn(userId);
        given(this.subjectsMapper.findByUserId(userId)).willReturn(subjectsEntities);
        final List<SubjectModelInterface> subjectsModels = new ArrayList<>();
        subjectsModels.add(this.mapSubjectModel(subjectEntityFirst));
        subjectsModels.add(this.mapSubjectModel(subjectEntitySecond));

        final List<SubjectModelInterface> foundedSubjectsModels = this.subjectsService.findByUser(userModel);

        verify(this.subjectsMapper).findByUserId(userId);
        Assert.assertEquals(subjectsModels, foundedSubjectsModels);
    }

    @Test
    public void testFindByUserWithOptions() throws Exception {
        final Integer userId = 7;
        final UserModelInterface userModel = Mockito.mock(UserModelInterface.class);
        final SubjectEntity subjectEntityFirst = this.createSubjectEntityMock(3, "test3", "description3", userId);
        final SubjectEntity subjectEntitySecond = this.createSubjectEntityMock(12, "test12", "description12", userId);
        final List<SubjectEntity> subjectsEntities = new ArrayList<>();
        subjectsEntities.add(subjectEntityFirst);
        subjectsEntities.add(subjectEntitySecond);
        given(userModel.getId()).willReturn(userId);
        given(this.subjectsMapper.findByUserId(userId)).willReturn(subjectsEntities);
        final List<SubjectModelInterface> subjectsModels = new ArrayList<>();
        subjectsModels.add(this.mapSubjectModel(subjectEntityFirst));
        subjectsModels.add(this.mapSubjectModel(subjectEntitySecond));
        final SubjectsOptionsInterface subjectOptions = Mockito.mock(SubjectsOptionsInterface.class);
        given(subjectOptions.withRelations(subjectsModels)).willReturn(subjectsModels);

        final List<SubjectModelInterface> foundedSubjectsModels =
                this.subjectsService.findByUser(userModel, subjectOptions);

        verify(this.subjectsMapper).findByUserId(userId);
        verify(subjectOptions).withRelations(subjectsModels);
        Assert.assertEquals(subjectsModels, foundedSubjectsModels);
    }

    @Test
    public void testSaveCreatesEntity() throws Exception {

        final SubjectModelInterface subjectModel = this.createSubjectModel(null, "test", "description", 1, 1);

        this.subjectsService.save(subjectModel);

        verify(this.subjectsMapper, times(1)).insert(this.mapSubjectEntity(subjectModel));

    }

    @Test
    public void testSaveUpdatesEntity() throws Exception {

        final SubjectModelInterface subjectModel = this.createSubjectModel(1, "test", "description", 1, 1);

        this.subjectsService.save(subjectModel);

        verify(this.subjectsMapper, times(1)).update(this.mapSubjectEntity(subjectModel));

    }

    @Test
    public void testSaveEntitiesList() throws Exception {

        final SubjectModelInterface subjectModelFirst = this.createSubjectModel(null, "test1", "description1", 1, 1);
        final SubjectModelInterface subjectModelSecond = this.createSubjectModel(null, "test2", "description2", 2, 2);

        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);

        final List<SubjectModelInterface> subjectsModels = new ArrayList<>();
        subjectsModels.add(subjectModelFirst);
        subjectsModels.add(subjectModelSecond);

        final SubjectsServiceInterface subjectsServiceSpy = Mockito.spy(subjectsService);

        subjectsServiceSpy.save(subjectsModels);
        verify(subjectsServiceSpy, times(1)).save(subjectModelFirst);
        verify(subjectsServiceSpy, times(1)).save(subjectModelSecond);

        subjectsServiceSpy.save(subjectsModels, subjectsOptions);
        verify(subjectsServiceSpy, times(1)).save(subjectModelFirst, subjectsOptions);
        verify(subjectsServiceSpy, times(1)).save(subjectModelSecond, subjectsOptions);

    }

    @Test
    public void testDeleteIdentifiedModel() throws Exception {

        final SubjectModelInterface subjectModel = this.createSubjectModel(1, "test", "description", 1, 1);

        this.subjectsService.delete(subjectModel);

        verify(this.subjectsMapper, times(1)).delete(this.mapSubjectEntity(subjectModel));

    }

    @Test
    public void testDeleteModelsList() throws Exception {

        final SubjectModelInterface subjectModelFirst = this.createSubjectModel(2, "test2", "description2", 1, 1);
        final SubjectModelInterface subjectModelSecond = this.createSubjectModel(3, "test3", "description3", 2, 2);

        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);

        final List<SubjectModelInterface> subjectsModels = new ArrayList<>();
        subjectsModels.add(subjectModelFirst);
        subjectsModels.add(subjectModelSecond);

        final SubjectsServiceInterface subjectsServiceSpy = Mockito.spy(subjectsService);

        subjectsServiceSpy.delete(subjectsModels);
        verify(subjectsServiceSpy, times(1)).delete(subjectModelFirst);
        verify(subjectsServiceSpy, times(1)).delete(subjectModelSecond);

        subjectsServiceSpy.delete(subjectsModels, subjectsOptions);
        verify(subjectsServiceSpy, times(1)).delete(subjectModelFirst, subjectsOptions);
        verify(subjectsServiceSpy, times(1)).delete(subjectModelSecond, subjectsOptions);
    }

    @Test
    public void testDeleteUnidentifiedModel() throws Exception {

        final SubjectModelInterface subjectModel = this.createSubjectModel(null, "test", "description", 1, 1);

        exception.expect(DeleteUnidentifiedModelException.class);
        this.subjectsService.delete(subjectModel);

    }

    @Test
    public void testFindWithOptions() throws Exception {
        final Integer id = 1;
        final Integer userId = 1;
        final SubjectEntity subjectEntity = this.createSubjectEntityMock(4, "test3", "description3", userId);
        final SubjectModelInterface subjectModel = this.mapSubjectModel(subjectEntity);
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        given(this.subjectsMapper.find(id)).willReturn(subjectEntity);
        given(subjectsOptions.withRelations(subjectModel)).willReturn(subjectModel);

        final SubjectModelInterface foundedSubjectModel = this.subjectsService.find(id, subjectsOptions);

        Assert.assertEquals(subjectModel, foundedSubjectModel);
        verify(subjectsOptions).withRelations(subjectModel);
    }

    @Test
    public void testFindAllWithOptions() throws Exception {
        final List<SubjectEntity> subjectsEntities = this.getSubjectsEntities();
        final List<SubjectModelInterface> subjectsModels = this.getUsersModels();
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        given(this.subjectsMapper.findAll()).willReturn(subjectsEntities);
        given(subjectsOptions.withRelations(Mockito.anyList())).willReturn(subjectsModels);

        final List<SubjectModelInterface> foundedSubjectsModels = this.subjectsService.findAll(subjectsOptions);

        verify(subjectsOptions).withRelations(foundedSubjectsModels);
        Assert.assertEquals(subjectsModels, foundedSubjectsModels);
    }

    @Test
    public void testSaveWithOptions() throws Exception {
        final SubjectModelInterface subjectModel = this.createSubjectModel(null, "test", "description", 1, 1);
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);

        this.subjectsService.save(subjectModel, subjectsOptions);

        verify(subjectsOptions).saveWithRelations(subjectModel);
    }

    @Test
    public void testDeleteWithOptions() throws Exception {
        final SubjectModelInterface subjectModel = this.createSubjectModel(null, "test", "description", 1, 1);
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);

        this.subjectsService.delete(subjectModel, subjectsOptions);

        verify(subjectsOptions).deleteWithRelations(subjectModel);
    }

}
