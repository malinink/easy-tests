package easytests.mappers;

import easytests.entities.Testee;
import easytests.entities.TesteeInterface;
import java.util.List;
import org.apache.ibatis.annotations.*;

/**
 * @author DoZor-80
 */
@Mapper
public interface TesteesMapper {
    @Results(
            id = "Testee",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "firstName", column = "first_name"),
                    @Result(property = "lastName", column = "last_name"),
                    @Result(property = "surname", column = "surname"),
                    @Result(property = "groupNumber", column = "group_number")
            })
    @Select("SELECT * FROM testees")
    List<Testee> findAll();

    @Select("SELECT * FROM testees WHERE id=#{id}")
    @ResultMap("Testee")
    Testee find(Integer id);

    @Insert("INSERT INTO testees (first_name, last_name, surname, group_number) VALUES(#{firstName}, "
            + "#{lastName}, #{surname}, #{groupNumber})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(TesteeInterface testee);

    @Update("UPDATE testees SET first_name=#{firstName}, last_name=#{lastName}, surname=#{surname}, "
            + "group_number=#{groupNumber} WHERE id=#{id}")
    void update(TesteeInterface testee);

    @Delete("DELETE FROM testees WHERE id=#{id}")
    void delete(TesteeInterface testee);
}
