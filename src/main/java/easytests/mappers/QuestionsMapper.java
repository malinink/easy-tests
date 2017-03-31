package easytests.mappers;

import easytests.entities.QuestionEntity;
import java.util.List;
import org.apache.ibatis.annotations.*;

/**
 * @author firkhraag
 */
@Mapper
public interface QuestionsMapper {

    @Results(
            id = "Question",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "text", column = "text"),
                    @Result(property = "type", column = "type"),
                    @Result(property = "topicId", column = "topic_id")
            })
    @Select("SELECT id, text, type, topic_id FROM questions")
    List<QuestionEntity> findAll();

    @Select("SELECT id, text, type, topic_id FROM questions where id=#{id}")
    @ResultMap("Question")
    QuestionEntity find(Integer id);

    @Select("SELECT id, text, type, topic_id FROM questions WHERE topic_id=#{id}")
    @ResultMap("Question")
    List<QuestionEntity> findByTopicId(Integer id);

    @Insert("INSERT INTO questions (text, type, topic_id) VALUES(#{text}, #{type}, #{topicId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(QuestionEntity question);

    @Update("UPDATE questions SET text=#{text}, type=#{type}, topic_id=#{topicId} WHERE id=#{id}")
    void update(QuestionEntity question);

    @Delete("DELETE FROM questions WHERE id=#{id}")
    void delete(QuestionEntity question);

}
