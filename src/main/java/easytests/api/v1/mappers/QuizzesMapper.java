package easytests.api.v1.mappers;

import easytests.api.v1.models.Quiz;
import easytests.api.v1.models.Testee;
import easytests.core.models.QuizModel;
import easytests.core.models.empty.AbstractModelEmpty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.modelmapper.Condition;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


/**
 * @author miron97
 */
@Service("QuizzesMapperV1")
public class QuizzesMapper extends ModelMapper {
    private static final String Z = "Z";

    private Condition notEmptyModel = context -> !(context.getSource() instanceof AbstractModelEmpty);

    private Condition notNull = context -> context.getSource() != null;

    private Converter<LocalDateTime, String> localDateTimeStringConverter = dateTime -> {
        if (dateTime.getSource() != null) {
            return dateTime.getSource().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + Z;
        } else {
            return null;
        }
    };

    public QuizzesMapper() {
        super();
        this.createTypeMap(QuizModel.class, Quiz.class)
                .addMappings(mapper -> mapper.<Integer>map(quizModel -> quizModel.getIssue().getId(),
                        (quiz, issueId) -> quiz.getIssue().setId(issueId)))
                .addMappings(mapper -> mapper.when(notEmptyModel)
                        .<Testee>map(quizModel -> quizModel.getTestee(),
                            (quiz, testee) -> quiz.setTestee(testee)))
                .addMappings(mapper -> mapper.when(notEmptyModel)
                        .<Integer>map(quizModel -> quizModel.getTestee().getId(),
                            (quiz, id) -> quiz.getTestee().setId(id)))
                .addMappings(mapper -> mapper.when(notEmptyModel)
                        .<Integer>map(quizModel -> quizModel.getTestee().getQuiz().getId(),
                            (quiz, id) -> quiz.getTestee().getQuiz().setId(id)))
                .addMappings(mapper -> mapper.using(localDateTimeStringConverter)
                        .<String>map(QuizModel::getStartedAt, (quiz, startedAt) -> quiz.setStartedAt(startedAt)))
                .addMappings(mapper -> mapper.using(localDateTimeStringConverter)
                        .<String>map(QuizModel::getFinishedAt, (quiz, finishedAt) -> quiz.setFinishedAt(finishedAt)));
    }
}
