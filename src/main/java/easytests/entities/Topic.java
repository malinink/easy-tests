package easytests.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author loriens
 */

public class Topic implements TopicInterface {

    private Integer id;

    private String name;

    private Integer questionsId;

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
    public List<QuestionInterface> getQuestions() {
        return new ArrayList<QuestionInterface>();
    }

    public void setQuestions(QuestionInterface question) {
        // Для метода нужна реализация класса Question
    }

}
