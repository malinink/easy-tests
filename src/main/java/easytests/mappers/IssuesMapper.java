package easytests.mappers;

import easytests.entities.Issue;
import easytests.entities.IssueInterface;
import java.util.List;

import org.apache.ibatis.annotations.*;


/**
 * @author fortyways
 */
@Mapper
public interface IssuesMapper {

    @Results(
        value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "authorId", column = "author_id")
            //,
            //@Result(property="quizzes", javaType = List.class,
            // column = "id", many = @Many(select = "easytests.mappers.QuizMapper.findByIssueId")
        })

    @Select("SELECT * FROM issues")
    List<Issue> findAll();

    @Select("SELECT * FROM issues WHERE id=#{id}")
    Issue find(Integer id);

    @Insert("INSERT INTO issues (name, author_id) VALUES(#{name}, #{authorId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(IssueInterface issueInterface);

    @Update("UPDATE issues SET name=#{name}, author_id=#{authorId} WHERE id=#{id}")
    void update(IssueInterface issueInterface);

    @Delete("DELETE FROM issues WHERE id=#{id}")
    void delete(IssueInterface issueInterface);

}
