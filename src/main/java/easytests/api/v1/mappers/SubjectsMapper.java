package easytests.api.v1.mappers;

import easytests.api.v1.models.Subject;
import easytests.core.models.SubjectModel;
import easytests.core.models.empty.ModelsListEmpty;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


/**
 * @author VeronikaRevjakina
 */
@Service("SubjectsMapperV1")
public class SubjectsMapper extends ModelMapper {
    public SubjectsMapper() {
        super();
        this.createTypeMap(SubjectModel.class, Subject.class)
            .addMappings(
                mapper -> mapper.when(
                    context -> !(context.getSource() instanceof ModelsListEmpty)
                ).<Integer>map(subjectModel -> subjectModel.getUser().getId(),
                    (subject, id) -> subject.getUser().setId(id))
            );
    }

}
