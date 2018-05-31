package easytests.auth.services;

import easytests.core.models.*;


/**
 * @author malinink
 */
public interface AccessControlLayerServiceInterface {

    Boolean hasAccess(UserModelInterface source);

    Boolean hasAccess(SubjectModelInterface source);

    Boolean hasAccess(TopicModelInterface source);

    Boolean hasAccess(QuestionModelInterface source);

    Boolean hasAccess(IssueModelInterface source);

    Boolean hasAccess(QuizModelInterface source);
}
