package easytests.api.v1.mappers;

import easytests.api.v1.models.AdminAnswer;
import easytests.api.v1.models.Question;
import easytests.core.models.AnswerModel;
import easytests.core.models.QuestionModel;
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
            .addMappings(mapper -> {
                mapper.<Integer>map(questionModel -> questionModel.getQuestionType().getId(),
                    (question, id) -> question.setType(id));

                mapper.<Integer>map(questionModel -> questionModel.getTopic().getId(),
                    (question, id) -> question.getTopic().setId(id));

                mapper.<List<AdminAnswer>>map(questionModel -> questionModel.getAnswers(),
                    (question, list) -> question.setAnswers(list));
            }
            );
        this.createTypeMap(AnswerModel.class, AdminAnswer.class)
            .addMappings(mapper -> {
                mapper.<String>map(answerModel -> answerModel.getTxt(),
                    (answer, text) -> answer.setText(text));

                mapper.<Boolean>map(answerModel -> answerModel.getRight(),
                    (answer, right) -> answer.setIsRight(right));
            }
            );
    }
}
