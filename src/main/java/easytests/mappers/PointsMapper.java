package easytests.mappers;

import easytests.entities.Point;
import easytests.entities.PointInterface;
import java.util.List;
import org.apache.ibatis.annotations.*;

/**
 * @author nikitapopov on 24/02/2017.
 */
@Mapper
public interface PointsMapper {

    @Results(
        id = "Point",
        value = {
            @Result(property = "id", column = "id"),
            @Result(property = "text", column = "text"),
            @Result(property = "type", column = "type"),
            @Result(property = "quiz", column = "quiz"),
            @Result(property = "solutions", column = "solutions")
        })
    @Select("SELECT id, text, type, quiz, solutions FROM points")
    List<Point> findAll();

    @Select("SELECT id, text, type, quiz, solutions FROM points WHERE id=#{id}")
    @ResultMap("Point")
    Point find(Integer id);

    @Insert("INSERT INTO points (text, type, quiz, solutions) VALUES(#{text}, #{type}, #{quiz}, #{solutions})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(PointInterface  point);

    @Update("UPDATE points SET text=#{text}, type=#{type}, quiz=#{quiz}, solutions=#{solutions} WHERE id=#{id}")
    void update(PointInterface point);

    @Delete("DELETE FROM points WHERE id=#{id}")
    void delete(PointInterface point);

}
