package easytests.api.v1.mappers;

import easytests.api.v1.models.AdminAnswer;
import easytests.api.v1.models.Identity;
import easytests.api.v1.models.Question;
import easytests.core.models.*;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

/**
 * @author RisaMagpie
 */
@Service("QuestionsMapperV1")
public class QuestionsMapper extends ModelMapper {
    public QuestionsMapper() {
        super();
        //-----------------------
        Converter<Identity, TopicModel> convertIdentityToTopicModel
                = new Converter<Identity, TopicModel>() {
            public TopicModel convert(MappingContext<Identity, TopicModel> context) {
                final TopicModel topicModel = new TopicModel();
                topicModel.setId(context.getSource().getId());

                return topicModel;
            }
        };

        Converter<AdminAnswer, AnswerModel> convertAdminAnswerToAnswerModel
                = new Converter<AdminAnswer, AnswerModel>() {
            public AnswerModel convert(MappingContext<AdminAnswer, AnswerModel> context) {
                final AnswerModel answerModel = new AnswerModel();
                answerModel.setId(context.getSource().getId());

                return answerModel;
            }
        };

        Converter<AdminAnswer, AnswerModel> convertIdentityToAnswerModel
                = new Converter<AdminAnswer, AnswerModel>() {
            public AnswerModel convert(MappingContext<AdminAnswer, AnswerModel> context) {
                final AnswerModel answerModel = new AnswerModel();
                answerModel.setId(context.getSource().getId());

                return answerModel;
            }
        };

        Converter<List<AdminAnswer>, List<AnswerModel>> convertAdminAnswerListToAnswerModelList
                = new Converter<List<AdminAnswer>, List<AnswerModel>>() {
            public List<AnswerModel> convert(MappingContext<List<AdminAnswer>, List<AnswerModel>> context) {
                final List<AnswerModel> answerModels = new ArrayList<>();
                for (int i = 0; i < context.getSource().size(); ++i) {
                    final AdminAnswer adminAnswer = context.getSource().get(i);

                    final AnswerModel answerModel = new AnswerModel();

                    answerModel.setId(adminAnswer.getId());
                    answerModel.setRight(adminAnswer.getIsRight());
                    answerModel.setTxt(adminAnswer.getText());
                    answerModel.setSerialNumber(adminAnswer.getNumber());

                    answerModels.add(answerModel);
                }
                return answerModels;
            }
        };
        Converter<Integer, QuestionTypeModel> convertIntegerToQuestionTypeModel
                = new Converter<Integer, QuestionTypeModel>() {
            public QuestionTypeModel convert(MappingContext<Integer, QuestionTypeModel> context) {
                final QuestionTypeModel questionTypeModel = new QuestionTypeModel();
                questionTypeModel.setId(context.getSource());

                return questionTypeModel;
            }
        };

        final PropertyMap<Question, QuestionModel> mymap = new PropertyMap<Question, QuestionModel>() {
            protected void configure() {
                map(source.getId()).setId(null);
                map(source.getText()).setText(null);
                using(convertIntegerToQuestionTypeModel).map(source.getType()).setQuestionType(null);
                using(convertIdentityToTopicModel).map(source.getTopic()).setTopic(null);
                using(convertAdminAnswerListToAnswerModelList).map(source.getAnswers()).setAnswers(null);
            }
        };

        this.addMappings(mymap);
    }
}
