package easytests.auth.services;

import easytests.core.models.QuestionModelInterface;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.TopicModelInterface;
import easytests.core.models.UserModelInterface;
import easytests.core.services.UsersServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


/**
 * @author malinink
 */
@Service
public class AccessControlLayerService {
    @Autowired
    protected UsersServiceInterface usersService;

    private Boolean userModelFetched = false;

    private UserModelInterface userModel;

    private Boolean hasAccess(SubjectModelInterface subjectModel, UserModelInterface userModel) {
        return this.isUser(userModel) && subjectModel.getUser().getId().equals(this.getUserModel().getId());
    }

    public Boolean hasAccess(SubjectModelInterface subjectModel) {
        return this.hasAccess(subjectModel, this.getUserModel());
    }

    private Boolean hasAccess(TopicModelInterface topicModel, UserModelInterface userModel) {
        return this.hasAccess(topicModel.getSubject(), userModel);
    }

    public Boolean hasAccess(TopicModelInterface topicModel) {
        return this.hasAccess(topicModel, this.getUserModel());
    }

    private Boolean hasAccess(QuestionModelInterface questionModel, UserModelInterface userModel) {
        return this.hasAccess(questionModel.getTopic(), userModel);
    }

    public Boolean hasAccess(QuestionModelInterface questionModel) {
        return this.hasAccess(questionModel, this.getUserModel());
    }

    private Boolean isUser(UserModelInterface userModel) {
        return userModel != null;
    }

    private Boolean isUser() {
        return this.isUser(this.getUserModel());
    }

    /**
     * TODO decide where that method should be?
     * @return UserModelInterface
     */
    public UserModelInterface getUserModel() {
        if (!this.userModelFetched) {
            final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                this.userModel = usersService.findByEmail(authentication.getName());
            }
            this.userModelFetched = true;
        }
        return this.userModel;
    }
}
