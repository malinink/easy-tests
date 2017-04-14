package easytests.personal.controllers;

import easytests.models.SubjectModel;
import easytests.models.SubjectModelInterface;
import easytests.personal.dto.SubjectDto;
import easytests.services.SubjectsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author vkpankov
 */
@Controller
@RequestMapping("/personal/subjects/")
public class SubjectsController extends AbstractPersonalController {

    @Autowired
    private SubjectsService subjectsService;

    private final String subjectsListUrl = "redirect:/personal/subjects/list";

    private final String subjectFieldName = "subject";

    private SubjectDto mapToDto(SubjectModelInterface subjectModel) {
        final SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subjectModel.getId());
        subjectDto.setName(subjectModel.getName());
        subjectDto.setDescription(subjectModel.getDescription());
        subjectDto.setUserId(this.getCurrentUserModel().getId());
        return subjectDto;
    }

    private SubjectModelInterface mapToModel(SubjectDto subjectDto) {
        final SubjectModelInterface subjectModel = new SubjectModel();
        subjectModel.setId(subjectDto.getId());
        subjectModel.setName(subjectDto.getName());
        subjectModel.setDescription(subjectDto.getDescription());
        subjectModel.setUser(this.getCurrentUserModel());
        return subjectModel;
    }

    @RequestMapping("list")
    public String list(Model model) {
        final List<SubjectModelInterface> subjects = this.subjectsService.findAll();
        model.addAttribute("subjects", subjects);
        return "subjects/root";
    }

    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute(subjectFieldName, new SubjectModel());
        return "subjects/create";
    }

    @PostMapping("create")
    @ResponseStatus(value = HttpStatus.MOVED_PERMANENTLY)
    public String create(@RequestParam("name") String name,
                         @RequestParam("description") String description,
                         Model model) {
        final SubjectDto subject = new SubjectDto();
        subject.setName(name);
        subject.setDescription(description);
        subjectsService.save(mapToModel(subject));
        return subjectsListUrl;
    }

    @GetMapping("read/{id}")
    public String read(@PathVariable("id") Integer id,
                         Model model) {
        final SubjectDto subject = mapToDto(this.subjectsService.find(id));
        model.addAttribute(subjectFieldName, subject);
        return "subjects/read";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable("id") Integer id,
                         Model model) {
        final SubjectDto subject = mapToDto(this.subjectsService.find(id));
        model.addAttribute(subjectFieldName, subject);
        return "subjects/update";
    }

    @PostMapping("update/{id}")
    @ResponseStatus(value = HttpStatus.MOVED_PERMANENTLY)
    public String update(@PathVariable("id") Integer id,
                         @RequestParam("name") String name,
                         @RequestParam("description") String description,
                         Model model) {
        final SubjectDto subject = new SubjectDto();
        subject.setId(id);
        subject.setName(name);
        subject.setDescription(description);
        subjectsService.save(mapToModel(subject));
        return subjectsListUrl;
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id,
                         Model model) {
        subjectsService.delete(this.subjectsService.find(id));
        return subjectsListUrl;
    }

}
