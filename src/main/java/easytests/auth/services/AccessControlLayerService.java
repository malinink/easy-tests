package easytests.auth.services;

import easytests.core.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author malinink
 */
@Service
public class AccessControlLayerService implements AccessControlLayerServiceInterface {

    @Autowired
    private SessionServiceInterface sessionService;

    @Override
    public Boolean hasAccess(UserModelInterface source) {
        return this.hasAccess(source, this.sessionService.getUserModel());
    }

    private Boolean hasAccess(UserModelInterface source, UserModelInterface userModel) {
        return userModel != null && source.getId().equals(userModel.getId());
    }

    @Override
    public Boolean hasAccess(SubjectModelInterface source) {
        return this.hasAccess(source, this.sessionService.getUserModel());
    }

    private Boolean hasAccess(SubjectModelInterface source, UserModelInterface userModel) {
        return userModel != null && source.getUser().getId().equals(userModel.getId());
    }

    @Override
    public Boolean hasAccess(TopicModelInterface source) {
        return this.hasAccess(source, this.sessionService.getUserModel());
    }

    private Boolean hasAccess(TopicModelInterface source, UserModelInterface userModel) {
        return this.hasAccess(source.getSubject(), userModel);
    }

    @Override
    public Boolean hasAccess(QuestionModelInterface source) {
        return this.hasAccess(source, this.sessionService.getUserModel());
    }

    private Boolean hasAccess(QuestionModelInterface source, UserModelInterface userModel) {
        return this.hasAccess(source.getTopic(), userModel);
    }

    @Override
    public Boolean hasAccess(IssueModelInterface source) {
        return this.hasAccess(source, this.sessionService.getUserModel());
    }

    private Boolean hasAccess(IssueModelInterface source, UserModelInterface userModel) {
        return this.hasAccess(source.getSubject(), userModel);
    }

    @Override
    public Boolean hasAccess(QuizModelInterface source) {
        return this.hasAccess(source, this.sessionService.getUserModel());
    }

    private Boolean hasAccess(QuizModelInterface source, UserModelInterface userModel) {
        return this.hasAccess(source.getIssue(), userModel);
    }

}
