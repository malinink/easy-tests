package easytests.core.mappers.providers;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

/**
 * @author vkpankov
 */
public class MapperComponentProvider extends ClassPathScanningCandidateComponentProvider {
    public MapperComponentProvider() {
        super(false);
        addIncludeFilter(new AnnotationTypeFilter(Mapper.class, false));
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface();
    }
}
