package easytests.options;

import easytests.models.SubjectModelInterface;
import easytests.models.UserModelInterface;
import easytests.services.SubjectsServiceInterface;
import easytests.services.UsersServiceInterface;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.BDDMockito.given;
import org.mockito.InOrder;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersOptionsTest {
    @Test
    public void testWithRelationsOnSingleModel() throws Exception {
        final UserModelInterface userModel = Mockito.mock(UserModelInterface.class);
        final UsersOptionsInterface usersOptions = new UsersOptions();
        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        usersOptions.setSubjectsService(subjectsService);
        usersOptions.withSubjects(subjectsOptions);
        final List<SubjectModelInterface> subjectsModels = new ArrayList<>();
        subjectsModels.add(Mockito.mock(SubjectModelInterface.class));
        given(subjectsService.findByUser(userModel, subjectsOptions)).willReturn(subjectsModels);

        final UserModelInterface userModelWithRelations = usersOptions.withRelations(userModel);

        Assert.assertEquals(userModel, userModelWithRelations);
        verify(subjectsService).findByUser(userModel, subjectsOptions);
        verify(userModel).setSubjects(subjectsModels);
        verify(userModel).setSubjects(Mockito.anyList());
    }

    @Test
    public void testWithRelationsOnModelsList() throws Exception {
        final UserModelInterface userModelFirst = Mockito.mock(UserModelInterface.class);
        userModelFirst.setId(1);
        final UserModelInterface userModelSecond = Mockito.mock(UserModelInterface.class);
        userModelSecond.setId(2);
        final List<UserModelInterface> usersModels = new ArrayList<>(2);
        usersModels.add(userModelFirst);
        usersModels.add(userModelSecond);

        final UsersOptionsInterface usersOptions = new UsersOptions();
        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        usersOptions.setSubjectsService(subjectsService);
        usersOptions.withSubjects(subjectsOptions);
        final List<SubjectModelInterface> subjectsModelsFirst = new ArrayList<>();
        subjectsModelsFirst.add(Mockito.mock(SubjectModelInterface.class));
        final List<SubjectModelInterface> subjectsModelsSecond = new ArrayList<>();
        subjectsModelsSecond.add(Mockito.mock(SubjectModelInterface.class));
        subjectsModelsSecond.add(Mockito.mock(SubjectModelInterface.class));
        given(subjectsService.findByUser(userModelFirst, subjectsOptions)).willReturn(subjectsModelsFirst);
        given(subjectsService.findByUser(userModelSecond, subjectsOptions)).willReturn(subjectsModelsSecond);

        final List<UserModelInterface> usersModelsWithRelations = usersOptions.withRelations(usersModels);

        Assert.assertEquals(usersModelsWithRelations, usersModels);
        verify(subjectsService).findByUser(userModelFirst, subjectsOptions);
        verify(usersModels.get(0)).setSubjects(subjectsModelsFirst);
        verify(usersModels.get(0)).setSubjects(Mockito.anyList());
        verify(subjectsService).findByUser(userModelSecond, subjectsOptions);
        verify(usersModels.get(1)).setSubjects(subjectsModelsSecond);
        verify(usersModels.get(1)).setSubjects(Mockito.anyList());
    }

    @Test
    public void testSaveWithRelations() throws Exception {
        final UserModelInterface userModel = Mockito.mock(UserModelInterface.class);
        final UsersOptionsInterface usersOptions = new UsersOptions();
        final UsersServiceInterface usersService = Mockito.mock(UsersServiceInterface.class);
        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        usersOptions.setUsersService(usersService);
        usersOptions.setSubjectsService(subjectsService);
        usersOptions.withSubjects(subjectsOptions);
        final List<SubjectModelInterface> subjectsModels = new ArrayList<>();
        subjectsModels.add(Mockito.mock(SubjectModelInterface.class));
        given(userModel.getSubjects()).willReturn(subjectsModels);
        final InOrder inOrder = Mockito.inOrder(subjectsService, usersService);

        usersOptions.saveWithRelations(userModel);

        inOrder.verify(usersService).save(userModel);
        inOrder.verify(subjectsService).save(subjectsModels, subjectsOptions);
    }

    @Test
    public void testDeleteWithRelations() throws Exception {
        final UserModelInterface userModel = Mockito.mock(UserModelInterface.class);
        final UsersOptionsInterface usersOptions = new UsersOptions();
        final UsersServiceInterface usersService = Mockito.mock(UsersServiceInterface.class);
        final SubjectsServiceInterface subjectsService = Mockito.mock(SubjectsServiceInterface.class);
        final SubjectsOptionsInterface subjectsOptions = Mockito.mock(SubjectsOptionsInterface.class);
        usersOptions.setUsersService(usersService);
        usersOptions.setSubjectsService(subjectsService);
        usersOptions.withSubjects(subjectsOptions);
        final List<SubjectModelInterface> subjectsModels = new ArrayList<>();
        subjectsModels.add(Mockito.mock(SubjectModelInterface.class));
        given(userModel.getSubjects()).willReturn(subjectsModels);
        final InOrder inOrder = Mockito.inOrder(subjectsService, usersService);

        usersOptions.deleteWithRelations(userModel);

        inOrder.verify(subjectsService).delete(subjectsModels, subjectsOptions);
        inOrder.verify(usersService).delete(userModel);
    }
}
