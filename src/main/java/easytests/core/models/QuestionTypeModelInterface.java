package easytests.core.models;

import easytests.core.entities.QuestionTypeEntity;


/**
 * @author malinink
 */
public interface QuestionTypeModelInterface extends ModelInterface {
    void setId(Integer id);

    String getName();

    void setName(String name);

    void map(QuestionTypeEntity questionTypeEntity);
}
