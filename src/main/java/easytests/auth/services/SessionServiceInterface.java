package easytests.auth.services;

import easytests.core.models.UserModelInterface;

/**
 * @author malinink
 */
public interface SessionServiceInterface {
    
    Boolean isUser();

    UserModelInterface getUserModel();
}
