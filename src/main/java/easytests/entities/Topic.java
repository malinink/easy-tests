package easytests.entities;


import easytests.entities.topic.CommonFieldsInterface;

import java.util.List;

/**
 * @author loriens
 */

public class Topic implements CommonFieldsInterface {
    String name;

    List<Question> questions;

    String getName() {
        return name;
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
