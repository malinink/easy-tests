package easytests.entities;

import java.util.List;

/**
 * @author loriens
 */

public class Topic implements TopicInterface {

    private Integer id;

    private String name;

    private Integer questions_id;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<Question> getQuestions() {
        // Для метода нужна реализация класса Question
    }

    public void addQuestion(Question question) {
        // Для метода нужна реализация класса Question
    }

}
