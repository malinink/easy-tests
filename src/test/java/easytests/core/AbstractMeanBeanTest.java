package easytests.core;

import easytests.support.meanbean.LocalDateTimeFactory;
import org.meanbean.lang.Factory;
import org.meanbean.test.*;
import java.time.LocalDateTime;


public class AbstractMeanBeanTest {

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
        this.addBeanTesterFactory(LocalDateTime.class, new LocalDateTimeFactory());
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
        this.addEqualsMethodTesterFactory(LocalDateTime.class, new LocalDateTimeFactory());
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
        this.addHashCodeMethodTesterFactory(LocalDateTime.class, new LocalDateTimeFactory());
    }

    protected HashCodeMethodTester getHashCodeMethodTester() {
        if (this.hashCodeMethodTester == null) {
            this.hashCodeMethodTester = new HashCodeMethodTester();
            this.addHashCodeMethodTesterFactories();
        }

        return this.hashCodeMethodTester;
    }
}
