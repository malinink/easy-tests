package easytests.mappers;

import java.util.List;

import easytests.entities.PointEntity;
import org.apache.ibatis.annotations.*;

/**
 * @author nikitalpopov
 */
@Mapper
public interface PointsMapper {

    @Results(
        id = "Point",
        value = {
            @Result(property = "id", column = "id"),
            @Result(property = "text", column = "text"),
            @Result(property = "type", column = "type"),
            @Result(property = "quizId", column = "quiz_id")
        })
    @Select("SELECT * FROM points")
    List<PointEntity> findAll();

    @Select("SELECT * FROM points WHERE id=#{id}")
    @ResultMap("Point")
    PointEntity find(Integer id);

    @Insert("INSERT INTO points (text, type, quiz_id) VALUES(#{text}, #{type}, #{quizId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(PointEntity point);

    @Update("UPDATE points SET text=#{text}, type=#{type}, quiz_id=#{quizId} WHERE id=#{id}")
    void update(PointEntity point);

    @Delete("DELETE FROM points WHERE id=#{id}")
    void delete(PointEntity point);

}
