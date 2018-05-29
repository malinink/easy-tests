package easytests.api.v1.mappers;

import easytests.api.v1.models.Identity;
import easytests.api.v1.models.Topic;
import easytests.core.models.SubjectModel;
import easytests.core.models.TopicModel;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

/**
 * @author lelay
 */
@Service("TopicsMapperV1")
public class TopicsMapper extends ModelMapper {
    public TopicsMapper() {
        super();
        this.createTypeMap(TopicModel.class, Topic.class)
                .addMappings(mapper -> mapper.<Integer>map(topicModel -> topicModel.getSubject().getId(),
                    (topic, id) -> topic.getSubject().setId(id)));

        final Converter<Identity, SubjectModel> convertIdentityToSubjectModel =
            new Converter<Identity, SubjectModel>() {
                public SubjectModel convert(MappingContext<Identity, SubjectModel> context) {

                    final SubjectModel subjectModel = new SubjectModel();
                    subjectModel.setId(context.getSource().getId());

                    return subjectModel;
                }
            };

        final PropertyMap<Topic, TopicModel> mymap = new PropertyMap<Topic, TopicModel>() {
            protected void configure() {
                map(source.getId()).setId(null);
                map(source.getName()).setName(null);
                using(convertIdentityToSubjectModel).map(source.getSubject()).setSubject(null);
            }
        };

        this.addMappings(mymap);
    }
}
