package easytests.personal.controllers;

import easytests.common.controllers.AbstractPersonalController;
import easytests.common.exceptions.ForbiddenException;
import easytests.common.exceptions.NotFoundException;
import easytests.models.*;
import easytests.options.*;
import easytests.personal.dto.IssueStandardDto;
import easytests.personal.validators.IssueStandardDtoValidator;
import easytests.services.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author SingularityA
 */
@Controller
@RequestMapping("personal/issue_standard/")
@SuppressWarnings({
        "checkstyle:MultipleStringLiterals",
        "checkstyle:ClassDataAbstractionCoupling",
        "checkstyle:ClassFanOutComplexity"})
public class IssueStandardsController extends AbstractPersonalController {

    @Autowired
    private IssueStandardsService issueStandardsService;

    @Autowired
    private IssueStandardTopicPrioritiesService topicPrioritiesService;

    @Autowired
    private IssueStandardQuestionTypeOptionsService questionTypeOptionsService;

    @Autowired
    private SubjectsService subjectsService;

    @Autowired
    private QuestionTypesService questionTypesService;

    @Autowired
    private IssueStandardDtoValidator issueStandardValidator;

    @ModelAttribute("questionTypes")
    private List<QuestionTypeModelInterface> getQuestionTypes() {
        return this.questionTypesService.findAll();
    }

    @ModelAttribute("viewUrl")
    private String viewUrl(@PathVariable Integer issueStandardId) {
        return "/personal/issue_standard/" + Integer.toString(issueStandardId) + "/";
    }

    @ModelAttribute("editUrl")
    private String editUrl(@PathVariable Integer issueStandardId) {
        return "/personal/issue_standard/update/" + Integer.toString(issueStandardId) + "/";
    }

    @GetMapping("{issueStandardId}/")
    public String read(Model model,
                       @PathVariable Integer issueStandardId) {

        final IssueStandardModelInterface issueStandard = this.getIssueStandardModel(
                issueStandardId,
                getIssueStandardsOptions());
        model.addAttribute("issueStandard", issueStandard);
        return "issue_standards/view";
    }

    @GetMapping("update/{issueStandardId}/")
    public String update(Model model,
                         @PathVariable Integer issueStandardId) {

        final IssueStandardModelInterface issueStandard = this.getIssueStandardModel(
                issueStandardId,
                getIssueStandardsOptions());
        final IssueStandardDto issueStandardDto = new IssueStandardDto();
        issueStandardDto.map(issueStandard);

        model.addAttribute("issueStandard", issueStandardDto);
        model.addAttribute("subject", issueStandard.getSubject());
        return "issue_standards/edit";
    }

    @PostMapping("update/{issueStandardId}/")
    public String save(Model model,
                               @PathVariable Integer issueStandardId,
                               @Valid IssueStandardDto issueStandardDto,
                               BindingResult bindingResult) {

        final IssueStandardModelInterface issueStandardModel = getIssueStandardModel(
                issueStandardId,
                new IssueStandardsOptions());
        issueStandardDto.setId(issueStandardId);
        issueStandardValidator.validate(issueStandardDto, bindingResult);

        // TODO: display errors on view
        System.out.println(issueStandardDto);
        System.out.println(bindingResult);

        if (bindingResult.hasErrors()) {
            final SubjectModelInterface subjectModel = this.getSubjectModel(issueStandardDto.getSubjectId());
            model.addAttribute("issueStandard", issueStandardDto);
            model.addAttribute("subject", subjectModel);
            model.addAttribute("errors", bindingResult);
            return "issue_standards/edit";
        }

        issueStandardDto.mapInto(issueStandardModel);
        issueStandardModel.setId(issueStandardId);
        saveIssueStandardModel(issueStandardModel);

        return "redirect:/personal/issue_standard/{issueStandardId}/";
    }

    private IssueStandardModelInterface getIssueStandardModel(
            Integer issueStandardId,
            IssueStandardsOptionsInterface issueStandardsOptions) {

        final IssueStandardModelInterface issueStandardModel = this.issueStandardsService.find(
                issueStandardId,
                issueStandardsOptions);
        if (issueStandardModel == null) {
            throw new NotFoundException();
        }
        final SubjectModelInterface subjectModel = this.getSubjectModel(issueStandardModel.getSubject().getId());
        issueStandardModel.setSubject(subjectModel);
        return issueStandardModel;
    }

    private IssueStandardsOptionsInterface getIssueStandardsOptions() {
        return new IssueStandardsOptions()
                .withTopicPriorities(new IssueStandardTopicPrioritiesOptions())
                .withQuestionTypeOptions(new IssueStandardQuestionTypeOptionsOptions());
    }

    private SubjectModelInterface getSubjectModel(Integer subjectId) {
        final SubjectModelInterface subjectModel = this.subjectsService.find(
                subjectId,
                new SubjectsOptions()
                        .withTopics(new TopicsOptions()));
        if (subjectModel == null) {
            throw new NotFoundException();
        }
        if (!subjectModel.getUser().getId().equals(this.getCurrentUserModel().getId())) {
            throw new ForbiddenException();
        }
        return subjectModel;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private void saveIssueStandardModel(IssueStandardModelInterface newIssueStandardModel) {
        final IssueStandardModelInterface currentIssueStandardModel
                = this.getIssueStandardModel(newIssueStandardModel.getId(), getIssueStandardsOptions());
        System.out.println(currentIssueStandardModel);

        final List<Integer> newTopicPriorityIds
                = new ArrayList<>(newIssueStandardModel.getTopicPriorities().size());

        newTopicPriorityIds.addAll(newIssueStandardModel.getTopicPriorities().stream()
                .map(IdentityInterface::getId).collect(Collectors.toList()));

        final List<Integer> newQuestionTypeOptionIds
                = new ArrayList<>(newIssueStandardModel.getQuestionTypeOptions().size());

        newQuestionTypeOptionIds.addAll(newIssueStandardModel.getQuestionTypeOptions().stream()
                .map(IdentityInterface::getId).collect(Collectors.toList()));

        for (IssueStandardTopicPriorityModelInterface currentTopicPriority
                : currentIssueStandardModel.getTopicPriorities()) {
            if (!newTopicPriorityIds.contains(currentTopicPriority.getId())) {
                this.topicPrioritiesService.delete(currentTopicPriority);
            }
        }

        for (IssueStandardQuestionTypeOptionModelInterface currentQuestionTypeOption
                : currentIssueStandardModel.getQuestionTypeOptions()) {
            if (!newQuestionTypeOptionIds.contains(currentQuestionTypeOption.getId())) {
                this.questionTypeOptionsService.delete(currentQuestionTypeOption);
            }
        }

        this.issueStandardsService.save(newIssueStandardModel, getIssueStandardsOptions());
    }
}
