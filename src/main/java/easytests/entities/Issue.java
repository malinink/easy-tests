package easytests.entities;


/**
 * @author fortyways
 */
public class Issue implements IssueInterface {

    private Integer id;

    private String name;

    private Integer authorId;

    //private List<Quiz> quizzes;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public IssueInterface setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public IssueInterface setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public Integer getAuthorId() {
        return authorId;
    }

    @Override
    public IssueInterface setAuthorId(Integer authorId) {
        this.authorId = authorId;
        return this;
    }

    /*@Override
    public List<QuizInterface> getQuizzes() {
        return quizzes;
    }

    @Override
    public IssueInterface setQuizzes(List<QuizInterface> quizzes) {
        this.quizzes = quizzes;
        return this;
    }*/
}
