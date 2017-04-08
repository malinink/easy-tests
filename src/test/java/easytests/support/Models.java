package easytests.support;

import easytests.models.UserModel;
import easytests.models.UserModelInterface;
import easytests.models.TesteeModel;
import easytests.models.TesteeModelInterface;
import easytests.models.empty.ModelsListEmpty;


/**
 * @author malinink
 */
public abstract class Models {
    public static UserModelInterface createUserModel(
            Integer id,
            String firstName,
            String lastName,
            String surname,
            String email,
            String password,
            Boolean isAdmin,
            Integer state
    ) {
        final UserModelInterface userModel = new UserModel();
        userModel.setId(id);
        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setSurname(surname);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setIsAdmin(isAdmin);
        userModel.setState(state);
        userModel.setSubjects(new ModelsListEmpty());
        return userModel;
    }
    public static TesteeModelInterface createTesteeModel(
            Integer id,
            String firstName,
            String lastName,
            String surname,
            Integer groupNumber
    ) {
        final TesteeModelInterface testeeModel = new TesteeModel();
        testeeModel.setId(id);
        testeeModel.setFirstName(firstName);
        testeeModel.setLastName(lastName);
        testeeModel.setSurname(surname);
        testeeModel.setGroupNumber(groupNumber);

        return testeeModel;
    }
}
