package easytests.personal.validators;

import easytests.common.validators.AbstractDtoValidator;
import easytests.models.IdentityInterface;
import easytests.models.IssueStandardQuestionTypeOptionModelInterface;
import easytests.models.IssueStandardTopicPriorityModelInterface;
import easytests.models.empty.IssueStandardModelEmpty;
import easytests.personal.dto.IssueStandardDto;
import easytests.personal.dto.IssueStandardQuestionTypeOptionDto;
import easytests.personal.dto.IssueStandardTopicPriorityDto;
import easytests.services.IssueStandardQuestionTypeOptionsService;
import easytests.services.IssueStandardTopicPrioritiesService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
        tpModelIds.addAll(tpModels.stream().map(IdentityInterface::getId).collect(Collectors.toList()));

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
        final Set<Integer> tpDtoIdSet = new HashSet<>();

        for (int index = 0; index < tpDtoList.size(); index++) {
            final Integer tpId = tpDtoList.get(index).getId();
            if (tpId == null) {
                continue;
            }
            if (tpDtoIdSet.contains(tpId)) {
                reject(errors, tpField(index, "id"), "Topic Priority id must be unique");
            }
            tpDtoIdSet.add(tpId);
        }
    }

    private void validateTopicsAreUnique(Errors errors, IssueStandardDto issueStandardDto) {
        final List<IssueStandardTopicPriorityDto> tpDtoList = issueStandardDto.getTopicPriorities();
        final Set<Integer> tpDtoTopicIdSet = new HashSet<>();

        for (int index = 0; index < tpDtoList.size(); index++) {
            final Integer tpTopicId = tpDtoList.get(index).getTopicId();
            if (tpTopicId == null) {
                continue;
            }
            if (tpDtoTopicIdSet.contains(tpTopicId)) {
                reject(errors, tpField(index, "topicId"), "Topic must be unique");
            }
            tpDtoTopicIdSet.add(tpTopicId);
        }
    }

    private void validateQuestionTypeOptionDtoIdBelongsToIssueStandard(Errors errors,
                                                                      IssueStandardDto issueStandardDto) {
        final List<IssueStandardQuestionTypeOptionModelInterface> qtoModels
                = this.questionTypeOptionsService
                .findByIssueStandard(new IssueStandardModelEmpty(issueStandardDto.getId()));

        final List<Integer> qtoModelIds = new ArrayList<>(qtoModels.size());
        qtoModelIds.addAll(qtoModels.stream().map(IdentityInterface::getId).collect(Collectors.toList()));

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
        final Set<Integer> qtoDtoIdSet = new HashSet<>();

        for (int index = 0; index < qtoDtoList.size(); index++) {
            final Integer qtoId = qtoDtoList.get(index).getId();
            if (qtoId == null) {
                continue;
            }
            if (qtoDtoIdSet.contains(qtoId)) {
                reject(errors, qtoField(index, "id"), "Question Type Option id must be unique");
            }
            qtoDtoIdSet.add(qtoId);
        }
    }

    private void validateQuestionTypesAreUnique(Errors errors, IssueStandardDto issueStandardDto) {
        final List<IssueStandardQuestionTypeOptionDto> qtoDtoList = issueStandardDto.getQuestionTypeOptions();
        final Set<Integer> qtoDtoQuestionTypeIdSet = new HashSet<>();

        for (int index = 0; index < qtoDtoList.size(); index++) {
            final Integer qtoQuestionTypeId = qtoDtoList.get(index).getQuestionTypeId();
            if (qtoQuestionTypeId == null) {
                continue;
            }
            if (qtoDtoQuestionTypeIdSet.contains(qtoQuestionTypeId)) {
                reject(errors, qtoField(index, "questionTypeId"), "Question Type must be unique");
            }
            qtoDtoQuestionTypeIdSet.add(qtoQuestionTypeId);
        }
    }

    private void validateMaxIsGreaterThanMin(Errors errors, IssueStandardDto issueStandardDto) {
        final List<IssueStandardQuestionTypeOptionDto> qtoDtoList = issueStandardDto.getQuestionTypeOptions();

        for (int index = 0; index < qtoDtoList.size(); index++) {
            final Integer minQuestions = qtoDtoList.get(index).getMinQuestions();
            final Integer maxQuestions = qtoDtoList.get(index).getMaxQuestions();
            if (minQuestions != null && maxQuestions != null && minQuestions > maxQuestions) {
                reject(errors, qtoField(index, "maxQuestions"), "Max Questions must be greater than Min Questions");
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
