package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.BadRequestException;
import easytests.api.v1.exceptions.ForbiddenException;
import easytests.api.v1.mappers.IssuesMapper;
import easytests.api.v1.models.Issue;
import easytests.common.exceptions.NotFoundException;
import easytests.core.models.IssueModelInterface;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.empty.SubjectModelEmpty;
import easytests.core.options.builder.IssuesOptionsBuilder;
import easytests.core.services.IssuesServiceInterface;
import easytests.core.services.SubjectsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yarik2308
 */
@RestController("IssuesControllerV1")
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/v1/issues")
public class IssuesController extends AbstractController{

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

        if(subjectModel == null) {
            throw new NotFoundException();
        }

        if(!this.acl.hasAccess(subjectModel)) {
            throw new ForbiddenException();
        }

        final List<IssueModelInterface> issuesModels = this.issuesService
                .findBySubject(new SubjectModelEmpty(subjectId));


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
    /**
     * delete(issueId)
     */

}
