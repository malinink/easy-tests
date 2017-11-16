package easytests.core.mappers;

import easytests.core.entities.IssueEntity;
import java.util.List;
import org.apache.ibatis.annotations.*;


/**
 * @author fortyways
 */
@Mapper
@SuppressWarnings("checkstyle:linelength")
public interface IssuesMapper {

    @Select("SELECT * FROM issues")
    @Results(
            id = "Issue",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "subjectId", column = "subject_id")
            })
    List<IssueEntity> findAll();

    @Select("SELECT * FROM issues WHERE id=#{id}")
    @ResultMap("Issue")
    IssueEntity find(Integer id);

    @Insert("INSERT INTO issues (name, subject_id) VALUES(#{name}, #{subjectId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(IssueEntity issue);

    @Update("UPDATE issues SET name=#{name}, subject_id=#{subjectId} WHERE id=#{id}")
    void update(IssueEntity issue);

    @Delete("DELETE FROM issues WHERE id=#{id}")
    void delete(IssueEntity issue);

    @Select("SELECT * FROM issues WHERE subject_id=#{subjectId}")
    @ResultMap("Issue")
    List<IssueEntity> findBySubjectId(Integer subjectId);

}
