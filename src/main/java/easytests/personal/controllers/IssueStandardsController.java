package easytests.personal.controllers;

import easytests.common.controllers.AbstractPersonalController;
import easytests.common.exceptions.ForbiddenException;
import easytests.common.exceptions.NotFoundException;
import easytests.models.*;
import easytests.options.*;
import easytests.personal.dto.IssueStandardDto;
import easytests.personal.validators.IssueStandardDtoValidator;
import easytests.services.IssueStandardsService;
import easytests.services.QuestionTypesService;
import easytests.services.TopicsService;
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
@SuppressWarnings("checkstyle:ClassDataAbstractionCoupling")
public class IssueStandardsController extends AbstractPersonalController {

    private static final String ISSUE_STANDARD_ATTR = "issueStandard";

    private static final String ERRORS_ATTR = "errors";

    private static final String VIEW_TEMPLATE = "issue_standards/view";

    private static final String EDIT_TEMPLATE = "issue_standards/edit";

    @Autowired
    private IssueStandardsService issueStandardsService;

    @Autowired
    private TopicsService topicsService;

    @Autowired
    private QuestionTypesService questionTypesService;

    @Autowired
    private IssueStandardDtoValidator issueStandardValidator;

    @ModelAttribute("subject")
    private SubjectModelInterface getCurrentSubject(@PathVariable Integer routeId) {
        final IssueStandardModelInterface issueStandardModel = this.issueStandardsService
                .find(routeId, new IssueStandardsOptions()
                        .withSubject(new SubjectsOptions()));
        if (issueStandardModel == null) {
            throw new NotFoundException();
        }
        return issueStandardModel.getSubject();
    }

    @ModelAttribute("topics")
    private List<TopicModelInterface> getTopics(@PathVariable Integer routeId) {
        final SubjectModelInterface subjectModel = this.getCurrentSubject(routeId);
        return this.topicsService.findBySubject(subjectModel);
    }

    @ModelAttribute("questionTypes")
    private List<QuestionTypeModelInterface> getQuestionTypes() {
        return this.questionTypesService.findAll();
    }

    @GetMapping("{routeId}")
    public String read(Model model,
                       @PathVariable Integer routeId) {
        final IssueStandardModelInterface issueStandard = this.issueStandardsService.find(
                routeId,
                new IssueStandardsOptions()
                        .withTopicPriorities(new IssueStandardTopicPrioritiesOptions()
                                .withTopic(new TopicsOptions()))
                        .withQuestionTypeOptions(new IssueStandardQuestionTypeOptionsOptions()
                                .withQuestionType(new QuestionTypesOptions()))
                        .withSubject(new SubjectsOptions()));

        model.addAttribute(ISSUE_STANDARD_ATTR, issueStandard);
        return VIEW_TEMPLATE;
    }

    @GetMapping("update/{routeId}")
    public String updateView(Model model,
                             @PathVariable Integer routeId) {
        final IssueStandardModelInterface issueStandard = this.issueStandardsService.find(
                routeId,
                new IssueStandardsOptions()
                        .withTopicPriorities(new IssueStandardTopicPrioritiesOptions())
                        .withQuestionTypeOptions(new IssueStandardQuestionTypeOptionsOptions())
                        .withSubject(new SubjectsOptions()));

        final IssueStandardDto issueStandardDto = new IssueStandardDto();
        issueStandardDto.setRouteId(routeId);
        issueStandardDto.map(issueStandard);

        model.addAttribute(ISSUE_STANDARD_ATTR, issueStandardDto);
        return EDIT_TEMPLATE;
    }

    @PostMapping("update/{routeId}")
    public String updateSubmit(Model model,
                               @PathVariable Integer routeId,
                               @RequestBody @Valid IssueStandardDto issueStandardDto,
                               BindingResult bindingResult) {

        checkPermission(issueStandardDto);
        issueStandardDto.setRouteId(routeId);
        issueStandardValidator.validate(issueStandardDto, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute(ISSUE_STANDARD_ATTR, issueStandardDto);
            model.addAttribute(ERRORS_ATTR, bindingResult);
            return EDIT_TEMPLATE;
        }
        final IssueStandardModelInterface issueStandardModel = new IssueStandardModel();
        issueStandardDto.mapInto(issueStandardModel);
        // TODO: сохрание списков
        // this.issueStandardsService.save(issueStandardModel);
        return "redirect:/personal/issue_standard/{routeId}";
    }

    private void checkPermission(IssueStandardDto issueStandardDto) {
        final IssueStandardModelInterface foundIssueStandardModel = this.issueStandardsService
                .find(issueStandardDto.getId(), new IssueStandardsOptions()
                .withSubject(new SubjectsOptions()
                .withUser(new UsersOptions())));
        if (!foundIssueStandardModel.getSubject().getUser().getId().equals(this.getCurrentUserModel().getId())) {
            throw new ForbiddenException();
        }
    }
}
