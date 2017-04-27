package easytests.personal.controllers;

import easytests.models.*;
import easytests.options.*;
import easytests.personal.dto.IssueStandardDto;
import easytests.services.IssueStandardsService;
import easytests.services.QuestionTypesService;
import easytests.services.TopicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author SingularityA
 */
@Controller
@RequestMapping("personal/issue_standard/")
@SuppressWarnings("checkstyle:MultipleStringLiterals")
public class IssueStandardsController extends AbstractPersonalController {

    @Autowired
    private IssueStandardsService issueStandardsService;

    @Autowired
    private TopicsService topicsService;

    @Autowired
    private QuestionTypesService questionTypesService;

    @GetMapping("{id}")
    public String read(Model model,
                       @PathVariable Integer id) {
        final IssueStandardModelInterface issueStandard = this.issueStandardsService.find(
                id,
                new IssueStandardsOptions()
                        .withTopicPriorities(new IssueStandardTopicPrioritiesOptions()
                                .withTopic(new TopicsOptions()))
                        .withQuestionTypeOptions(new IssueStandardQuestionTypeOptionsOptions()
                                .withQuestionType(new QuestionTypesOptions()))
                        .withSubject(new SubjectsOptions()));

        model.addAttribute("issueStandard", issueStandard);
        return "issue_standards/view";
    }

    @GetMapping("update/{id}")
    public String updateView(Model model,
                             @PathVariable Integer id) {
        final IssueStandardModelInterface issueStandard = this.issueStandardsService.find(
                id,
                new IssueStandardsOptions()
                        .withTopicPriorities(new IssueStandardTopicPrioritiesOptions())
                        .withQuestionTypeOptions(new IssueStandardQuestionTypeOptionsOptions())
                        .withSubject(new SubjectsOptions()));

        final IssueStandardDto issueStandardDto = new IssueStandardDto();
        issueStandardDto.map(issueStandard);

        model.addAttribute("subjectName", issueStandard.getSubject().getName());
        model.addAttribute("issueStandard", issueStandardDto);
        model.addAttribute("topics", this.topicsService.findBySubject(issueStandard.getSubject()));
        model.addAttribute("questionTypes", this.questionTypesService.findAll());
        return "issue_standards/edit";
    }

    @PostMapping("update/{id}")
    public String updateSubmit(Model model,
                               @PathVariable Integer id,
                               @RequestBody @Validated IssueStandardDto issueStandardDto,
                               BindingResult bindingResult) {
        // TODO: add errors handlering and save
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
        }
        System.out.println(issueStandardDto);
        return "redirect:/personal/issue_standard/{id}";
    }
}
