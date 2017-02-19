package easytests.entities;

import easytests.entities.topic.CommonFieldsInterface;

import java.util.List;

/**
 * @author loriens
 */

public class Topic implements CommonFieldsInterface {
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
