
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
                    @Result(property = "questionsId", column = "questions_id")
            })
    @Select("SELECT * FROM topics")
    List<TopicInterface> findAll();

    @Select("SELECT * FROM topics where id=#{id}")
    @ResultMap("Topic")
    TopicInterface find(Integer id);

    @Insert("INSERT INTO topics (id, name, questions_id) VALUES(#{id}, #{name}, #{questionsId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(TopicInterface topic);

    @Update("UPDATE topics SET name=#{name}, questions_id=#{questionsId} WHERE id=#{id}")
    void update(TopicInterface topic);

    @Delete("DELETE FROM topics WHERE id=#{id}")
    void delete(TopicInterface topic);

}
