package easytests.core.entities;

import easytests.core.models.QuizModelInterface;
import easytests.core.models.empty.IssueModelEmpty;
import easytests.support.QuizzesSupport;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.meanbean.test.*;
import org.mockito.Mockito;

import java.time.LocalDateTime;

/**
 * @author miron97
 */
public class QuizEntityTest extends AbstractEntityTest {

    private QuizzesSupport quizSupport = new QuizzesSupport();

    @Override
    protected Configuration getConfiguration(){
        return new ConfigurationBuilder().iterations(10)
                .ignoreProperty("startedAt")
                .ignoreProperty("finishedAt")
                .build();
    }

    @Override
    protected void testCommon(Class entityClass) {
        final Configuration configuration = this.getConfiguration();
        new BeanTester().testBean(entityClass, configuration);
    }

    @Test
    public void testCommon() throws Exception {
        this.testCommon(QuizEntity.class);
    }

    @Test
    public void testMap() throws Exception {
        final QuizModelInterface quizModel = this.quizSupport.getModelFixtureMock(0);
        final QuizEntity quizEntity = new QuizEntity();
        quizEntity.map(quizModel);
        this.quizSupport.assertEquals(quizModel, quizEntity);
    }

}
