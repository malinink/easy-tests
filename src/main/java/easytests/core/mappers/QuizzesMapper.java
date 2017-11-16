package easytests.core.mappers;

import easytests.core.entities.QuizEntity;

import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.*;


/**
 * @author vkpankov
 */
@Mapper
@SuppressWarnings("checkstyle:linelength")
public interface QuizzesMapper {

    @Select("SELECT * FROM quizzes")
    @Results(
            id = "Quiz",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "issueId", column = "issue_id"),
                    @Result(property = "inviteCode", column = "invite_code"),
                    @Result(property = "codeExpired", column = "code_expired"),
                    @Result(property = "startedAt", column = "started_at", javaType = LocalDateTime.class),
                    @Result(property = "finishedAt", column = "finished_at", javaType = LocalDateTime.class)
            })
    List<QuizEntity> findAll();

    @Select("SELECT * FROM quizzes WHERE id=#{id}")
    @ResultMap("Quiz")
    QuizEntity find(Integer id);

    @Select("SELECT * FROM quizzes WHERE issue_id=#{issueId}")
    @ResultMap("Quiz")
    List<QuizEntity> findByIssueId(Integer issueId);

    @Insert("INSERT INTO quizzes (issue_id, invite_code, code_expired, started_at, finished_at) VALUES (#{issueId},#{inviteCode},#{codeExpired},#{startedAt},#{finishedAt})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(QuizEntity quiz);

    @Update("UPDATE quizzes SET issue_id=#{issueId}, invite_code=#{inviteCode}, code_expired=#{codeExpired}, started_at=#{startedAt},finished_at=#{finishedAt} WHERE id=#{id}")
    void update(QuizEntity quiz);

    @Delete("DELETE FROM quizzes WHERE id=#{id}")
    void delete(QuizEntity quiz);

}
