package easytests.controllers;

import easytests.dto.IssueStandardDto;
import easytests.models.IssueStandardModel;
import easytests.models.IssueStandardModelInterface;
import easytests.models.empty.ModelsListEmpty;
import easytests.models.empty.SubjectModelEmpty;
import easytests.services.IssueStandardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;


/**
 * @author SingularityA
 */
@Controller
@RequestMapping("issue-standard/")
public class IssueStandardsController {

    @Autowired
    private IssueStandardsService issueStandardsService;

    @Autowired
    @Qualifier("issueStandardDtoValidator")
    private Validator validator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @GetMapping("{id}")
    @ResponseBody
    public IssueStandardDto getIssueStandard(@PathVariable("id") Integer id) {
        return this.mapToDto(this.issueStandardsService.find(id));
    }

    @PostMapping("save")
    public void saveIssueStandard(@RequestBody @Validated IssueStandardDto issueStandardDto, Errors errors) {
        if (errors.hasErrors()) {
            // handle errors
            System.out.println(errors);
            return;
        }
        this.issueStandardsService.save(this.mapToModel(issueStandardDto));
    }

    private IssueStandardDto mapToDto(IssueStandardModelInterface issueStandardModel) {
        final IssueStandardDto issueStandardDto = new IssueStandardDto();
        issueStandardDto.setId(issueStandardModel.getId());
        issueStandardDto.setQuestionsNumber(issueStandardModel.getQuestionsNumber());
        issueStandardDto.setTimeLimit(issueStandardModel.getTimeLimit());
        issueStandardDto.setSubjectId(issueStandardModel.getSubject().getId());
        return issueStandardDto;
    }

    private IssueStandardModelInterface mapToModel(IssueStandardDto issueStandardDto) throws ParseException {
        final IssueStandardModelInterface issueStandardModel = new IssueStandardModel();
        issueStandardModel.setId(issueStandardDto.getId());
        issueStandardModel.setQuestionsNumber(issueStandardDto.getQuestionsNumber());
        issueStandardModel.setTimeLimit(issueStandardDto.getTimeLimit());
        issueStandardModel.setQuestionTypeOptions(new ModelsListEmpty());
        issueStandardModel.setTopicPriorities(new ModelsListEmpty());
        issueStandardModel.setSubject(new SubjectModelEmpty(issueStandardDto.getSubjectId()));
        return issueStandardModel;
    }
}
