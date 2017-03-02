package easytests.mappers;

import easytests.entities.IssueInterface;
import easytests.entities.Quiz;
import easytests.entities.QuizInterface;
import java.util.List;
import org.apache.ibatis.annotations.*;


/**
 * @author vkpankov
 */
@Mapper
public interface QuizzesMapper {

    @Select("SELECT * FROM quizzes WHERE id=#{quizId}")
    @Results(
            id = "Quiz",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "issueId", column = "issue_id"),
                    @Result(property = "inviteCode", column = "invite_code")

            })
    Quiz find(Integer quizId);

    @Select("SELECT * FROM quizzes")
    @ResultMap("Quiz")
    List<QuizInterface> findAll();

    @Select("SELECT * FROM quizzes WHERE issue_id=#{id}")
    @ResultMap("Quiz")
    List<QuizInterface> findByIssue(IssueInterface issue);

    @Insert("INSERT INTO quizzes (issue_id, invite_code) VALUES (#{issueId},#{inviteCode})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(QuizInterface quiz);

    @Update("UPDATE quizzes SET issue_id=#{issueId}, invite_code=#{inviteCode} WHERE id=#{id}")
    void update(QuizInterface quiz);

    @Delete("DELETE FROM quizzes WHERE id=#{id}")
    void delete(QuizInterface quiz);

}
