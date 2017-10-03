package easytests.core.mappers;

import easytests.config.DatabaseConfig;
import easytests.core.entities.QuestionTypeEntity;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;


/**
 * @author malinink
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = {"classpath:database.test.properties"})
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = {DatabaseConfig.class})
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/mappersTestData.sql")
public class QuestionTypesMapperTest {
    @Autowired
    private QuestionTypesMapper questionTypesMapper;

    @Test
    public void testFindAll() throws Exception {
        final List<QuestionTypeEntity> questionTypesEntities = this.questionTypesMapper.findAll();
        Assert.assertEquals((Integer) 4, (Integer) questionTypesEntities.size());
    }

    @Test
    public void testFind() throws Exception {
        final QuestionTypeEntity questionTypesEntities = this.questionTypesMapper.find(1);
        Assert.assertEquals((Integer) 1, questionTypesEntities.getId());
        Assert.assertEquals("Один ответ", questionTypesEntities.getName());
    }
}
