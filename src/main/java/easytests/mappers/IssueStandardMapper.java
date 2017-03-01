package easytests.mappers;

import easytests.entities.IssueStandard;
import easytests.entities.IssueStandardInterface;
import easytests.entities.SubjectInterface;
import java.util.List;
import org.apache.ibatis.annotations.*;

/**
 * @author SingularityA
 */
@Mapper
public interface IssueStandardMapper {

    @Results(
        id = "IssueStandard",
        value = {
            @Result(property = "id", column = "id"),
            @Result(property = "timeLimit", column = "time_limit"),
            @Result(property = "questionsNumber", column = "questions_number")
        })
    @Select("SELECT * FROM issue_standard")
    List<IssueStandard> findAll();

    @Select("SELECT * FROM issue_standard WHERE id=#{id}")
    @ResultMap("IssueStandard")
    IssueStandard find(Integer id);

    @Select("SELECT * FROM issue_standard WHERE subject_id=#{id}")
    @ResultMap("IssueStandard")
    IssueStandard findBySubject(SubjectInterface subject);

    @Insert("INSERT INTO issue_standard (time_limit, questions_number, subject_id)"
            + " VALUES (#{timeLimit}, #{questionsNumber}, #{subject.id})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(IssueStandardInterface issueStandard);

    @Update("UPDATE issue_standard SET time_limit=#{timeLimit}, questions_number=#{questionsNumber} WHERE id=#{id}")
    void update(IssueStandardInterface issueStandard);

    @Delete("DELETE FROM issue_standard WHERE id=#{id}")
    void delete(IssueStandardInterface issueStandard);
}
