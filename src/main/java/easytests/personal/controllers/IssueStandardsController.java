package easytests.personal.controllers;

import easytests.common.controllers.AbstractPersonalController;
import easytests.common.exceptions.ForbiddenException;
import easytests.common.exceptions.NotFoundException;
import easytests.models.*;
import easytests.options.*;
import easytests.personal.dto.IssueStandardDto;
import easytests.personal.validators.IssueStandardDtoValidator;
import easytests.services.*;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private SubjectsService subjectsService;

    @Autowired
    private QuestionTypesService questionTypesService;

    @Autowired
    private IssueStandardDtoValidator issueStandardValidator;

    private IssueStandardModelInterface getIssueStandardModel(Integer issueStandardId) {
        final IssueStandardModelInterface issueStandardModel = this.issueStandardsService.find(
                issueStandardId,
                new IssueStandardsOptions()
                        .withTopicPriorities(new IssueStandardTopicPrioritiesOptions())
                        .withQuestionTypeOptions(new IssueStandardQuestionTypeOptionsOptions()));
        if (issueStandardModel == null) {
            throw new NotFoundException();
        }
        final SubjectModelInterface subjectModel = this.getSubjectModel(issueStandardModel.getSubject().getId());
        issueStandardModel.setSubject(subjectModel);
        return issueStandardModel;
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

        final IssueStandardModelInterface issueStandard = this.getIssueStandardModel(issueStandardId);
        model.addAttribute("issueStandard", issueStandard);
        return "issue_standards/view";
    }

    @GetMapping("update/{issueStandardId}/")
    public String updateView(Model model,
                             @PathVariable Integer issueStandardId) {

        final IssueStandardModelInterface issueStandard = this.getIssueStandardModel(issueStandardId);
        final IssueStandardDto issueStandardDto = new IssueStandardDto();
        issueStandardDto.map(issueStandard);

        model.addAttribute("issueStandard", issueStandardDto);
        model.addAttribute("subject", issueStandard.getSubject());
        return "issue_standards/edit";
    }

    @PostMapping("update/{issueStandardId}/")
    public String updateSubmit(Model model,
                               @PathVariable Integer issueStandardId,
                               @RequestBody @Valid IssueStandardDto issueStandardDto,
                               BindingResult bindingResult) {

        final IssueStandardModelInterface currentIssueStandardModel = this.getIssueStandardModel(issueStandardId);
        issueStandardDto.setId(issueStandardId);
        issueStandardValidator.validate(issueStandardDto, bindingResult);

        System.out.println(issueStandardDto);
        System.out.println(bindingResult);

        if (bindingResult.hasErrors()) {
            final SubjectModelInterface subjectModel = this.getSubjectModel(issueStandardDto.getSubjectId());
            model.addAttribute("issueStandard", issueStandardDto);
            model.addAttribute("subject", subjectModel);
            model.addAttribute("errors", bindingResult);
            return "issue_standards/edit";
        }

        // TODO: save
        return "redirect:/personal/issue_standard/{issueStandardId}/";
    }
}