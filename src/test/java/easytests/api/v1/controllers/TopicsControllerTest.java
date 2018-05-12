package easytests.api.v1.controllers;

import easytests.api.v1.mappers.TopicsMapper;
import easytests.config.SwaggerRequestValidationConfig;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.TopicModel;
import easytests.core.models.TopicModelInterface;
import easytests.core.options.builder.TopicsOptionsBuilderInterface;
import easytests.core.services.TopicsServiceInterface;
import easytests.support.JsonSupport;
import easytests.support.TopicsSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author lelay
 */
@Import({TopicsMapper.class, SwaggerRequestValidationConfig.class})
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TopicsController.class, secure = false)
public class TopicsControllerTest {
    private static final String id = "id";
    private static final String name = "name";
    private static final String subject = "subject";
    private static final String subjectId = "subjectId";
    private static final String subjectIdParamValue = "2";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TopicsServiceInterface topicsService;

    @MockBean
    TopicsOptionsBuilderInterface topicsOptionsBuilder;

    private TopicsSupport topicsSupport = new TopicsSupport();

    @Test
    public void testListSuccess() throws Exception {
        final List<TopicModelInterface> topicsModels = new ArrayList<>();
        IntStream.range(0, 2).forEach(idx -> {
            final TopicModel topicModel = new TopicModel();
            topicModel.map(this.topicsSupport.getEntityFixtureMock(idx));
            topicsModels.add(topicModel);
        });
        when(this.topicsService.findBySubject(any(SubjectModelInterface.class))).thenReturn(topicsModels);

        String myJson = new JsonSupport()
                .with(id, topicsModels.get(0).getId())
                .with(name, topicsModels.get(0).getName())
                .with(subject, new JsonSupport()
                        .with(id, topicsModels.get(0).getSubject().getId())
                )
                .build();

        mvc.perform(get("/v1/topics")
                .param(subjectId, subjectIdParamValue)
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk()) //todo: test has failed this line
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new JsonSupport()
                        .with(new JsonSupport()
                                .with(id, topicsModels.get(0).getId())
                                .with(name, topicsModels.get(0).getName())
                                .with(subject, new JsonSupport()
                                        .with(id, topicsModels.get(0).getSubject().getId())
                                )
                        )
                        .with(new JsonSupport()
                                .with(id, topicsModels.get(1).getId())
                                .with(name, topicsModels.get(1).getName())
                                .with(subject, new JsonSupport()
                                        .with(id, topicsModels.get(0).getSubject().getId())
                                )
                        )
                        .build()
                ))
                .andReturn();

    }
}
