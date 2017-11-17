package easytests.core.mappers;

import easytests.core.entities.QuestionTypeEntity;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author malinink
 */
public class QuestionTypesMapperTest extends AbstractMapperTest {
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
