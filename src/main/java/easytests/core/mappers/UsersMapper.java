package easytests.core.mappers;

import easytests.core.entities.UserEntity;
import java.util.List;
import org.apache.ibatis.annotations.*;


/**
 * @author malinink
 */
@Mapper
@SuppressWarnings("checkstyle:linelength")
public interface UsersMapper {

    @Results(
        id = "User",
        value = {
            @Result(property = "id", column = "id"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "surname", column = "surname"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "isAdmin", column = "is_admin"),
            @Result(property = "state", column = "state")
        })
    @Select("SELECT * FROM users")
    List<UserEntity> findAll();

    @Select("SELECT * FROM users where id=#{id}")
    @ResultMap("User")
    UserEntity find(Integer id);

    @Select("SELECT * FROM users where email=#{email}")
    @ResultMap("User")
    UserEntity findByEmail(String email);

    @Insert("INSERT INTO users (first_name, last_name, surname, email, password, is_admin, state) VALUES(#{firstName}, #{lastName}, #{surname}, #{email}, #{password}, #{isAdmin}, #{state})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(UserEntity user);

    @Update("UPDATE users SET first_name=#{firstName}, last_name=#{lastName}, surname=#{surname}, email=#{email}, password=#{password}, is_admin=#{isAdmin}, state=#{state} WHERE id=#{id}")
    void update(UserEntity user);

    @Delete("DELETE FROM users WHERE id=#{id}")
    void delete(UserEntity user);

}
