package easytests.entities;

import java.util.List;

/**
 * @author loriens
 */

public class Topic {
    private Integer id;

    private String name;

    private List<Question> questions;

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
        return questions;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }
}
