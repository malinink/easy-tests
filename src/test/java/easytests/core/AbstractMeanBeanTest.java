package easytests.core;

import easytests.core.models.*;
import easytests.support.meanbean.*;
import easytests.support.meanbean.models.*;
import java.time.LocalDateTime;
import org.meanbean.lang.Factory;
import org.meanbean.test.*;


/**
 * @author malinink
 */
public class AbstractMeanBeanTest {

    private static Object[][] mappedFactories = new Object[][]{
            {
                    LocalDateTime.class,
                    new LocalDateTimeFactory()
            },
            {
                    TopicModelInterface.class,
                    new TopicModelFactory()
            },
            {
                    QuestionTypeModelInterface.class,
                    new QuestionTypeModelFactory()
            },
            {
                    IssueModelInterface.class,
                    new IssueModelFactory()
            },
            {
                    TesteeModelInterface.class,
                    new TesteeModelFactory()
            },
            {
                    QuizModelInterface.class,
                    new QuizModelFactory()
            },
            {
                    PointModelInterface.class,
                    new PointModelFactory()
            },
            {
                    AnswerModelInterface.class,
                    new AnswerModelFactory()
            },
            {
                    IssueStandardModelInterface.class,
                    new IssueStandardModelFactory()
            },
            {
                    UserModelInterface.class,
                    new UserModelFactory()
            },
            {
                    QuestionModelInterface.class,
                    new QuestionModelFactory()
            },
            {
                    SubjectModelInterface.class,
                    new SubjectModelFactory()
            }
    };

    private BeanTester beanTester;

    private EqualsMethodTester equalsMethodTester;

    private HashCodeMethodTester hashCodeMethodTester;

    private Configuration configuration;

    protected Configuration getConfiguration() {
        if (this.configuration == null) {
            this.configuration = new ConfigurationBuilder().iterations(10).build();
        }

        return this.configuration;
    }

    protected void addBeanTesterFactory(Class<?> item, Factory factory) {
        this.getBeanTester().getFactoryCollection().addFactory(item, factory);
    }

    protected void addBeanTesterFactories() {
        for (Object[] mappedFactory: mappedFactories) {
            this.addBeanTesterFactory((Class) mappedFactory[0], (Factory) mappedFactory[1]);
        }
    }

    protected BeanTester getBeanTester() {
        if (this.beanTester == null) {
            this.beanTester = new BeanTester();
            this.addBeanTesterFactories();
        }

        return this.beanTester;
    }

    protected void addEqualsMethodTesterFactory(Class<?> item, Factory factory) {
        this.getEqualsMethodTester().getFactoryCollection().addFactory(item, factory);
    }

    protected void addEqualsMethodTesterFactories() {
        for (Object[] mappedFactory: mappedFactories) {
            this.addEqualsMethodTesterFactory((Class) mappedFactory[0], (Factory) mappedFactory[1]);
        }
    }

    protected EqualsMethodTester getEqualsMethodTester() {
        if (this.equalsMethodTester == null) {
            this.equalsMethodTester = new EqualsMethodTester();
            this.addEqualsMethodTesterFactories();
        }

        return this.equalsMethodTester;
    }

    protected void addHashCodeMethodTesterFactory(Class<?> item, Factory factory) {
        this.getHashCodeMethodTester().getFactoryCollection().addFactory(item, factory);
    }

    protected void addHashCodeMethodTesterFactories() {
        for (Object[] mappedFactory: mappedFactories) {
            this.addHashCodeMethodTesterFactory((Class) mappedFactory[0], (Factory) mappedFactory[1]);
        }
    }

    protected HashCodeMethodTester getHashCodeMethodTester() {
        if (this.hashCodeMethodTester == null) {
            this.hashCodeMethodTester = new HashCodeMethodTester();
            this.addHashCodeMethodTesterFactories();
        }

        return this.hashCodeMethodTester;
    }
}
