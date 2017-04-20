package easytests.personal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


/**
 * @author malinink
 */
@Controller
public class AbstractCrudController extends AbstractPersonalController {
    protected static void setCreateBehaviour(Model model) {
        model.addAttribute(getSubmitButtonName(), "Create");
    }

    protected static void setUpdateBehaviour(Model model) {
        model.addAttribute(getSubmitButtonName(), "Save");
    }

    private static String getSubmitButtonName() {
        return "submitButtonName";
    }
}
