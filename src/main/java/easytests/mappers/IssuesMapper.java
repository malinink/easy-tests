package easytests.mappers;

import easytests.entities.IssueEntity;
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
    List<IssueEntity> findAll();

    @Select("SELECT id,name,author_id FROM issues WHERE id=#{id}")
    @ResultMap("Issue")
    IssueEntity find(Integer id);

    @Insert("INSERT INTO issues (name, author_id) VALUES(#{name}, #{authorId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(IssueEntity issue);

    @Update("UPDATE issues SET name=#{name}, author_id=#{authorId} WHERE id=#{id}")
    void update(IssueEntity issue);

    @Delete("DELETE FROM issues WHERE id=#{id}")
    void delete(IssueEntity issue);
}
