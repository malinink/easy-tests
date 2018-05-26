package easytests.api.v1.mappers;

import easytests.api.v1.models.AdminAnswer;
import easytests.api.v1.models.Identity;
import easytests.api.v1.models.Question;
import easytests.core.models.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author RisaMagpie
 */
@Service("QuestionsMapperV1")
public class QuestionsMapper extends ModelMapper {
    public QuestionsMapper() {
        super();
        //-----------------------
        Converter<Identity, TopicModel> convertIdentityToTopicModel = new Converter<Identity, TopicModel>() {
            public TopicModel convert(MappingContext<Identity, TopicModel> context) {
                TopicModel topicModel = new TopicModel();
                topicModel.setId(context.getSource().getId());

                return topicModel;
            }
        };

        Converter<AdminAnswer, AnswerModel> convertAdminAnswerToAnswerModel = new Converter<AdminAnswer, AnswerModel>() {
            public AnswerModel convert(MappingContext<AdminAnswer, AnswerModel> context) {
                AnswerModel answerModel = new AnswerModel();
                answerModel.setId(context.getSource().getId());

                return answerModel;
            }
        };

        Converter<AdminAnswer, AnswerModel> convertIdentityToAnswerModel = new Converter<AdminAnswer, AnswerModel>() {
            public AnswerModel convert(MappingContext<AdminAnswer, AnswerModel> context) {
                AnswerModel answerModel = new AnswerModel();
                answerModel.setId(context.getSource().getId());

                return answerModel;
            }
        };

        Converter<List<AdminAnswer>, List<AnswerModel>> convertAdminAnswerListToAnswerModelList
                = new Converter<List<AdminAnswer>, List<AnswerModel>>() {
            public List<AnswerModel> convert(MappingContext<List<AdminAnswer>, List<AnswerModel>> context) {
                /*List<AnswerModel> answerModels = new ArrayList<>();
                for (AdminAnswer adminAnswer: context.getSource()) {
                    answerModels.add();
                }*/

                List<AnswerModel> answerModels = new ArrayList<>();
                for (int i = 0; i < context.getSource().size(); ++i) {
                    AdminAnswer adminAnswer = context.getSource().get(i);

                    AnswerModel answerModel = new AnswerModel();

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
                QuestionTypeModel questionTypeModel = new QuestionTypeModel();
                questionTypeModel.setId(context.getSource());

                return questionTypeModel;
            }
        };

        PropertyMap<Question, QuestionModel> mymap = new PropertyMap<Question, QuestionModel>() {
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
