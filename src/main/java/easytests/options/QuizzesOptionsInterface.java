package easytests.options;

import easytests.models.QuizModelInterface;

import java.util.List;

/**
 * @author fortyways
 */
public interface QuizzesOptionsInterface {
    QuizModelInterface withRelations(QuizModelInterface subjectModel);

    List<QuizModelInterface> withRelations(List<QuizModelInterface> subjectsModels);
}
