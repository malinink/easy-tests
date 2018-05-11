package easytests.api.v1.controllers;

import easytests.api.v1.mappers.IssuesMapper;
import easytests.core.options.builder.IssuesOptionsBuilder;
import easytests.core.services.IssuesService;
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
    protected IssuesService issuesService;

    @Autowired
    protected IssuesOptionsBuilder issuesOptions;

    @Autowired
    @Qualifier("IssuesMapperV1")
    private IssuesMapper issuesMapper;

    /**
     * list
     */
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
