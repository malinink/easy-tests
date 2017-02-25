package easytests.mappers;

import easytests.entities.Question;
import easytests.entities.QuestionInterface;
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
    List<Question> findAll();

    @Select("SELECT id, text, type, topic_id FROM questions where id=#{id}")
    @ResultMap("Question")
    Question find(Integer id);

    @Insert("INSERT INTO questions (text, type, topic_id) VALUES(#{text}, #{type}, #{topicId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(QuestionInterface question);

    @Update("UPDATE questions SET text=#{text}, type=#{type}, topic_id=#{topicId} WHERE id=#{id}")
    void update(QuestionInterface question);

    @Delete("DELETE FROM questions WHERE id=#{id}")
    void delete(QuestionInterface question);

}
