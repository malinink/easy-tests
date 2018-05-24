package easytests.api.v1.mappers;

import easytests.api.v1.models.AdminAnswer;
import easytests.api.v1.models.Question;
import easytests.core.models.QuestionModel;
import easytests.core.models.empty.ModelsListEmpty;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


/**
 * @author RisaMagpie
 */
@Service("QuestionsMapperV1")
public class QuestionsMapper extends ModelMapper {
    public QuestionsMapper() {
        super();
        this.createTypeMap(QuestionModel.class, Question.class)
                .addMappings(
                        mapper -> {
                            mapper.when(
                                    context -> !(context.getSource() instanceof ModelsListEmpty)
                            ).<Integer>map(questionModel -> questionModel.getQuestionType().getId(),
                                    (question, id) -> question.setType(id));
                            mapper.when(
                                    context -> !(context.getSource() instanceof ModelsListEmpty)
                            ).<Integer>map(questionModel -> questionModel.getTopic().getId(),
                                    (question, id) -> question.getTopic().setId(id));
                            mapper.when(
                                    context -> !(context.getSource() instanceof ModelsListEmpty)
                            ).<List<AdminAnswer>>map(questionModel -> questionModel.getAnswers(),
                                    (question, list) -> question.setAnswers(list));
                        }
                );
    }
}