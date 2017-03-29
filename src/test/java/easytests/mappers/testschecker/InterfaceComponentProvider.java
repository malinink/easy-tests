package easytests.mappers.testschecker;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * @author vkpankov
 */
class InterfaceComponentProvider extends ClassPathScanningCandidateComponentProvider {
    InterfaceComponentProvider() {
        super(false);
        addIncludeFilter(new AnnotationTypeFilter(Mapper.class, false));
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface();
    }
}
