package easytests.mappers;

import easytests.entities.Subject;
import easytests.entities.SubjectInterface;
import java.util.List;
import org.apache.ibatis.annotations.*;


/**
 * @author vkpankov
 */
public interface SubjectsMapper {

    @Select("SELECT * FROM subjects WHERE id=#{subjectId}")
    @Results(
            id = "Subject",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "userId", column = "user_id")
            })
    Subject find(Integer subjectId);

    @Select("SELECT * FROM subjects")
    @ResultMap("Subject")
    List<SubjectInterface> findAll();

    @Select("SELECT * FROM subjects WHERE user_id=#{id}")
    @ResultMap("Subject")
    List<SubjectInterface> findByUserId(Integer id);

    @Insert("INSERT INTO subjects (name,user_id) VALUES (#{name},#{userId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(SubjectInterface subject);

    @Update("UPDATE subjects SET name=#{name}, user_id=#{userId} WHERE id=#{id}")
    void update(SubjectInterface subject);

    @Delete("DELETE FROM subjects WHERE id=#{id}")
    void delete(SubjectInterface subject);

}
