package easytests.personal.dto;

import easytests.models.*;
import easytests.models.empty.SubjectModelEmpty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import lombok.Data;

/**
 * @author SingularityA
 */
@Data
public class IssueStandardDto {

    @Null
    private Integer id;

    @Min(1)
    @Max(1500)
    private Integer timeLimit;

    @Min(1)
    @Max(3000)
    private Integer questionsNumber;

    @Valid
    private List<IssueStandardTopicPriorityDto> topicPriorities;

    @Valid
    private List<IssueStandardQuestionTypeOptionDto> questionTypeOptions;

    @NotNull
    private Integer subjectId;

    public void map(IssueStandardModelInterface issueStandardModel) {
        final List<IssueStandardTopicPriorityDto> topicPriorityDtoList
                = new ArrayList<>(issueStandardModel.getTopicPriorities().size());

        for (IssueStandardTopicPriorityModelInterface topicPriorityModel: issueStandardModel.getTopicPriorities()) {
            final IssueStandardTopicPriorityDto topicPriorityDto = new IssueStandardTopicPriorityDto();
            topicPriorityDto.map(topicPriorityModel);
            topicPriorityDtoList.add(topicPriorityDto);
        }

        final List<IssueStandardQuestionTypeOptionDto> questionTypeOptionDtoList
                = new ArrayList<>(issueStandardModel.getQuestionTypeOptions().size());

        for (IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                : issueStandardModel.getQuestionTypeOptions()) {

            final IssueStandardQuestionTypeOptionDto questionTypeOptionDto = new IssueStandardQuestionTypeOptionDto();
            questionTypeOptionDto.map(questionTypeOptionModel);
            questionTypeOptionDtoList.add(questionTypeOptionDto);
        }

        this.setTimeLimit(issueStandardModel.getTimeLimit());
        this.setQuestionsNumber(issueStandardModel.getQuestionsNumber());
        this.setTopicPriorities(topicPriorityDtoList);
        this.setQuestionTypeOptions(questionTypeOptionDtoList);
        this.setSubjectId(issueStandardModel.getSubject().getId());
    }

    public void mapInto(IssueStandardModelInterface issueStandardModel) {

        final List<IssueStandardTopicPriorityModelInterface> topicPriorityModelList
                = new ArrayList<>(this.getTopicPriorities().size());

        for (IssueStandardTopicPriorityDto topicPriorityDto: this.getTopicPriorities()) {
            final IssueStandardTopicPriorityModelInterface topicPriorityModel
                    = new IssueStandardTopicPriorityModel();
            topicPriorityDto.mapInto(topicPriorityModel, this.getId());
            topicPriorityModelList.add(topicPriorityModel);
        }

        final List<IssueStandardQuestionTypeOptionModelInterface> questionTypeOptionModelList
                = new ArrayList<>(this.getQuestionTypeOptions().size());

        for (IssueStandardQuestionTypeOptionDto questionTypeOptionDto: this.getQuestionTypeOptions()) {
            final IssueStandardQuestionTypeOptionModelInterface questionTypeOptionModel
                    = new IssueStandardQuestionTypeOptionModel();
            questionTypeOptionDto.mapInto(questionTypeOptionModel, this.getId());
            questionTypeOptionModelList.add(questionTypeOptionModel);
        }

        issueStandardModel.setTimeLimit(this.getTimeLimit());
        issueStandardModel.setQuestionsNumber(this.getQuestionsNumber());
        issueStandardModel.setTopicPriorities(topicPriorityModelList);
        issueStandardModel.setQuestionTypeOptions(questionTypeOptionModelList);
        issueStandardModel.setSubject(new SubjectModelEmpty(this.getSubjectId()));
    }
}
