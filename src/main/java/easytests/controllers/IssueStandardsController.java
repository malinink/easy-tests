package easytests.controllers;

import easytests.dto.IssueStandardDTO;
import easytests.models.IssueStandardModel;
import easytests.models.IssueStandardModelInterface;
import easytests.models.empty.ModelsListEmpty;
import easytests.models.empty.SubjectModelEmpty;
import easytests.services.IssueStandardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author SingularityA
 */
@Controller
public class IssueStandardsController {

    @Autowired
    private IssueStandardsService issueStandardsService;

    @RequestMapping(value = "issue_standard/{id}", method = RequestMethod.GET)
    @ResponseBody
    public IssueStandardDTO getIssueStandard(@PathVariable("id") Integer id) {
        return this.mapToDto(this.issueStandardsService.find(id));
    }

    private IssueStandardDTO mapToDto(IssueStandardModelInterface issueStandardModel) {
        System.out.println(issueStandardModel);
        final IssueStandardDTO issueStandardDTO = new IssueStandardDTO();
        issueStandardDTO.setId(issueStandardModel.getId());
        issueStandardDTO.setQuestionsNumber(issueStandardModel.getQuestionsNumber());
        issueStandardDTO.setTimeLimit(issueStandardModel.getTimeLimit());
        issueStandardDTO.setSubjectId(issueStandardModel.getSubject().getId());
        System.out.println(issueStandardDTO);
        System.out.println(this.mapToModel(issueStandardDTO));
        return issueStandardDTO;
    }

    private IssueStandardModelInterface mapToModel(IssueStandardDTO issueStandardDTO) throws ParseException {
        final IssueStandardModelInterface issueStandardModel = new IssueStandardModel();
        issueStandardModel.setId(issueStandardDTO.getId());
        issueStandardModel.setQuestionsNumber(issueStandardDTO.getQuestionsNumber());
        issueStandardModel.setTimeLimit(issueStandardDTO.getTimeLimit());
        issueStandardModel.setQuestionTypeOptions(new ModelsListEmpty());
        issueStandardModel.setTopicPriorities(new ModelsListEmpty());
        issueStandardModel.setSubject(new SubjectModelEmpty(issueStandardDTO.getSubjectId()));
        return issueStandardModel;
    }
}
