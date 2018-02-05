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

    private QuestionTypesSupport question_typesSupport = new QuestionTypesSupport();

    @Autowired
    private QuestionTypesMapper question_typesMapper;

    @Test
    public void testFindAll() throws Exception {
        final List<QuestionTypeEntity> question_typesFoundedEntities = this.question_typesMapper.findAll();

        Assert.assertEquals(4, question_typesFoundedEntities.size());

        Integer index = 0;
        for (QuestionTypeEntity question_typeEntity: question_typesFoundedEntities) {
            final QuestionTypeEntity question_typeFixtureEntity = this.question_typesSupport.getEntityFixtureMock(index);

            this.question_typesSupport.assertEquals(question_typeFixtureEntity, question_typeEntity);
            index++;
        }
    }

    @Test
    public void testFind() throws Exception {
        final QuestionTypeEntity question_typeFixtureEntity = this.question_typesSupport.getEntityFixtureMock(0);

        final QuestionTypeEntity question_typeFoundedEntity = this.question_typesMapper.find(question_typeFixtureEntity.getId());

        this.question_typesSupport.assertEquals(question_typeFixtureEntity, question_typeFoundedEntity);
    }

}