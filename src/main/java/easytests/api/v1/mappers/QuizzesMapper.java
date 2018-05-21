package easytests.api.v1.mappers;

import easytests.api.v1.models.Quiz;
import easytests.api.v1.models.Testee;
import easytests.core.models.QuizModel;
import easytests.core.models.TesteeModel;
import easytests.core.models.TesteeModelInterface;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.TesteeModelEmpty;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Destination;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @author miron97
 */
@Service("QuizzesMapperV1")
public class QuizzesMapper extends ModelMapper {
    public QuizzesMapper() {
        super();
        this.createTypeMap(QuizModel.class, Quiz.class)
                .addMappings(mapper -> mapper.when(context -> !(context.getSource() instanceof ModelsListEmpty))
                        .<Integer>map(quizModel -> quizModel.getTestee().getQuiz().getId(), (quiz, id) -> quiz.getTestee().getQuiz().setId(id)))
                .addMappings(mapper -> mapper.when(context -> !(context.getSource() instanceof ModelsListEmpty))
                        .<Integer>map(quizModel -> quizModel.getTestee().getId(), (quiz, id) -> quiz.getTestee().setId(id)))
                .addMappings(mapper -> mapper.using(src -> ((LocalDateTime)src.getSource()).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "Z")
                        .<String>map(QuizModel::getStartedAt, (quiz, startedAt) -> quiz.setStartedAt(startedAt)))
                .addMappings(mapper -> mapper.using(src -> ((LocalDateTime)src.getSource()).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + "Z")
                        .<String>map(QuizModel::getFinishedAt, (quiz, finishedAt) -> quiz.setFinishedAt(finishedAt)));
    }
}
