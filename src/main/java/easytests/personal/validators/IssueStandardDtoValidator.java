package easytests.personal.validators;

import easytests.common.validators.AbstractDtoValidator;
import easytests.models.IssueStandardQuestionTypeOptionModelInterface;
import easytests.models.IssueStandardTopicPriorityModelInterface;
import easytests.models.empty.IssueStandardModelEmpty;
import easytests.personal.dto.IssueStandardDto;
import easytests.personal.dto.IssueStandardQuestionTypeOptionDto;
import easytests.personal.dto.IssueStandardTopicPriorityDto;
import easytests.services.IssueStandardQuestionTypeOptionsService;
import easytests.services.IssueStandardTopicPrioritiesService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * @author SingularityA
 */
@Component("issueStandardDtoValidator")
@SuppressWarnings("checkstyle:MultipleStringLiterals")
public class IssueStandardDtoValidator extends AbstractDtoValidator {

    @Autowired
    private IssueStandardTopicPrioritiesService topicPrioritiesService;

    @Autowired
    private IssueStandardQuestionTypeOptionsService questionTypeOptionsService;

    public boolean supports(Class clazz) {
        return IssueStandardDto.class.isAssignableFrom(clazz);
    }

    public void validate(Object object, Errors errors) {
        final IssueStandardDto issueStandardDto = (IssueStandardDto) object;
        validateTopicPriorities(errors, issueStandardDto);
        validateQuestionTypeOptions(errors, issueStandardDto);
    }

    private void validateTopicPriorities(Errors errors, IssueStandardDto issueStandardDto) {
        validateTopicPriorityDtoIdBelongsToIssueStandard(errors, issueStandardDto);
        validateTopicPriorityIdsAreUnique(errors, issueStandardDto);
        validateTopicsAreUnique(errors, issueStandardDto);
    }

    private void validateQuestionTypeOptions(Errors errors, IssueStandardDto issueStandardDto) {
        validateQuestionTypeOptionDtoIdBelongsToIssueStandard(errors, issueStandardDto);
        validateQuestionTypeOptionIdsAreUnique(errors, issueStandardDto);
        validateQuestionTypesAreUnique(errors, issueStandardDto);
        validateMaxIsGreaterThanMin(errors, issueStandardDto);
    }

    private void validateTopicPriorityDtoIdBelongsToIssueStandard(Errors errors, IssueStandardDto issueStandardDto) {
        final List<IssueStandardTopicPriorityModelInterface> tpModels
                = this.topicPrioritiesService
                .findByIssueStandard(new IssueStandardModelEmpty(issueStandardDto.getId()));

        final List<Integer> tpModelIds = new ArrayList<>(tpModels.size());
        for (IssueStandardTopicPriorityModelInterface tpModel: tpModels) {
            tpModelIds.add(tpModel.getId());
        }
        int index = 0;
        for (IssueStandardTopicPriorityDto tpDto: issueStandardDto.getTopicPriorities()) {
            if (tpDto.getId() != null && !tpModelIds.contains(tpDto.getId())) {
                reject(errors, tpField(index, "id"), "Foreign topicPriority id for IssueStandard entity");
            }
            index++;
        }
    }

    private void validateTopicPriorityIdsAreUnique(Errors errors, IssueStandardDto issueStandardDto) {
        final List<IssueStandardTopicPriorityDto> tpDtoList = issueStandardDto.getTopicPriorities();

        for (int i = 0; i < tpDtoList.size(); i++) {
            if (tpDtoList.get(i).getId() == null) {
                continue;
            }
            for (int j = 0; j < tpDtoList.size(); j++) {
                if (i != j && tpDtoList.get(i).getId().equals(tpDtoList.get(j).getId())) {
                    reject(errors, tpField(i, "id"), "Topic Priority id must be unique");
                    break;
                }
            }
        }
    }

    private void validateTopicsAreUnique(Errors errors, IssueStandardDto issueStandardDto) {
        final List<IssueStandardTopicPriorityDto> tpDtoList = issueStandardDto.getTopicPriorities();

        for (int i = 0; i < tpDtoList.size(); i++) {
            if (tpDtoList.get(i).getTopicId() == null) {
                continue;
            }
            for (int j = 0; j < tpDtoList.size(); j++) {
                if (i != j && tpDtoList.get(i).getTopicId().equals(tpDtoList.get(j).getTopicId())) {
                    reject(errors, tpField(i, "topicId"), "Topic must be unique");
                    break;
                }
            }
        }
    }

    private void validateQuestionTypeOptionDtoIdBelongsToIssueStandard(Errors errors,
                                                                      IssueStandardDto issueStandardDto) {
        final List<IssueStandardQuestionTypeOptionModelInterface> qtoModels
                = this.questionTypeOptionsService
                .findByIssueStandard(new IssueStandardModelEmpty(issueStandardDto.getId()));

        final List<Integer> qtoModelIds = new ArrayList<>(qtoModels.size());
        for (IssueStandardQuestionTypeOptionModelInterface qtoModel : qtoModels) {
            qtoModelIds.add(qtoModel.getId());
        }
        int index = 0;
        for (IssueStandardQuestionTypeOptionDto qtoDto: issueStandardDto.getQuestionTypeOptions()) {
            if (qtoDto.getId() != null && !qtoModelIds.contains(qtoDto.getId())) {
                reject(errors, qtoField(index, "id"), "Foreign questionTypeOption id for IssueStandard entity");
            }
            index++;
        }
    }

    private void validateQuestionTypeOptionIdsAreUnique(Errors errors, IssueStandardDto issueStandardDto) {
        final List<IssueStandardQuestionTypeOptionDto> qtoDtoList = issueStandardDto.getQuestionTypeOptions();

        for (int i = 0; i < qtoDtoList.size(); i++) {
            if (qtoDtoList.get(i).getId() == null) {
                continue;
            }
            for (int j = 0; j < qtoDtoList.size(); j++) {
                if (i != j && qtoDtoList.get(i).getId().equals(qtoDtoList.get(j).getId())) {
                    reject(errors, qtoField(i, "id"), "Question Type Option id must be unique");
                    break;
                }
            }
        }
    }

    private void validateQuestionTypesAreUnique(Errors errors, IssueStandardDto issueStandardDto) {
        final List<IssueStandardQuestionTypeOptionDto> qtoDtoList = issueStandardDto.getQuestionTypeOptions();

        for (int i = 0; i < qtoDtoList.size(); i++) {
            if (qtoDtoList.get(i).getQuestionTypeId() == null) {
                continue;
            }
            for (int j = 0; j < qtoDtoList.size(); j++) {
                if (i != j && qtoDtoList.get(i).getQuestionTypeId().equals(qtoDtoList.get(j).getQuestionTypeId())) {
                    reject(errors, qtoField(i, "questionTypeId"), "Question Type must be unique");
                    break;
                }
            }
        }
    }

    private void validateMaxIsGreaterThanMin(Errors errors, IssueStandardDto issueStandardDto) {
        final List<IssueStandardQuestionTypeOptionDto> qtoDtoList = issueStandardDto.getQuestionTypeOptions();

        for (int i = 0; i < qtoDtoList.size(); i++) {
            final Integer minQuestions = qtoDtoList.get(i).getMinQuestions();
            final Integer maxQuestions = qtoDtoList.get(i).getMaxQuestions();
            if (minQuestions != null && maxQuestions != null && minQuestions > maxQuestions) {
                reject(errors, qtoField(i, "maxQuestions"), "Max Questions must be greater than Min Questions");
            }
        }
    }

    private static String tpField(int index, String propertyName) {
        return "topicPriorities[" + Integer.toString(index) + "]." + propertyName;
    }

    private static String qtoField(int index, String propertyName) {
        return "questionTypeOptions[" + Integer.toString(index) + "]." + propertyName;
    }
}
