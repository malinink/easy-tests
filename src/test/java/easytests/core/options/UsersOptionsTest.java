package easytests.core.options;

import easytests.core.models.SubjectModelInterface;
import easytests.core.models.UserModelInterface;
import easytests.core.services.SubjectsServiceInterface;
import easytests.core.services.UsersServiceInterface;
import easytests.support.UsersSupport;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import static org.mockito.BDDMockito.when;
import org.mockito.InOrder;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersOptionsTest {

    private UsersSupport usersSupport = new UsersSupport();

    private UsersOptionsInterface usersOptions;

    private SubjectsServiceInterface subjectsService;

    private SubjectsOptionsInterface subjectsOptions;

    private UsersServiceInterface usersService;

    private List<SubjectModelInterface> subjectsModels;

    private UserModelInterface userModel;

    private List<UserModelInterface> usersModels;

    private List<List<SubjectModelInterface>> subjectsModelsLists;

    private ArgumentCaptor<List> listCaptor;

    @Before
    public void before() {
        this.subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        this.subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        this.usersService = Mockito.mock(UsersServiceInterface.class);

        this.usersOptions = new UsersOptions();
        this.usersOptions.setUsersService(this.usersService);
        this.usersOptions.setSubjectsService(this.subjectsService);

        this.listCaptor = ArgumentCaptor.forClass(List.class);
    }

    private UsersOptionsTest withUserModel() {
        this.userModel = this.usersSupport.getModelFixtureMock(0);
        return this;
    }

    private UsersOptionsTest withSubjectsModelsFounded() {
        this.subjectsModels = new ArrayList<>();
        when(this.subjectsService.findByUser(this.userModel, this.subjectsOptions)).thenReturn(this.subjectsModels);
        return this;
    }

    private UsersOptionsTest withSubjectsModelsInjected() {
        this.subjectsModels = new ArrayList<>();
        when(this.userModel.getSubjects()).thenReturn(this.subjectsModels);
        return this;
    }


    private UsersOptionsTest withSubjects() {
        this.usersOptions.withSubjects(this.subjectsOptions);
        return this;
    }

    private UsersOptionsTest withUsersList() {
        this.usersModels = new ArrayList<>(2);
        this.usersModels.add(this.usersSupport.getModelFixtureMock(0));
        this.usersModels.add(this.usersSupport.getModelFixtureMock(1));

        return this;
    }

    private UsersOptionsTest withSubjectsModelsListsFounded() {
        this.subjectsModelsLists = new ArrayList<>(2);
        this.subjectsModelsLists.add(new ArrayList<>());
        this.subjectsModelsLists.add(new ArrayList<>());
        when(this.subjectsService.findByUser(this.usersModels.get(0), this.subjectsOptions)).thenReturn(subjectsModelsLists.get(0));
        when(this.subjectsService.findByUser(this.usersModels.get(1), this.subjectsOptions)).thenReturn(subjectsModelsLists.get(1));

        return this;
    }

    @Test
    public void testWithNoRelations() throws Exception {
        this.withUserModel().withSubjectsModelsFounded();

        final UserModelInterface userModelWithRelations = this.usersOptions.withRelations(this.userModel);

        Assert.assertSame(userModel, userModelWithRelations);
        verify(this.subjectsService, times(0)).findByUser(any(), any());
        verify(this.userModel, times(0)).setSubjects(anyList());
    }

    @Test
    public void testWithSubjectsRelations() throws Exception {
        this.withUserModel().withSubjectsModelsFounded().withSubjects();

        final UserModelInterface userModelWithRelations = this.usersOptions.withRelations(this.userModel);

        Assert.assertSame(userModel, userModelWithRelations);
        verify(this.subjectsService, times(1)).findByUser(this.userModel, this.subjectsOptions);
        verify(this.userModel, times(1)).setSubjects(this.listCaptor.capture());
        Assert.assertSame(this.subjectsModels, this.listCaptor.getValue());
    }


    @Test
    public void testWithRelationsOnNull() throws Exception {
        final UserModelInterface userModel = null;

        final UserModelInterface userModelWithRelations = this.usersOptions.withRelations(userModel);

        Assert.assertSame(null, userModelWithRelations);
    }

    @Test
    public void testWithNoRelationsOnModelsList() throws Exception {
        this.withUsersList().withSubjectsModelsListsFounded();

        final List<UserModelInterface> usersModelsWithRelations = this.usersOptions.withRelations(this.usersModels);

        Assert.assertSame(usersModelsWithRelations, this.usersModels);
        for (Integer i = 0; i < 2; i++) {
            verify(this.subjectsService, times(0)).findByUser(any(), any());
            verify(this.usersModels.get(i), times(0)).setSubjects(anyList());
        }
    }

    @Test
    public void testWithSubjectsRelationsOnModelsList() throws Exception {
        this.withUsersList().withSubjectsModelsListsFounded().withSubjects();

        final List<UserModelInterface> usersModelsWithRelations = this.usersOptions.withRelations(this.usersModels);

        Assert.assertSame(usersModelsWithRelations, this.usersModels);
        for (Integer i = 0; i < 2; i++) {
            verify(this.subjectsService, times(1)).findByUser(this.usersModels.get(i), this.subjectsOptions);
            verify(this.usersModels.get(i), times(1)).setSubjects(this.listCaptor.capture());
            Assert.assertSame(this.subjectsModelsLists.get(i), this.listCaptor.getValue());
        }
    }

    @Test
    public void testSaveWithNoRelations() throws Exception {
        this.withUserModel().withSubjectsModelsInjected();

        this.usersOptions.saveWithRelations(this.userModel);

        verify(this.usersService, times(1)).save(this.userModel);
        verify(this.subjectsService, times(0)).save(anyList(), any());
    }

    @Test
    public void testSaveWithSubjectsRelations() throws Exception {
        this.withUserModel().withSubjectsModelsInjected().withSubjects();
        final ArgumentCaptor<SubjectsOptionsInterface> subjectsOptionsCaptor = ArgumentCaptor.forClass(SubjectsOptionsInterface.class);;


        this.usersOptions.saveWithRelations(this.userModel);

        verify(this.usersService, times(1)).save(this.userModel);
        verify(this.subjectsService, times(1)).save(this.listCaptor.capture(), subjectsOptionsCaptor.capture());
        Assert.assertSame(this.subjectsModels, this.listCaptor.getValue());
        Assert.assertSame(this.subjectsOptions, subjectsOptionsCaptor.getValue());
    }

    @Test
    public void testSaveWithAllRelationsOrder() throws Exception {
        this.withUserModel().withSubjectsModelsInjected().withSubjects();
        final InOrder inOrder = inOrder(this.subjectsService, this.usersService);

        this.usersOptions.saveWithRelations(this.userModel);

        inOrder.verify(this.usersService, times(1)).save(any());
        inOrder.verify(this.subjectsService, times(1)).save(anyList(), any());
    }

    @Test
    public void testDeleteWithNoRelations() throws Exception {
        this.withUserModel().withSubjectsModelsInjected();

        this.usersOptions.deleteWithRelations(this.userModel);

        verify(this.usersService, times(1)).delete(this.userModel);
        verify(this.subjectsService, times(0)).delete(this.subjectsModels, this.subjectsOptions);
    }

    @Test
    public void testDeleteWithSubjectsRelations() throws Exception {
        this.withUserModel().withSubjectsModelsInjected().withSubjects();
        final ArgumentCaptor<SubjectsOptionsInterface> subjectsOptionsCaptor = ArgumentCaptor.forClass(SubjectsOptionsInterface.class);;

        this.usersOptions.deleteWithRelations(this.userModel);

        verify(this.usersService, times(1)).delete(this.userModel);
        verify(this.subjectsService, times(1)).delete(this.listCaptor.capture(), subjectsOptionsCaptor.capture());
        Assert.assertSame(this.subjectsModels, this.listCaptor.getValue());
        Assert.assertSame(this.subjectsOptions, subjectsOptionsCaptor.getValue());
    }

    @Test
    public void testDeleteWithAllRelationsOrder() throws Exception {
        this.withUserModel().withSubjectsModelsInjected().withSubjects();
        final InOrder inOrder = inOrder(this.subjectsService, this.usersService);

        this.usersOptions.deleteWithRelations(this.userModel);

        inOrder.verify(this.subjectsService, times(1)).delete(anyList(), any());
        inOrder.verify(this.usersService, times(1)).delete(any());
    }
}
