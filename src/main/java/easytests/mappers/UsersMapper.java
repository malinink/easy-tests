package easytests.mappers;

import easytests.entities.User;
import java.util.List;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UsersMapper {

    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "surname", column = "surname")
    })

    @Select("SELECT id, first_name, last_name, surname FROM users")
    List<User> readAll();

    @Insert("INSERT INTO users (first_name, last_name, surname) VALUES(#{firstName}, #{lastName}, #{surname})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void create(User user);

    @Delete("DELETE FROM users WHERE id=#{id}")
    void delete(User user);

    @Update("UPDATE users SET first_name=#{firstName}, last_name=#{last_name}, surname=#{surname} WHERE id=#{id}")
    void update(User user);
}
