package easytests.mappers;

import easytests.entities.Answer;
import easytests.entities.AnswerInterface;
import java.util.List;
import org.apache.ibatis.annotations.*;

/**
 * @author rezenbekk
 */
@Mapper
public interface AnswersMapper {

    @Results(
            id = "Answer",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "txt", column = "txt"),
                    @Result(property = "questionId", column = "question_id"),
                    @Result(property = "isRight", column = "is_right")
            })
    @Select("SELECT id, txt, question_id, is_right FROM answers")
    List<Answer> findAll();

    @Select("SELECT id, txt, question_id, is_right FROM answers where id=#{id}")
    @ResultMap("Answer")
    Answer find(Integer id);

    @Insert("INSERT INTO answers (txt, question_id, is_right) VALUES(#{txt}, #{questionId}, #{isRight})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(AnswerInterface answer);

    @Update("UPDATE answers SET txt=#{txt}, questionId=#{question_id}, isRight=#{is_right} WHERE id=#{id}")
    void update(AnswerInterface answer);

    @Delete("DELETE FROM answers WHERE id=#{id}")
    void delete(AnswerInterface answer);

}
