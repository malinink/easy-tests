package easytests.api.v1.mappers;

import easytests.api.v1.models.Identity;
import easytests.api.v1.models.Subject;
import easytests.core.models.SubjectModel;
import easytests.core.models.UserModelInterface;
import easytests.core.services.UsersServiceInterface;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


/**
 * @author VeronikaRevjakina
 */
@Service("SubjectsMapperV1")
public class SubjectsMapper extends ModelMapper {

    @Getter
    UsersServiceInterface usersService;

    public SubjectsMapper() {
        super();
        this.createTypeMap(SubjectModel.class, Subject.class)
                .addMappings(mapper -> mapper.<Integer>map(subjectModel -> subjectModel.getUser().getId(),
                        (subject, id) -> subject.getUser().setId(id)));

        this.createTypeMap(Identity.class, UserModelInterface.class)
                .addMappings(mapper -> mapper
                        .skip(UserModelInterface::setId));
    }


}
