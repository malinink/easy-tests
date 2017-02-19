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

    String getName() {
        return name;
    }

    Integer getId() {
        return id;
    }

    void setName(String name) {
        this.name = name;
    }

    List<Question> getQuestions() {
        return questions;
    }

    void addQuestion(Question question) {
        questions.add(question);
    }
}
