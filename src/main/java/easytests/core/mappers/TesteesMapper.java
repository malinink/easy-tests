package easytests.core.mappers;

import easytests.core.entities.TesteeEntity;
import java.util.List;
import org.apache.ibatis.annotations.*;


/**
 * @author DoZor-80
 */
@Mapper
@SuppressWarnings("checkstyle:linelength")
public interface TesteesMapper {

    @Results(
            id = "Testee",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "firstName", column = "first_name"),
                    @Result(property = "lastName", column = "last_name"),
                    @Result(property = "surname", column = "surname"),
                    @Result(property = "groupNumber", column = "group_number"),
                    @Result(property = "quizId", column = "quiz_id")
            })
    @Select("SELECT * FROM testees")
    List<TesteeEntity> findAll();

    @Select("SELECT * FROM testees WHERE id=#{id}")
    @ResultMap("Testee")
    TesteeEntity find(Integer id);

    @Select("SELECT * FROM testees WHERE quiz_id=#{quizId}")
    @ResultMap("Testee")
    TesteeEntity findByQuizId(Integer quizId);

    @Insert("INSERT INTO testees (first_name, last_name, surname, group_number, quiz_id) VALUES(#{firstName}, #{lastName}, #{surname}, #{groupNumber}, #{quizId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(TesteeEntity testee);

    @Update("UPDATE testees SET first_name=#{firstName}, last_name=#{lastName}, surname=#{surname}, group_number=#{groupNumber}, quiz_id=#{quizId} WHERE id=#{id}")
    void update(TesteeEntity testee);

    @Delete("DELETE FROM testees WHERE id=#{id}")
    void delete(TesteeEntity testee);

}
