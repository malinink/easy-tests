package easytests.api.v1.controllers;

import easytests.core.mappers.IssuesMapper;
import easytests.core.options.builder.IssuesOptionsBuilderInterface;
import easytests.core.services.IssuesServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author Yarik2308
 */
@RestController("IssueControllerV1")
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/v1/issues")
public class IssuesController {

    @Autowired
    protected IssuesServiceInterface issuesService;

    @Autowired
    protected IssuesOptionsBuilderInterface issuesOptions;

    @Autowired
    @Qualifier("IssuesMapperV1")
    private IssuesMapper issuesMapper;

    /**
     * listIssues
     */
    /**
     * createIssue
     */
    /**
     * updateIssue
     */
    /**
     * showIssueById
     */
    /**
     * deleteIssueById
     */

}
