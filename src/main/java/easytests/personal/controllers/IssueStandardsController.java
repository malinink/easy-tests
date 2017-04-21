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
    public String read(Model model, @PathVariable Integer id) {
        final IssueStandardModelInterface issueStandard = this.issueStandardsService.find(
                id,
                new IssueStandardsOptions()
                        .withTopicPriorities(new IssueStandardTopicPrioritiesOptions()
                                .withTopic(new TopicsOptions()))
                        .withQuestionTypeOptions(new IssueStandardQuestionTypeOptionsOptions()
                                .withQuestionType(new QuestionTypesOptions()))
                        .withSubject(new SubjectsOptions()));

        model.addAttribute("issueStandard", issueStandard);
        model.addAttribute("topicPriorities", issueStandard.getTopicPriorities());
        model.addAttribute("questionTypeOptions", issueStandard.getQuestionTypeOptions());
        return "issue_standards/view";
    }

    @GetMapping("update/{id}")
    public String updateView(Model model, @PathVariable Integer id) {
        final IssueStandardModelInterface issueStandard = this.issueStandardsService.find(
                id,
                new IssueStandardsOptions()
                        .withTopicPriorities(new IssueStandardTopicPrioritiesOptions()
                                .withTopic(new TopicsOptions()))
                        .withQuestionTypeOptions(new IssueStandardQuestionTypeOptionsOptions()
                                .withQuestionType(new QuestionTypesOptions()))
                        .withSubject(new SubjectsOptions()));

        model.addAttribute("issueStandard", issueStandard);
        model.addAttribute("topicPriorities", issueStandard.getTopicPriorities());
        model.addAttribute("questionTypeOptions", issueStandard.getQuestionTypeOptions());

        model.addAttribute("topics", this.topicsService.findBySubject(issueStandard.getSubject()));
        model.addAttribute("questionTypes", this.questionTypesService.findAll());
        return "issue_standards/edit";
    }

    @PostMapping("update/{id}")
    public String updateSubmit(Model model, @PathVariable Integer id, @RequestBody IssueStandardDto issueStandardDto) {
        // TODO: submit
        System.out.println(issueStandardDto);
        return "redirect:/personal/issue_standards/list";
    }
}
