package easytests.core.mappers;

import easytests.core.entities.SubjectEntity;
import java.util.List;
import org.apache.ibatis.annotations.*;


/**
 * @author vkpankov
 */
@Mapper
@SuppressWarnings("checkstyle:linelength")
public interface SubjectsMapper {

    @Select("SELECT * FROM subjects")
    @Results(
            id = "Subject",
            value = {
                    @Result(property = "id", column = "id"),
                    @Result(property = "name", column = "name"),
                    @Result(property = "description", column = "description"),
                    @Result(property = "userId", column = "user_id")
            })
    List<SubjectEntity> findAll();

    @Select("SELECT * FROM subjects WHERE id=#{id}")
    @ResultMap("Subject")
    SubjectEntity find(Integer id);

    @Select("SELECT * FROM subjects WHERE user_id=#{userId}")
    @ResultMap("Subject")
    List<SubjectEntity> findByUserId(Integer userId);

    @Insert("INSERT INTO subjects (name, description, user_id) VALUES (#{name}, #{description}, #{userId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    void insert(SubjectEntity subject);

    @Update("UPDATE subjects SET name=#{name}, description=#{description}, user_id=#{userId} WHERE id=#{id}")
    void update(SubjectEntity subject);

    @Delete("DELETE FROM subjects WHERE id=#{id}")
    void delete(SubjectEntity subject);

}
