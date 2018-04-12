package easytests.core.services;

import easytests.core.entities.QuizEntity;
import easytests.core.mappers.QuizzesMapper;
import easytests.core.models.IssueModelInterface;
import easytests.core.models.QuizModel;
import easytests.core.models.QuizModelInterface;
import easytests.core.options.QuizzesOptionsInterface;
import easytests.core.services.exceptions.DeleteUnidentifiedModelException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vkpankov
 */
@Service
public class QuizzesService implements QuizzesServiceInterface {

    @Autowired
    private QuizzesMapper quizzesMapper;

    @Autowired
    private IssuesService issuesService;

    @Autowired
    private PointsService pointsService;

    @Autowired
    private TesteesService testeesService;

    @Override
    public List<QuizModelInterface> findAll() {
        return this.map(this.quizzesMapper.findAll());
    }

    @Override
    public List<QuizModelInterface> findAll(QuizzesOptionsInterface quizzesOptions) {
        return this.withServices(quizzesOptions).withRelations(this.findAll());
    }

    public QuizModelInterface find(Integer id) {
        final QuizEntity quizEntity = this.quizzesMapper.find(id);
        if (quizEntity == null) {
            return null;
        }
        return this.map(quizEntity);
    }

    @Override
    public QuizModelInterface find(Integer id, QuizzesOptionsInterface quizzesOptions) {
        return this.withServices(quizzesOptions).withRelations(this.find(id));

    }

    @Override
    public List<QuizModelInterface> findByIssue(IssueModelInterface issueModel) {
        return this.map(this.quizzesMapper.findByIssueId(issueModel.getId()));
    }

    @Override
    public List<QuizModelInterface> findByIssue(
            IssueModelInterface issueModel,
            QuizzesOptionsInterface quizzesOptions
    ) {
        return this.withServices(quizzesOptions)
                .withRelations(this.map(this.quizzesMapper.findByIssueId(issueModel.getId())));
    }

    @Override
    public void save(QuizModelInterface quizModel) {
        final QuizEntity quizEntity = this.map(quizModel);
        if (quizEntity.getId() == null) {
            this.quizzesMapper.insert(quizEntity);
            quizModel.setId(quizEntity.getId());
            return;
        }
        this.quizzesMapper.update(quizEntity);
    }

    @Override
    public void save(QuizModelInterface quizModel, QuizzesOptionsInterface quizzesOptions) {
        this.withServices(quizzesOptions).saveWithRelations(quizModel);
    }

    @Override
    public void save(List<QuizModelInterface> quizzesModels) {
        for (QuizModelInterface quizModel: quizzesModels) {
            this.save(quizModel);
        }
    }

    @Override
    public void save(List<QuizModelInterface> quizzesModels, QuizzesOptionsInterface quizzesOptions) {
        for (QuizModelInterface quizModel: quizzesModels) {
            this.save(quizModel, quizzesOptions);
        }
    }

    @Override
    public void delete(QuizModelInterface quizModel) {
        final QuizEntity quizEntity = this.map(quizModel);
        if (quizEntity.getId() == null) {
            throw new DeleteUnidentifiedModelException();
        }
        this.quizzesMapper.delete(quizEntity);
    }

    @Override
    public void delete(QuizModelInterface quizModel, QuizzesOptionsInterface quizzesOptions) {
        this.withServices(quizzesOptions).deleteWithRelations(quizModel);
    }

    @Override
    public void delete(List<QuizModelInterface> quizzesModels) {
        for (QuizModelInterface quizModel : quizzesModels) {
            this.delete(quizModel);
        }
    }

    @Override
    public void delete(List<QuizModelInterface> quizzesModels, QuizzesOptionsInterface quizzesOptions) {
        for (QuizModelInterface quizModel: quizzesModels) {
            this.delete(quizModel, quizzesOptions);
        }
    }

    private QuizEntity map(QuizModelInterface quizModel) {
        final QuizEntity quizEntity = new QuizEntity();
        quizEntity.map(quizModel);
        return quizEntity;
    }

    private QuizModel map(QuizEntity quizEntity) {
        final QuizModel quizModel = new QuizModel();
        quizModel.map(quizEntity);
        return quizModel;
    }

    private List<QuizModelInterface> map(List<QuizEntity> quizzesList) {
        final List<QuizModelInterface> resultQuizList = new ArrayList<>(quizzesList.size());
        for (QuizEntity quiz: quizzesList) {
            resultQuizList.add(this.map(quiz));
        }
        return resultQuizList;
    }

    private QuizzesOptionsInterface withServices(QuizzesOptionsInterface quizzesOptions) {
        quizzesOptions.setQuizzesService(this);
        quizzesOptions.setIssuesService(this.issuesService);
        quizzesOptions.setPointsService(this.pointsService);
        quizzesOptions.setTesteesService(this.testeesService);
        return quizzesOptions;
    }
}
