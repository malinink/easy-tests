package easytests.models.empty;

import easytests.entities.SolutionEntity;
import easytests.models.AnswerModelInterface;
import easytests.models.PointModelInterface;
import easytests.models.SolutionModelInterface;
import easytests.models.exceptions.CallMethodOnEmptyModelException;
import easytests.models.exceptions.CreateEmptyModelWithoutIdException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import java.lang.reflect.Method;

/**
 * @author SingularityA
 */
public class SolutionModelEmptyTest {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void testConstructors() {
        Integer id = 1;
        SolutionModelInterface solutionModel = new SolutionModelEmpty(id);
        Assert.assertEquals(id, solutionModel.getId());

        exception.expect(CreateEmptyModelWithoutIdException.class);
        solutionModel = new SolutionModelEmpty();
    }

    @Test
    public void testId() {
        Integer id = 1;
        final SolutionModelInterface solutionModel = new SolutionModelEmpty(id);
        Assert.assertEquals(id, solutionModel.getId());

        exception.expect(CallMethodOnEmptyModelException.class);
        solutionModel.setId(2);
    }

    @Test
    public void testAnswer() {
        Integer id = 1;
        AnswerModelInterface answer = Mockito.mock(AnswerModelInterface.class);
        final SolutionModelInterface solutionModel = new SolutionModelEmpty(id);

        exception.expect(CallMethodOnEmptyModelException.class);
        solutionModel.setAnswer(answer);
        solutionModel.getAnswer();
    }

    @Test
    public void testPoint() {
        Integer id = 1;
        PointModelInterface point = Mockito.mock(PointModelInterface.class);
        final SolutionModelInterface solutionModel = new SolutionModelEmpty(id);

        exception.expect(CallMethodOnEmptyModelException.class);
        solutionModel.setPoint(point);
        solutionModel.getPoint();
    }

    @Test
    public void testMap() {
        Integer id = 1;
        final SolutionEntity solutionEntity = Mockito.mock(SolutionEntity.class);
        final SolutionModelInterface solutionModel = new SolutionModelEmpty(id);

        exception.expect(CallMethodOnEmptyModelException.class);
        solutionModel.map(solutionEntity);
    }

    @Ignore
    @Test
    public void testMethods() {
        Integer id = 1;
        final SolutionModelInterface solutionModel = new SolutionModelEmpty(id);
        Method[] methods = solutionModel.getClass().getMethods();

        for (Method method: methods) {
            if (method.getName().equals("getId")) {
                Assert.assertEquals(id, solutionModel.getId());
            } else {
                try {
                    Class[] paramTypes = method.getParameterTypes();
                    Object[] args = new Object[paramTypes.length];

                    // не может создавать инстансы интерфейсов, потому падает
                    for (int i = 0; i < args.length; i++) {
                        args[i] = paramTypes[i].newInstance();
                    }
                    method.invoke(solutionModel, args);
                } catch (Exception exc) {
                    Assert.assertEquals(exc.getClass(), CallMethodOnEmptyModelException.class);
                }
            }
        }
    }
}
