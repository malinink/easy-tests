package easytests.core.mappers;

import easytests.core.entities.QuestionTypeEntity;
import easytests.support.QuestionTypesSupport;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author SvetlanaTselikova
 */
public class QuestionTypesMapperTest extends AbstractMapperTest {

    private QuestionTypesSupport questionTypesSupport = new QuestionTypesSupport();

    @Autowired
    private QuestionTypesMapper questionTypesMapper;

    @Test
    public void testFindAll() throws Exception {
        final List<QuestionTypeEntity> questionTypesFoundedEntities = this.questionTypesMapper.findAll();

        Assert.assertEquals(4, questionTypesFoundedEntities.size());
        Integer index = 0;
        for (QuestionTypeEntity questionTypeEntity : questionTypesFoundedEntities) {
            final QuestionTypeEntity questionTypeFixtureEntity = this.questionTypesSupport.getEntityFixtureMock(index);
            this.questionTypesSupport.assertEquals(questionTypeFixtureEntity, questionTypeEntity);
            index++;
        }
    }

    @Test
    public void testFind() throws Exception {
        final QuestionTypeEntity questionTypeFixtureEntity = this.questionTypesSupport.getEntityFixtureMock(0);

        final QuestionTypeEntity questionTypeFoundedEntity = this.questionTypesMapper.find(questionTypeFixtureEntity.getId());

        this.questionTypesSupport.assertEquals(questionTypeFixtureEntity, questionTypeFoundedEntity);
    }
}
