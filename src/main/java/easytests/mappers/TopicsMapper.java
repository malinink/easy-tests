package easytests.mappers;

import easytests.entities.TopicEntity;
import java.util.List;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;



/**
 * @author vkpankov
 */
public interface TopicsMapper {

    @Select("SELECT * FROM topics where subject_id=#{subjectId}")
    @ResultMap("Topic")
    List<TopicEntity> findBySubjectId(Integer subjectId);

}
