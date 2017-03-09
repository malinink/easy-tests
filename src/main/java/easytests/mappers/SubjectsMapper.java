package easytests.mappers;

import easytests.entities.SubjectEntity;
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
    SubjectEntity find(Integer subjectId);

    @Select("SELECT * FROM subjects")
    @ResultMap("Subject")
    List<SubjectEntity> findAll();

    @Select("SELECT * FROM subjects WHERE user_id=#{id}")
    @ResultMap("Subject")
    List<SubjectEntity> findByUserId(Integer id);

    @Insert("INSERT INTO subjects (name,user_id) VALUES (#{name},#{userId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(SubjectEntity subject);

    @Update("UPDATE subjects SET name=#{name}, user_id=#{userId} WHERE id=#{id}")
    void update(SubjectEntity subject);

    @Delete("DELETE FROM subjects WHERE id=#{id}")
    void delete(SubjectEntity subject);

}
