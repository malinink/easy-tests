package easytests.entities;

import java.util.List;

/**
 * @author vkpankov
 */
public class Quiz implements QuizInterface {

    private Integer id;

    private List<PointInterface> points;

    private Integer issueId;

    private String inviteCode;

    public Integer getId() {
        return id;
    }

    @Override
    public QuizInterface setId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public List<PointInterface> getPoints() {
        return points;
    }

    @Override
    public QuizInterface setPoints(List<PointInterface> points) {
        this.points = points;
        return this;
    }

    @Override
    public Integer getIssueId() {
        return issueId;
    }

    @Override
    public QuizInterface setIssueId(Integer issueId) {
        this.issueId = issueId;
        return this;
    }

    @Override
    public String getInviteCode() {
        return inviteCode;
    }

    @Override
    public QuizInterface setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
        return this;
    }

}
