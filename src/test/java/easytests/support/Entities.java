package easytests.support;

import easytests.entities.IssueEntity;
import easytests.entities.UserEntity;
import org.mockito.Mockito;


/**
 * @author malinink
 */
public abstract class Entities {
    public static UserEntity createUserEntityMock(
            Integer id,
            String firstName,
            String lastName,
            String surname,
            String email,
            String password,
            Boolean isAdmin,
            Integer state
    ) {
        final UserEntity userEntity = Mockito.mock(UserEntity.class);
        Mockito.when(userEntity.getId()).thenReturn(id);
        Mockito.when(userEntity.getFirstName()).thenReturn(firstName);
        Mockito.when(userEntity.getLastName()).thenReturn(lastName);
        Mockito.when(userEntity.getSurname()).thenReturn(surname);
        Mockito.when(userEntity.getEmail()).thenReturn(email);
        Mockito.when(userEntity.getPassword()).thenReturn(password);
        Mockito.when(userEntity.getIsAdmin()).thenReturn(isAdmin);
        Mockito.when(userEntity.getState()).thenReturn(state);
        return userEntity;
    }
    public static IssueEntity createIssueEntityMock(
            Integer id,
            String name,
            Integer authorId
    ) {
        final IssueEntity issueEntity = Mockito.mock(IssueEntity.class);
        Mockito.when(issueEntity.getId()).thenReturn(id);
        Mockito.when(issueEntity.getName()).thenReturn(name);
        Mockito.when(issueEntity.getAuthorId()).thenReturn(authorId);
        return issueEntity;
    }
}
