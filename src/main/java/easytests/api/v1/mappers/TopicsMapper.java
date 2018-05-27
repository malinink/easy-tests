package easytests.api.v1.mappers;

import easytests.api.v1.models.Topic;
import easytests.core.models.TopicModel;
import org.modelmapper.ModelMapper;
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
    }
}
