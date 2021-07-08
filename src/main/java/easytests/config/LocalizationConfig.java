package easytests.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

/**
 * @author Loriens
 */
@Configuration
public class LocalizationConfig {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        final ReloadableResourceBundleMessageSource resourseBundle = new
                ReloadableResourceBundleMessageSource();
        resourseBundle.setBasename("classpath:/locales/sign-in");
        resourseBundle.setDefaultEncoding("UTF-8");
        return resourseBundle;
    }
}
