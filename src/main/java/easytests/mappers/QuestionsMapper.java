package easytests.mappers;

import easytests.entities.Answer;
import easytests.entities.Question;
import easytests.entities.QuestionInterface;
import java.util.List;
import org.apache.ibatis.annotations.*;

/**
 * @author firkhraag
 */
@Mapper
public interface QuestionsMapper {

    @Select("SELECT id, text, is_right, question_id FROM answers WHERE question_id = #{id}")
    List<Answer> findAnswers(Integer id);

    @Results(
            id = "Question",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "text", column = "text"),
                    @Result(property = "type", column = "type"),
                    @Result(property = "topicId", column = "topic_id"),
                    //@Result(property = "answers", javaType=List.class, column = "id", many = @Many(select = "findAnswers"))
            })
    @Select("SELECT id, text, type, topic_id FROM questions")
    List<Question> findAll();

   @Results(
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "text", column = "text"),
                    @Result(property = "type", column = "type"),
                    @Result(property = "topicId", column = "topic_id"),
                    @Result(property = "answers", javaType=List.class, column = "id", many = @Many(select = "findAnswers"))
            })
    @Select("SELECT id, text, type, topic_id FROM questions where id=#{id}")
    @ResultMap("Question")
    Question find(Integer id);

    @Insert("INSERT INTO questions (text, type, topicId) VALUES(#{text}, #{type}, #{topicId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(QuestionInterface question);

    @Update("UPDATE questions SET text=#{text}, type=#{type}, topicId=#{topicId} WHERE id=#{id}")
    void update(QuestionInterface question);

    @Delete("DELETE FROM questions WHERE id=#{id}")
    void delete(QuestionInterface question);

}
