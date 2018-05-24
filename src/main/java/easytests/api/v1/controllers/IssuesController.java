package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.ForbiddenException;
import easytests.api.v1.exceptions.NotFoundException;
import easytests.api.v1.mappers.IssuesMapper;
import easytests.api.v1.models.Issue;
import easytests.core.models.IssueModelInterface;
import easytests.core.models.SubjectModelInterface;
import easytests.core.options.builder.IssuesOptionsBuilder;
import easytests.core.services.IssuesServiceInterface;
import easytests.core.services.SubjectsServiceInterface;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yarik2308
 */
@RestController("IssuesControllerV1")
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/v1/issues")
public class IssuesController extends AbstractController {

    @Autowired
    protected IssuesServiceInterface issuesService;

    @Autowired
    protected IssuesOptionsBuilder issuesOptions;

    @Autowired
    protected SubjectsServiceInterface subjectsService;

    @Autowired
    @Qualifier("IssuesMapperV1")
    private IssuesMapper issuesMapper;

    @GetMapping
    public List<Issue> list(@RequestParam(name = "subjectId", required = true) Integer subjectId)
            throws NotFoundException, ForbiddenException {
        final SubjectModelInterface subjectModel = this.subjectsService.find(subjectId);

        if (subjectModel == null) {
            throw new NotFoundException();
        }

        if (!this.acl.hasAccess(subjectModel)) {
            throw new ForbiddenException();
        }

        final List<IssueModelInterface> issuesModels = this.issuesService.findBySubject(subjectModel);

        return issuesModels
                .stream()
                .map(model -> this.issuesMapper.map(model, Issue.class))
                .collect(Collectors.toList());
    }
    /**
     * create
     */
    /**
     * update
     */
    /**
     * read(issueId)
     */
    @GetMapping("/{issueId}")
    public Object show(@PathVariable Integer issueId) throws NotFoundException, ForbiddenException{
        final IssueModelInterface issueModel = this.getIssueModel(issueId);
        return this.issuesMapper.map(issueModel, Issue.class);
    }

    private IssueModelInterface getIssueModel(Integer id) throws NotFoundException{
        final IssueModelInterface issueModel = this.issuesService.find(id);
        if (issueModel == null) {
            throw new NotFoundException();
        }
        return issueModel;
    }


    /**
     * delete(issueId)
     */

}
