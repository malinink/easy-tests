package easytests.auth.helpers;

import javax.servlet.http.HttpSession;


/**
 * @author malinink
 */
public class SessionLoginStoreHelper {
    public static final String LAST_LOGIN = "LAST_LOGIN";

    private HttpSession session;

    public SessionLoginStoreHelper(HttpSession session) {
        this.session = session;
    }

    public Boolean exists() {
        return this.session != null;
    }

    public void setLogin(String login) {
        if (!this.exists()) {
            return;
        }
        this.session.setAttribute(LAST_LOGIN, login);
    }

    public String getLogin() {
        if (!this.exists()) {
            return null;
        }
        return (String) this.session.getAttribute(LAST_LOGIN);
    }

    public void removeLogin() {
        if (!this.exists()) {
            return;
        }
        this.session.removeAttribute(LAST_LOGIN);
    }
}
