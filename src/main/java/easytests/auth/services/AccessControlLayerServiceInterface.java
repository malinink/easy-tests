package easytests.auth.services;

import easytests.core.models.QuestionModelInterface;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.TopicModelInterface;
import easytests.core.models.UserModelInterface;


/**
 * @author malinink
 */
public interface AccessControlLayerServiceInterface {

    Boolean hasAccess(UserModelInterface source);

    Boolean hasAccess(SubjectModelInterface source);

    Boolean hasAccess(TopicModelInterface source);

    Boolean hasAccess(QuestionModelInterface source);
}
