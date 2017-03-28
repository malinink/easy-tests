package easytests.mappers;

import easytests.entities.IssueStandardEntity;
import java.util.List;
import org.apache.ibatis.annotations.*;

/**
 * @author SingularityA
 */
@Mapper
public interface IssueStandardsMapper {

    @Results(
        id = "IssueStandard",
        value = {
            @Result(property = "id", column = "id"),
            @Result(property = "timeLimit", column = "time_limit"),
            @Result(property = "questionsNumber", column = "questions_number"),
            @Result(property = "subjectId", column = "subject_id")
        })
    @Select("SELECT * FROM issue_standard")
    List<IssueStandardEntity> findAll();

    @Select("SELECT * FROM issue_standard WHERE id=#{id}")
    @ResultMap("IssueStandard")
    IssueStandardEntity find(Integer id);

    @Select("SELECT * FROM issue_standard WHERE subject_id=#{subjectId}")
    @ResultMap("IssueStandard")
    IssueStandardEntity findBySubjectId(Integer subjectId);

    @Insert("INSERT INTO issue_standard (time_limit, questions_number, subject_id)"
            + " VALUES (#{timeLimit}, #{questionsNumber}, #{subjectId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(IssueStandardEntity issueStandard);

    @Update("UPDATE issue_standard SET time_limit=#{timeLimit}, questions_number=#{questionsNumber},"
            + " subject_id=#{subjectId} WHERE id=#{id}")
    void update(IssueStandardEntity issueStandard);

    @Delete("DELETE FROM issue_standard WHERE id=#{id}")
    void delete(IssueStandardEntity issueStandard);
}
