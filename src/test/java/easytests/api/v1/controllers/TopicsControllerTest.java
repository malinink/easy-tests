package easytests.api.v1.controllers;

import easytests.api.v1.mappers.TopicsMapper;
import easytests.auth.services.AccessControlLayerServiceInterface;
import easytests.config.SwaggerRequestValidationConfig;
import easytests.core.entities.TopicEntity;
import easytests.core.models.SubjectModel;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.TopicModel;
import easytests.core.models.TopicModelInterface;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.SubjectModelEmpty;
import easytests.core.options.builder.SubjectsOptionsBuilder;
import easytests.core.services.SubjectsServiceInterface;
import easytests.core.services.TopicsServiceInterface;
import easytests.support.JsonSupport;
import easytests.support.TopicsSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    private static final String subjectIdParamValue = "2";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TopicsServiceInterface topicsService;

    @MockBean
    private SubjectsServiceInterface subjectsService;

    @MockBean
    private AccessControlLayerServiceInterface acl;

    @MockBean
    protected SubjectsOptionsBuilder subjectsOptionsBuilder;

    @Autowired
    @Qualifier("TopicsMapperV1")
    private TopicsMapper topicsMapper;

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

        when(this.subjectsService.find(Integer.valueOf(subjectIdParamValue)))
                .thenReturn(new SubjectModelEmpty(Integer.valueOf(subjectIdParamValue)));
        when(this.acl.hasAccess(any(SubjectModelInterface.class)))
                .thenReturn(true);

        final String receivedModelsJson = new JsonSupport()
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
                                .with(id, topicsModels.get(1).getSubject().getId())
                        )
                )
                .build();

        mvc.perform(get("/v1/topics?subjectId={subjectIdParamValue}", subjectIdParamValue)
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(receivedModelsJson))
                .andReturn();

    }

    @Test
    public void testListNotFound() throws Exception {
        final String nonexistentSubjectId = "5";

        this.mvc.perform(get("/v1/topics?subjectId={nonexistentSubjectId}", nonexistentSubjectId)
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testListForbidden() throws Exception {

        when(this.subjectsService.find(Integer.valueOf(subjectIdParamValue)))
                .thenReturn(new SubjectModelEmpty(Integer.valueOf(subjectIdParamValue)));
        when(this.acl.hasAccess(any(SubjectModelInterface.class))).thenReturn(false);

        this.mvc.perform(get("/v1/topics?subjectId={subjectIdParamValue}", subjectIdParamValue)
                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();

    }

    @Test
    public void testCreateSuccess() throws Exception {
        final TopicEntity topicAdditionalEntity = this.topicsSupport.getEntityAdditionalMock(0);
        when(topicAdditionalEntity.getId()).thenReturn(5);

        when(this.subjectsService.find(any(), any())).thenReturn(new SubjectModel());
        when(this.acl.hasAccess(any(SubjectModelInterface.class))).thenReturn(true);

        ArgumentCaptor<TopicModelInterface> argumentCaptor = ArgumentCaptor.forClass(TopicModelInterface.class);
        doAnswer(invocation -> {
            final TopicModel topicModel = (TopicModel) invocation.getArguments()[0];
            topicModel.setId(5);
            topicModel.setQuestions(new ModelsListEmpty());
            return null;
        }).when(this.topicsService).save(any(TopicModelInterface.class));

        mvc.perform(post("/v1/topics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(name, topicAdditionalEntity.getName())
                        .with(subject, new JsonSupport().with(id, topicAdditionalEntity.getSubjectId()))
                        .build()
                ))
                .andExpect(status().is(201))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(
                        new JsonSupport()
                                .with(id, 5)
                                .build()
                ))
                .andReturn();
        verify(this.topicsService, times(1)).save(argumentCaptor.capture());
        this.topicsSupport.assertEquals(topicAdditionalEntity, argumentCaptor.getValue());
    }

    @Test
    public void testCreateWithIdFailed() throws Exception {
        mvc.perform(post("/v1/topics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                        .with(id, 2)
                        .with(name, "TopicName")
                        .with(subject, new JsonSupport().with(id, 1))
                        .build()
                ))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""))
                .andReturn();
    }


    @Test
    public void testCreateBadRequest() throws Exception {
        when(this.subjectsService.find(any(), any())).thenReturn(new SubjectModel());
        when(this.acl.hasAccess(any(SubjectModelInterface.class))).thenReturn(false);


        mvc.perform(post("/v1/topics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new JsonSupport()
                                .with(name, "TopicName")
                                .with(subject, new JsonSupport().with(id, 2))
                                .build()
                ))
                .andExpect(status().isBadRequest())
                .andExpect(content().string(""))
                .andReturn();
    }



    @Test
    public void testShowSuccess() throws Exception {
        final TopicModelInterface topicModel = new TopicModel();
        topicModel.map(this.topicsSupport.getEntityFixtureMock(0));
        when(this.topicsService.find(any(Integer.class))).thenReturn(topicModel);
        when(this.acl.hasAccess(any(TopicModelInterface.class))).thenReturn(true);

        mvc.perform(get("/v1/topics/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(new JsonSupport()
                        .with(id, topicModel.getId())
                        .with(name, topicModel.getName())
                        .with(subject, new JsonSupport()
                                .with(id, topicModel.getSubject().getId())
                        )
                        .build()
                ))
                .andReturn();
    }

    @Test
    public void testShowNotFound() throws Exception {
        when(this.topicsService.find(any(Integer.class))).thenReturn(null);

        mvc.perform(get("/v1/topics/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string(""))
                .andReturn();
    }

    @Test
    public void testShowForbidden() throws Exception {
        final TopicModelInterface topicModel = new TopicModel();
        topicModel.map(this.topicsSupport.getEntityFixtureMock(0));
        when(this.topicsService.find(any(Integer.class))).thenReturn(topicModel);
        when(this.acl.hasAccess(any(TopicModelInterface.class))).thenReturn(false);

        mvc.perform(get("/v1/topics/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string(""))
                .andReturn();
    }
}
