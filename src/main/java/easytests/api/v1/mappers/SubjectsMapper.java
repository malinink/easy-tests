package easytests.api.v1.mappers;

import easytests.api.v1.models.Identity;
import easytests.api.v1.models.Subject;
import easytests.core.models.SubjectModel;
import easytests.core.models.UserModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;


/**
 * @author VeronikaRevjakina
 */
@Service("SubjectsMapperV1")
public class SubjectsMapper extends ModelMapper {
    public SubjectsMapper() {
        super();
        this.createTypeMap(SubjectModel.class, Subject.class)
                .addMappings(mapper -> mapper.<Integer>map(subjectModel -> subjectModel.getUser().getId(),
                    (subject, id) -> subject.getUser().setId(id)));

        final Converter<Identity, UserModel> convertIdentityToUserModel =
            new Converter<Identity, UserModel>() {
                public UserModel convert(MappingContext<Identity, UserModel> context) {
                    final UserModel userModel = new UserModel();
                    userModel.setId(context.getSource().getId());
                    return userModel;
                }
            };
        final PropertyMap<Subject, SubjectModel> map = new PropertyMap<Subject, SubjectModel>() {
            protected void configure() {
                map(source.getId()).setId(null);
                map(source.getName()).setName(null);
                map(source.getDescription()).setDescription(null);
                using(convertIdentityToUserModel).map(source.getUser()).setUser(null);
            }
        };

        this.addMappings(map);
    }

}
