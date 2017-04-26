package easytests.common.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


/**
 * @author malinink
 */
@Controller
public abstract class AbstractCrudController extends AbstractPersonalController {

    protected static void setCreateBehaviour(Model model) {
        model.addAttribute("create", true);
    }

    protected static void setUpdateBehaviour(Model model) {
        model.addAttribute("update", true);
    }
}
