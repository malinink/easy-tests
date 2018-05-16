package easytests.api.v1.mappers;

import easytests.api.v1.models.Topic;
import easytests.core.models.TopicModel;
import easytests.core.models.empty.ModelsListEmpty;
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
            .addMappings(mapper -> mapper.when(
                context -> !(context.getSource() instanceof ModelsListEmpty))
            .<Integer>map(topicModel -> topicModel.getSubject().getId(),
                (topic, id) -> topic.getSubject().setId(id)));
    }
}
