package easytests.core.mappers;

import easytests.core.entities.IssueStandardTopicPriorityEntity;
import java.util.List;
import org.apache.ibatis.annotations.*;


/**
 * @author SingularityA
 */
@Mapper
@SuppressWarnings("checkstyle:linelength")
public interface IssueStandardTopicPrioritiesMapper {

    @Results(
        id = "IssueStandardTopicPriority",
        value = {
            @Result(property = "id", column = "id"),
            @Result(property = "topicId", column = "topic_id"),
            @Result(property = "isPreferable", column = "is_preferable"),
            @Result(property = "issueStandardId", column = "issue_standard_id")
        })
    @Select("SELECT * FROM topic_priorities")
    List<IssueStandardTopicPriorityEntity> findAll();

    @Select("SELECT * FROM topic_priorities WHERE id=#{id}")
    @ResultMap("IssueStandardTopicPriority")
    IssueStandardTopicPriorityEntity find(Integer id);

    @Select("SELECT * FROM topic_priorities WHERE issue_standard_id=#{issueStandardId}")
    @ResultMap("IssueStandardTopicPriority")
    List<IssueStandardTopicPriorityEntity> findByIssueStandardId(Integer issueStandardId);

    @Insert("INSERT INTO topic_priorities (topic_id, is_preferable, issue_standard_id) VALUES (#{topicId}, #{isPreferable}, #{issueStandardId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(IssueStandardTopicPriorityEntity issueStandardTopicPriority);

    @Update("UPDATE topic_priorities SET topic_id=#{topicId}, is_preferable=#{isPreferable}, issue_standard_id=#{issueStandardId} WHERE id=#{id}")
    void update(IssueStandardTopicPriorityEntity issueStandardTopicPriority);

    @Delete("DELETE FROM topic_priorities WHERE id=#{id}")
    void delete(IssueStandardTopicPriorityEntity issueStandardTopicPriority);
}
