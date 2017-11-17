package easytests.core.mappers.providers;

import easytests.core.mappers.AbstractMapperTest;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AssignableTypeFilter;

/**
 * @author malinink
 */
public class MapperTestComponentProvider extends ClassPathScanningCandidateComponentProvider {
    public MapperTestComponentProvider() {
        super(false);
        addIncludeFilter(new AssignableTypeFilter(AbstractMapperTest.class));
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return !beanDefinition.getMetadata().isAbstract();
    }
}
