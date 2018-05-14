package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.BadRequestException;
import easytests.api.v1.mappers.IssuesMapper;
import easytests.api.v1.models.Issue;
import easytests.core.models.IssueModelInterface;
import easytests.core.models.empty.SubjectModelEmpty;
import easytests.core.options.builder.IssuesOptionsBuilder;
import easytests.core.services.IssuesService;
import easytests.core.services.SubjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yarik2308
 */
@RestController("IssueControllerV1")
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/v1/issues")
public class IssuesController {

    @Autowired
    protected IssuesService issuesService;

    @Autowired
    protected IssuesOptionsBuilder issuesOptions;

    @Autowired
    @Qualifier("IssuesMapperV1")
    private IssuesMapper issuesMapper;

    protected SubjectsService subjectsService;

    @GetMapping
    public List<Issue> list(@RequestParam(name = "subjectId", required = true) Integer subjectId) throws BadRequestException {

        if (subjectsService.find(subjectId) == null) {
            throw new BadRequestException();
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
