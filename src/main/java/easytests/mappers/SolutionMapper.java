package easytests.mappers;

import easytests.entities.PointInterface;
import easytests.entities.Solution;
import easytests.entities.SolutionInterface;
import java.util.List;
import org.apache.ibatis.annotations.*;

/**
 * @author SingularityA
 */
@Mapper
public interface SolutionMapper {

    @Results(
        id = "Solution",
        value = {
             @Result(property = "id", column = "id")
        })
    @Select("SELECT * FROM solutions")
    List<Solution> findAll();

    @Select("SELECT * FROM solutions WHERE id=#{id}")
    @ResultMap("Solution")
    Solution find(Integer id);

    @Select("SELECT * FROM solutions WHERE point_id=#{id}")
    @ResultMap("Solution")
    List<Solution> findByPoint(PointInterface point);

    @Insert("INSERT INTO solutions (answer_id, point_id) VALUES (#{answer.id}, #{point.id})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(SolutionInterface solution);

    @Update("UPDATE solutions SET answer_id=#{answer.id}, point_id=#{point.id} WHERE id=#{id}")
    void update(SolutionInterface solution);

    @Delete("DELETE FROM solutions WHERE id=#{id}")
    void delete(SolutionInterface solution);
}
