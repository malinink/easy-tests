package easytests.options;

import easytests.models.SubjectModelInterface;
import java.util.List;

/**
 * @author malinink
 */
public interface SubjectsOptionsInterface extends OptionsInterface {
    SubjectModelInterface withRelations(SubjectModelInterface subjectModel);

    List<SubjectModelInterface> withRelations(List<SubjectModelInterface> subjectsModels);
}
