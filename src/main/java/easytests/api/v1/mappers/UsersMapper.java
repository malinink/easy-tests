package easytests.api.v1.mappers;

import easytests.api.v1.models.User;
import easytests.core.models.UserModel;
import easytests.core.models.empty.ModelsListEmpty;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


/**
 * @author SvetlanaTselikova
 */
@Service("UsersMapperV1")
public class UsersMapper extends ModelMapper {
    public UsersMapper() {
        super();
        this.createTypeMap(UserModel.class, User.class)
            .addMappings(
                mapper -> mapper.when(
                    context -> !(context.getSource() instanceof ModelsListEmpty)
                ).map(UserModel::getSubjects, User::setSubjects)
            );
    }
}
