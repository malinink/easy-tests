package easytests;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TemplateController {

    @RequestMapping("/template")
    public String templates() {
        return "template";
    }

}
