package easytests.mappers;

import easytests.entities.QuizEntity;
import java.util.List;
import org.apache.ibatis.annotations.*;

/**
 * @author vkpankov
 */
public interface QuizzesMapper {

    @Select("SELECT * FROM quizzes")
    @Results(
            id = "Quiz",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "issueId", column = "issue_id"),
                    @Result(property = "inviteCode", column = "invite_code")

            })
    List<QuizEntity> findAll();

    @Select("SELECT * FROM quizzes WHERE id=#{id}")
    @ResultMap("Quiz")
    QuizEntity find(Integer id);

    @Select("SELECT * FROM quizzes WHERE issue_id=#{issueId}")
    @ResultMap("Quiz")
    List<QuizEntity> findByIssueId(Integer issueId);

    @Insert("INSERT INTO quizzes (issue_id, invite_code) VALUES (#{issueId},#{inviteCode})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(QuizEntity quiz);

    @Update("UPDATE quizzes SET issue_id=#{issueId}, invite_code=#{inviteCode} WHERE id=#{id}")
    void update(QuizEntity quiz);

    @Delete("DELETE FROM quizzes WHERE id=#{id}")
    void delete(QuizEntity quiz);

}
