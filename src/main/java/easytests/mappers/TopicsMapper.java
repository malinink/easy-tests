
package easytests.mappers;

import easytests.entities.*;

import java.util.List;
import org.apache.ibatis.annotations.*;

/**
 * @author loriens
 */

public interface TopicsMapper {

    @Results(
            id = "topicResults",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "questions_id", column = "questions_id")
            })
    @Select("SELECT * FROM topics")
    List<Topic> findAll();

    @Select("SELECT * FROM topics where id=#{id}")
    @ResultMap("Topic")
    Topic find(Integer id);

    @Insert("INSERT INTO topics (id, name, questions_id) VALUES(#{id}, #{name}, #{questions_id})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(TopicInterface topic);

    @Update("UPDATE topics SET name=#{firstName}, questions_id=#{last_name}, surname=#{surname} WHERE id=#{id}")
    void update(TopicInterface topic);

    @Delete("DELETE FROM topics WHERE id=#{id}")
    void delete(TopicInterface topic);

}
