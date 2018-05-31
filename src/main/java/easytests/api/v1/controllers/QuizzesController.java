package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.ForbiddenException;
import easytests.api.v1.exceptions.NotFoundException;
import easytests.api.v1.mappers.QuizzesMapper;
import easytests.api.v1.models.Quiz;
import easytests.core.models.IssueModelInterface;
import easytests.core.models.QuizModelInterface;
import easytests.core.options.QuizzesOptionsInterface;
import easytests.core.options.builder.QuizzesOptionsBuilderInterface;
import easytests.core.services.IssuesServiceInterface;
import easytests.core.services.QuizzesServiceInterface;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;


/**
 * @author miron97
 */
@RestController("QuizzesControllerV1")
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/v1/quizzes")
public class QuizzesController extends AbstractController {

    @Autowired
    protected QuizzesServiceInterface quizzesService;

    @Autowired
    private QuizzesOptionsBuilderInterface quizzesOptions;

    @Autowired
    private IssuesServiceInterface issuesService;

    @Autowired
    @Qualifier("QuizzesMapperV1")
    private QuizzesMapper quizzesMapper;

    @GetMapping("")
    public List<Quiz> list(@RequestParam(name = "issueId", required = true) Integer issueId)
            throws NotFoundException, ForbiddenException {
        final IssueModelInterface issueModel = this.issuesService.find(issueId);

        if (issueModel == null) {
            throw new NotFoundException();
        }
        if (!this.acl.hasAccess(issueModel)) {
            throw new ForbiddenException();
        }

        final List<QuizModelInterface> quizModels = this.quizzesService
                .findByIssue(issueModel);

        return quizModels
                .stream()
                .map(model -> this.quizzesMapper.map(model, Quiz.class))
                .collect(Collectors.toList());
    }
    /**
     * show(quizId)
     */

    @GetMapping("/{quizId}")
    public Quiz show(@PathVariable Integer quizId) throws NotFoundException, ForbiddenException {
        final QuizModelInterface quizModel = this.getQuizModel(quizId);
        if (!this.acl.hasAccess(quizModel)) {
            throw new ForbiddenException();
        }

        return this.quizzesMapper.map(quizModel, Quiz.class);
    }

    private QuizModelInterface getQuizModel(Integer id, QuizzesOptionsInterface quizOptions) throws NotFoundException {
        final QuizModelInterface quizModel = this.quizzesService.find(id, quizOptions);
        if (quizModel == null) {
            throw new NotFoundException();
        }
        return quizModel;
    }

    private QuizModelInterface getQuizModel(Integer id) throws NotFoundException {
        return this.getQuizModel(id, this.quizzesOptions.forAuth());
    }

}
