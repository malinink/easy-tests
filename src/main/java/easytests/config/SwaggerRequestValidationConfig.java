package easytests.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import easytests.swagger.SwaggerValidationFilter;
import easytests.swagger.SwaggerValidationInterceptor;
import java.io.IOException;
import javax.servlet.Filter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author malinink
 */
@Configuration
@EnableWebMvc
public class SwaggerRequestValidationConfig implements WebMvcConfigurer {

    private final SwaggerValidationInterceptor swaggerValidationInterceptor;

    /**
     * @param yamlResource the {@link Resource} to your Swagger schema
     */
    @Autowired
    public SwaggerRequestValidationConfig(
            @Value("classpath:api/api.yaml") final Resource yamlResource
    ) throws IOException {
        final String json = (new ObjectMapper()).writeValueAsString(
                (new ObjectMapper(new YAMLFactory())).readValue(
                        FileUtils.readFileToString(yamlResource.getFile()),
                        Object.class
                )
        );

        final EncodedResource swaggerResource = new EncodedResource(new ByteArrayResource(json.getBytes()), "UTF-8");
        this.swaggerValidationInterceptor = new SwaggerValidationInterceptor(swaggerResource);
    }

    @Bean
    public Filter swaggerValidationFilter() {
        return new SwaggerValidationFilter();
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(this.swaggerValidationInterceptor);
    }
}
