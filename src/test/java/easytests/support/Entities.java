package easytests.support;

import easytests.entities.TesteeEntity;
import easytests.entities.TopicEntity;
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

    public static TesteeEntity createTesteeEntityMock(
            Integer id,
            String firstName,
            String lastName,
            String surname,
            Integer groupNumber
    ) {
        final TesteeEntity testeeEntity = Mockito.mock(TesteeEntity.class);
        Mockito.when(testeeEntity.getId()).thenReturn(id);
        Mockito.when(testeeEntity.getFirstName()).thenReturn(firstName);
        Mockito.when(testeeEntity.getLastName()).thenReturn(lastName);
        Mockito.when(testeeEntity.getSurname()).thenReturn(surname);
        Mockito.when(testeeEntity.getGroupNumber()).thenReturn(groupNumber);
        return testeeEntity;
    }

    public static TopicEntity createTopicEntityMock(Integer id, Integer subjectId, String name) {

        final TopicEntity topicEntity = Mockito.mock(TopicEntity.class);

        Mockito.when(topicEntity.getId()).thenReturn(id);
        Mockito.when(topicEntity.getSubjectId()).thenReturn(subjectId);
        Mockito.when(topicEntity.getName()).thenReturn(name);
        return topicEntity;

    }

}
