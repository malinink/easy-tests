package easytests.mappers;

import easytests.entities.AnswerEntity;
import java.util.List;
import org.apache.ibatis.annotations.*;

/**
 * @author rezenbekk
 */
@Mapper
public interface AnswersMapper {

    @Results(
            id = "AnswerEntity",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "txt", column = "txt"),
                    @Result(property = "questionId", column = "question_id"),
                    @Result(property = "right", column = "is_right")
            })
    @Select("SELECT id, txt, question_id, is_right FROM answers")
    List<AnswerEntity> findAll();

    @Select("SELECT id, txt, question_id, is_right FROM answers where id=#{id}")
    @ResultMap("AnswerEntity")
    AnswerEntity find(Integer id);

    @Insert("INSERT INTO answers (txt, question_id, is_right) VALUES(#{txt}, #{questionId}, #{right})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(AnswerEntity answer);

    @Update("UPDATE answers SET txt=#{txt}, question_id=#{questionId}, is_right=#{right} WHERE id=#{id}")
    void update(AnswerEntity answer);

    @Delete("DELETE FROM answers WHERE id=#{id}")
    void delete(AnswerEntity answer);

}
