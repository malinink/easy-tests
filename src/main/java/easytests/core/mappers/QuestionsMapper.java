package easytests.core.mappers;

import easytests.core.entities.QuestionEntity;
import java.util.List;
import org.apache.ibatis.annotations.*;


/**
 * @author firkhraag
 */
@Mapper
@SuppressWarnings("checkstyle:linelength")
public interface QuestionsMapper {

    @Results(
            id = "Question",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "text", column = "text"),
                    @Result(property = "questionTypeId", column = "question_type_id"),
                    @Result(property = "topicId", column = "topic_id")
            })
    @Select("SELECT * FROM questions")
    List<QuestionEntity> findAll();

    @Select("SELECT * FROM questions where id=#{id}")
    @ResultMap("Question")
    QuestionEntity find(Integer id);

    @Select("SELECT * FROM questions where topic_id=#{topicId}")
    @ResultMap("Question")
    List<QuestionEntity> findByTopicId(Integer topicId);

    @Insert("INSERT INTO questions (text, question_type_id, topic_id) VALUES(#{text}, #{questionTypeId}, #{topicId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(QuestionEntity question);

    @Update("UPDATE questions SET text=#{text}, question_type_id=#{questionTypeId}, topic_id=#{topicId} WHERE id=#{id}")
    void update(QuestionEntity question);

    @Delete("DELETE FROM questions WHERE id=#{id}")
    void delete(QuestionEntity question);

}
