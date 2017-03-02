package easytests.entities;

/**
 * @author singularityA
 */
public class Solution implements SolutionInterface {

    private Integer id;

    private AnswerInterface answer;

    private PointInterface point;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public SolutionInterface setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public AnswerInterface getAnswer() {
        return answer;
    }

    @Override
    public SolutionInterface setAnswer(AnswerInterface answer) {
        this.answer = answer;
        return this;
    }

    @Override
    public PointInterface getPoint() {
        return point;
    }

    @Override
    public SolutionInterface setPoint(PointInterface point) {
        this.point = point;
        return this;
    }
}
