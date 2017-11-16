package easytests.core.mappers;

import easytests.core.entities.SolutionEntity;
import java.util.List;
import org.apache.ibatis.annotations.*;


/**
 * @author SingularityA
 */
@Mapper
@SuppressWarnings("checkstyle:linelength")
public interface SolutionsMapper {

    @Results(
        id = "Solution",
        value = {
             @Result(property = "id", column = "id"),
             @Result(property = "answerId", column = "answer_id"),
             @Result(property = "pointId", column = "point_id")
        })
    @Select("SELECT * FROM solutions")
    List<SolutionEntity> findAll();

    @Select("SELECT * FROM solutions WHERE id=#{id}")
    @ResultMap("Solution")
    SolutionEntity find(Integer id);

    @Select("SELECT * FROM solutions WHERE point_id=#{pointId}")
    @ResultMap("Solution")
    List<SolutionEntity> findByPointId(Integer pointId);

    @Insert("INSERT INTO solutions (answer_id, point_id) VALUES (#{answerId}, #{pointId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(SolutionEntity solution);

    @Update("UPDATE solutions SET answer_id=#{answerId}, point_id=#{pointId} WHERE id=#{id}")
    void update(SolutionEntity solution);

    @Delete("DELETE FROM solutions WHERE id=#{id}")
    void delete(SolutionEntity solution);
}
