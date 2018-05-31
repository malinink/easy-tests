package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.ForbiddenException;
import easytests.api.v1.exceptions.NotFoundException;
import easytests.api.v1.mappers.IssuesMapper;
import easytests.api.v1.models.Issue;
import easytests.core.models.IssueModelInterface;
import easytests.core.models.SubjectModelInterface;
import easytests.core.options.IssuesOptionsInterface;
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
    protected IssuesOptionsBuilder issuesOptionsBuilder;

    @Autowired
    protected SubjectsServiceInterface subjectsService;

    @Autowired
    @Qualifier("IssuesMapperV1")
    private IssuesMapper issuesMapper;

    /**
     * list
     */
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
     * show(issueId)
     */
    @GetMapping("/{issueId}")
    public Object show(@PathVariable Integer issueId) throws NotFoundException, ForbiddenException {

        final IssueModelInterface issueModel = this.getIssueModel(issueId);

        if (!this.acl.hasAccess(issueModel)) {
            throw new ForbiddenException();
        }
        return this.issuesMapper.map(issueModel, Issue.class);
    }


    /**
     * delete(issueId)
     */
    @DeleteMapping("/{issueId}")
    public void delete(@PathVariable Integer issueId) throws NotFoundException, ForbiddenException {
        IssueModelInterface issueModel = this.getIssueModel(issueId);

        if (!this.acl.hasAccess(issueModel)) {
            throw new ForbiddenException();
        }
        final IssuesOptionsInterface issuesOptions = this.issuesOptionsBuilder.forDelete();
        issueModel = this.issuesService.find(issueId, issuesOptions);
        this.issuesService.delete(issueModel, issuesOptions);
    }

    private IssueModelInterface getIssueModel(Integer id) throws NotFoundException {
        final IssuesOptionsInterface issuesOptionsInterface = this.issuesOptionsBuilder.forAuth();
        return this.getIssueModel(id, issuesOptionsInterface);
    }

    private IssueModelInterface getIssueModel(Integer id, IssuesOptionsInterface issueOption)
            throws NotFoundException {
        final IssueModelInterface issueModel = this.issuesService.find(id, issueOption);
        if (issueModel == null) {
            throw new NotFoundException();
        }
        return issueModel;
    }
}
