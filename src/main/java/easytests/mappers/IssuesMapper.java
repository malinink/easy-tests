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
            id = "Issue",
        value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "authorId", column = "author_id")
        })

    @Select("SELECT id,name,author_id FROM issues")
    List<Issue> findAll();

    @Select("SELECT id,name,author_id FROM issues WHERE id=#{id}")
    @ResultMap("Issue")
    Issue find(Integer id);

    @Insert("INSERT INTO issues (name, author_id) VALUES(#{name}, #{authorId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(IssueInterface issueInterface);

    @Update("UPDATE issues SET name=#{name}, author_id=#{authorId} WHERE id=#{id}")
    void update(IssueInterface issueInterface);

    @Delete("DELETE FROM issues WHERE id=#{id}")
    void delete(IssueInterface issueInterface);

}
