package easytests.api.v1.mappers;

import easytests.api.v1.models.Object;
import easytests.core.models.UserModel;
import easytests.core.models.empty.ModelsListEmpty;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


/**
 * @author malinink
 */
@Service("ObjectsMapperV1")
public class ObjectsMapper extends ModelMapper {
    public ObjectsMapper() {
        super();
        this.createTypeMap(UserModel.class, Object.class)
            .addMappings(
                mapper -> mapper.when(
                    context -> !(context.getSource() instanceof ModelsListEmpty)
                ).map(UserModel::getSubjects, Object::setSubjects)
            );
    }
}
