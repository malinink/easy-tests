package easytests.personal.dto;

import easytests.core.models.AnswerModel;
import easytests.core.models.AnswerModelInterface;
import easytests.core.models.QuestionModelInterface;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.Data;




/**
 * @author rezenbekk
 */
@Data
public class AnswerListDto {

    @Valid
    private List<AnswerDto> answersList;

    public void map(QuestionModelInterface questionModel) {
        final List<AnswerModelInterface> answers = questionModel.getAnswers();
        final List<AnswerDto> answerDtoList = new ArrayList<>(answers.size());
        for (AnswerModelInterface answer : answers) {
            final AnswerDto answerDto = new AnswerDto();
            answerDto.map(answer);
            answerDtoList.add(answerDto);
        }
        this.setAnswersList(answerDtoList);
        if (answerDtoList == null) {
            this.setAnswersList(new ArrayList<>());
        }
    }

    public void mapInto(QuestionModelInterface questionModel) {
        final List<AnswerDto> answerDtoList = this.getAnswersList();
        final List<AnswerModelInterface> answers = new ArrayList<>(this.getAnswersList().size());
        for (AnswerDto answerDto: answerDtoList) {
            final AnswerModelInterface answer = new AnswerModel();
            answerDto.mapInto(answer);
            answers.add(answer);
        }
        questionModel.setAnswers(answers);
    }
}

