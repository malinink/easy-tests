package easytests.entities;
import java.util.List;

/**
 * @author nikitapopov on 24/02/2017.
 */
public class Point implements PointInterface {
    private Integer id;

    private String text;
    private String type;
    private Quiz quiz;
    private List<Solution> solutions;

    @Override
    public Integer getId() {
        return this.id;
    }

    @Override
    public PointInterface setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public PointInterface setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public PointInterface setType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public Quiz getQuiz() {
        return this.quiz;
    }

    @Override
    public PointInterface setQuiz(Quiz quiz) {
        this.quiz = quiz;
        return this;
    }

    @Override
    public List<Solution> getSolutions() {
        return this.solutions;
    }

    @Override
    public PointInterface setSolutions(List<Solution> solutions) {
        this.solutions = solutions;
        return this;
    }

}
