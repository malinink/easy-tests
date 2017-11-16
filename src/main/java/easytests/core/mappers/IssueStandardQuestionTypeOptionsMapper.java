package easytests.core.mappers;

import easytests.core.entities.IssueStandardQuestionTypeOptionEntity;
import java.util.List;
import org.apache.ibatis.annotations.*;

/**
 * @author  SingularityA
 */
@Mapper
@SuppressWarnings("checkstyle:linelength")
public interface IssueStandardQuestionTypeOptionsMapper {

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
    List<IssueStandardQuestionTypeOptionEntity> findAll();

    @Select("SELECT * FROM question_type_options WHERE id=#{id}")
    @ResultMap("IssueStandardQuestionTypeOption")
    IssueStandardQuestionTypeOptionEntity find(Integer id);

    @Select("SELECT * FROM question_type_options WHERE issue_standard_id=#{issueStandardId}")
    @ResultMap("IssueStandardQuestionTypeOption")
    List<IssueStandardQuestionTypeOptionEntity> findByIssueStandardId(Integer issueStandardId);

    @Insert("INSERT INTO question_type_options (question_type_id, min_number, max_number, time_limit, issue_standard_id) VALUES (#{questionTypeId}, #{minQuestions}, #{maxQuestions}, #{timeLimit}, #{issueStandardId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(IssueStandardQuestionTypeOptionEntity issueStandardQuestionTypeOption);

    @Update("UPDATE question_type_options SET question_type_id=#{questionTypeId}, min_number=#{minQuestions}, max_number=#{maxQuestions}, time_limit=#{timeLimit}, issue_standard_id=#{issueStandardId} WHERE id=#{id}")
    void update(IssueStandardQuestionTypeOptionEntity issueStandardQuestionTypeOption);

    @Delete("DELETE FROM question_type_options WHERE id=#{id}")
    void delete(IssueStandardQuestionTypeOptionEntity issueStandardQuestionTypeOption);

}
