package easytests.core.mappers;

import easytests.core.entities.AnswerEntity;
import java.util.List;
import org.apache.ibatis.annotations.*;


/**
 * @author rezenbekk
 */
@Mapper
@SuppressWarnings("checkstyle:linelength")
public interface AnswersMapper {

    @Results(
            id = "AnswerEntity",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "txt", column = "txt"),
                    @Result(property = "questionId", column = "question_id"),
                    @Result(property = "serialNumber", column = "serial_number"),
                    @Result(property = "right", column = "is_right")
            })
    @Select("SELECT id, txt, question_id, serial_number, is_right FROM answers")
    List<AnswerEntity> findAll();

    @Select("SELECT id, txt, question_id, serial_number, is_right FROM answers where id=#{id}")
    @ResultMap("AnswerEntity")
    AnswerEntity find(Integer id);

    @Select("SELECT id, txt, question_id, serial_number, is_right FROM answers WHERE"
            + " question_id=#{questionId}")
    @ResultMap("AnswerEntity")
    List<AnswerEntity> findByQuestionId(Integer id);

    @Insert("INSERT INTO answers (txt, question_id, serial_number, is_right) VALUES(#{txt}, #{questionId}, #{serialNumber}, #{right})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(AnswerEntity answer);

    @Update("UPDATE answers SET txt=#{txt}, question_id=#{questionId}, serial_number=#{serialNumber}, is_right=#{right} WHERE id=#{id}")
    void update(AnswerEntity answer);

    @Delete("DELETE FROM answers WHERE id=#{id}")
    void delete(AnswerEntity answer);

}
