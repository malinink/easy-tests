package org.mybatis.example;

/**
 * Created by loriens on 17.02.17.
 */

public interface TestMapper {
    @Select("select * from Blog where id = #{id}")
    Blog selectBlog(int id);
}