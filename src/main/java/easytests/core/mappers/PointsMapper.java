package easytests.core.mappers;

import easytests.core.entities.PointEntity;

import java.util.List;
import org.apache.ibatis.annotations.*;


/**
 * @author nikitalpopov
 */
@Mapper
@SuppressWarnings("checkstyle:linelength")
public interface PointsMapper {

    @Results(
        id = "Point",
        value = {
            @Result(property = "id", column = "id"),
            @Result(property = "questionId", column = "question_id"),
            @Result(property = "quizId", column = "quiz_id")
        })
    @Select("SELECT * FROM points")
    List<PointEntity> findAll();

    @Select("SELECT * FROM points WHERE quiz_id=#{quizId}")
    @ResultMap("Point")
    List<PointEntity> findByQuizId(Integer quizId);

    @Select("SELECT * FROM points WHERE id=#{id}")
    @ResultMap("Point")
    PointEntity find(Integer id);

    @Insert("INSERT INTO points (question_id, quiz_id) VALUES(#{questionId}, #{quizId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(PointEntity point);

    @Update("UPDATE points SET question_id=#{questionId}, quiz_id=#{quizId} WHERE id=#{id}")
    void update(PointEntity point);

    @Delete("DELETE FROM points WHERE id=#{id}")
    void delete(PointEntity point);

}
