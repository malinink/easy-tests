package easytests.dao;

import java.util.List;

import easytests.entities.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserDAO {
    @Select("SELECT firstname,lastname,surname FROM users")
    public List<User> getAllUsers();

    @Insert("INSERT INTO users (firstname,lastname,surname) VALUES (#{firstName},#{lastName},#{surname})")
    public void addUser(User user);

}
