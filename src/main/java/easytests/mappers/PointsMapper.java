package easytests.mappers;

import easytests.entities.Point;
import easytests.entities.PointInterface;
import java.util.List;
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
    List<Point> findAll();

    @Select("SELECT * FROM points WHERE id=#{id}")
    @ResultMap("Point")
    Point find(Integer id);

    @Insert("INSERT INTO points (text, type, quiz_id) VALUES(#{text}, #{type}, #{quizId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(PointInterface point);

    @Update("UPDATE points SET text=#{text}, type=#{type}, quiz_id=#{quizId} WHERE id=#{id}")
    void update(PointInterface point);

    @Delete("DELETE FROM points WHERE id=#{id}")
    void delete(PointInterface point);

}
