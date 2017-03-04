package easytests.mappers;

import easytests.entities.IssueStandardInterface;
import easytests.entities.IssueStandardQuestionTypeOption;
import easytests.entities.IssueStandardQuestionTypeOptionInterface;
import java.util.List;
import org.apache.ibatis.annotations.*;

/**
 * @author  SingularityA
 */
@Mapper
public interface IssueStandardQuestionTypeOptionMapper {

    @Results(
        id = "IssueStandardQuestionTypeOption",
        value = {
            @Result(property = "id", column = "id"),
            @Result(property = "questionTypeId", column = "question_type_id"),
            @Result(property = "minQuestions", column = "min_number"),
            @Result(property = "maxQuestions", column = "max_number"),
            @Result(property = "timeLimit", column = "time_limit"),
            @Result(property = "issueStandardId", column = "issue_standard_id")
        })
    @Select("SELECT * FROM question_type_options")
    List<IssueStandardQuestionTypeOption> findAll();

    @Select("Select * FROM question_type_options WHERE id=#{id}")
    @ResultMap("IssueStandardQuestionTypeOption")
    IssueStandardQuestionTypeOption find(Integer id);

    @Select("SELECT * FROM question_type_options WHERE issue_standard_id=#{id}")
    @ResultMap("IssueStandardQuestionTypeOption")
    List<IssueStandardQuestionTypeOption> findByIssueStandard(IssueStandardInterface issueStandard);

    @Insert("INSERT INTO question_type_options"
            + " (question_type_id, min_number, max_number, time_limit, issue_standard_id)"
            + " VALUES (#{questionTypeId}, #{minQuestions}, #{maxQuestions}, #{timeLimit}, #{issueStandardId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(IssueStandardQuestionTypeOptionInterface issueStandardQuestionTypeOption);

    @Update("UPDATE question_type_options"
            + " SET min_number=#{minQuestions}, max_number=#{maxQuestions}, time_limit=#{timeLimit} WHERE id=#{id}")
    void update(IssueStandardQuestionTypeOptionInterface issueStandardQuestionTypeOption);

    @Delete("DELETE FROM question_type_options WHERE id=#{id}")
    void delete(IssueStandardQuestionTypeOptionInterface issueStandardQuestionTypeOption);
}
