package easytests.core.entities;

import easytests.core.models.QuizModelInterface;
import easytests.core.models.empty.IssueModelEmpty;
import easytests.support.QuizzesSupport;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.meanbean.test.BeanTester;
import org.meanbean.test.ConfigurationBuilder;
import org.meanbean.test.EqualsMethodTester;
import org.meanbean.test.HashCodeMethodTester;
import org.mockito.Mockito;

import java.time.LocalDateTime;

/**
 * @author miron97
 */
public class QuizEntityTest extends AbstractEntityTest {

    private QuizzesSupport quizSupport = new QuizzesSupport();

    @Override
    protected ConfigurationBuilder getConfigurationBuilder(){
        return super.getConfigurationBuilder()
                .ignoreProperty("startedAt")
                .ignoreProperty("finishedAt");
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
