package easytests.entities;

import java.util.ArrayList;
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

    public void setId(Integer number) {
        this.id = number;
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
        List<Question> questions = new ArrayList<Question>();
        return questions;
    }

    public void addQuestion(Question question) {
        // Для метода нужна реализация класса Question
    }

}
