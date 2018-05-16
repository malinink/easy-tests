package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.BadRequestException;
import easytests.api.v1.exceptions.ForbiddenException;
import easytests.api.v1.exceptions.UnidentifiedModelException;
import easytests.api.v1.mappers.IssuesMapper;
import easytests.api.v1.models.Issue;
import easytests.common.exceptions.NotFoundException;
import easytests.core.models.IssueModelInterface;
import easytests.core.options.IssuesOptionsInterface;
import easytests.core.options.builder.IssuesOptionsBuilderInterface;
import easytests.core.services.IssuesServiceInterface;
import easytests.core.services.SubjectsServiceInterface;
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
    protected IssuesOptionsBuilderInterface issuesOptionsBuilder;

    @Autowired
    protected SubjectsServiceInterface subjectsService;

    @Autowired
    @Qualifier("IssuesMapperV1")
    private IssuesMapper issuesMapper;

    /**
     * list
     */
    /**
     * create
     */

    @PutMapping("")
    public void update(@RequestBody Issue issue) throws BadRequestException, NotFoundException, ForbiddenException {

        if (issue.getId() == null) {
            throw new UnidentifiedModelException();
        }

        final IssueModelInterface issueModel = this.getIssueModel(issue.getId());

        if (!this.acl.hasAccess(issueModel.getSubject())) {
            throw new ForbiddenException();
        }

        this.issuesMapper.map(issue, issueModel);

        this.issuesService.save(issueModel);
    }

    /**
     * show(issueId)
     */
    /**
     * delete(issueId)
     */

    private IssueModelInterface getIssueModel(Integer id, IssuesOptionsInterface issueOptions)
            throws NotFoundException {
        final IssueModelInterface issueModel = this.issuesService.find(id, issueOptions);
        if (issueModel == null) {
            throw new NotFoundException();
        }
        return issueModel;
    }

    private IssueModelInterface getIssueModel(Integer id) throws NotFoundException {
        return this.getIssueModel(id, this.issuesOptionsBuilder.forAuth());
    }
}
