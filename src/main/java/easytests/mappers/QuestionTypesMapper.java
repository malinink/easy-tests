package easytests.mappers;

import easytests.entities.QuestionTypeEntity;
import java.util.List;
import org.apache.ibatis.annotations.*;


/**
 * @author malinink
 */
@Mapper
@SuppressWarnings("checkstyle:linelength")
public interface QuestionTypesMapper {
    @Results(
        id = "QuestionType",
        value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name")
        })
    @Select("SELECT id, name FROM question_types order by sort")
    List<QuestionTypeEntity> findAll();

    @Select("SELECT id, name FROM question_types where id=#{id}")
    @ResultMap("QuestionType")
    QuestionTypeEntity find(Integer id);
}
