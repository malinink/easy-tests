package easytests.mappers;

import easytests.entities.User;
import easytests.entities.UserInterface;
import java.util.List;
import org.apache.ibatis.annotations.*;

/**
 * @author malinink
 */
@Mapper
public interface UsersMapper {

    @Results(
        id = "User",
        value = {
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "surname", column = "surname")
        })
    @Select("SELECT id, first_name, last_name, surname FROM users")
    List<User> findAll();

    @Select("SELECT id, first_name, last_name, surname FROM users where id=#{id}")
    @ResultMap("User")
    User find(Integer id);

    @Insert("INSERT INTO users (first_name, last_name, surname) VALUES(#{firstName}, #{lastName}, #{surname})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(UserInterface user);

    @Update("UPDATE users SET first_name=#{firstName}, last_name=#{last_name}, surname=#{surname} WHERE id=#{id}")
    void update(UserInterface user);

    @Delete("DELETE FROM users WHERE id=#{id}")
    void delete(UserInterface user);

}
