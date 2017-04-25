package easytests.personal.controllers;

import easytests.models.IssueStandardModel;
import easytests.models.IssueStandardModelInterface;
import easytests.models.SubjectModel;
import easytests.models.SubjectModelInterface;
import easytests.models.empty.SubjectModelEmpty;
import easytests.options.IssueStandardsOptions;
import easytests.options.SubjectsOptions;
import easytests.options.SubjectsOptionsInterface;
import easytests.personal.dto.SubjectDto;
import easytests.personal.exceptions.ForbiddenException;
import easytests.services.IssueStandardsService;
import easytests.services.SubjectsService;
import java.util.List;
import javax.validation.constraints.NotNull;
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

    private static final String SUBJECTS_LIST_URL = "redirect:/personal/subjects/list";

    private static final String SUBJECT_FIELD_NAME = "subject";

    private static final String SUBJECTS_EDIT_TEMPLATE = "subjects/create";

    private static final String METHOD_TITLE_FIELD_NAME = "methodTitle";

    @Autowired
    private SubjectsService subjectsService;

    @Autowired
    private IssueStandardsService issueStandardsService;

    private SubjectDto mapToDto(@NotNull SubjectModelInterface subjectModel) {
        final SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subjectModel.getId());
        subjectDto.setName(subjectModel.getName());
        subjectDto.setDescription(subjectModel.getDescription());
        subjectDto.setIssueStandardId(subjectModel.getIssueStandard().getId());
        return subjectDto;
    }

    private SubjectModelInterface mapToModel(@NotNull SubjectDto subjectDto) {
        final SubjectModelInterface subjectModel = new SubjectModel();
        subjectModel.setId(subjectDto.getId());
        subjectModel.setName(subjectDto.getName());
        subjectModel.setDescription(subjectDto.getDescription());
        subjectModel.setUser(this.getCurrentUserModel());
        return subjectModel;
    }

    private void checkPermissions(SubjectDto subject) {
        final SubjectModelInterface foundSubject = subjectsService.find(subject.getId());
        if (foundSubject.getUser().getId() != this.getCurrentUserModel().getId()) {
            throw new ForbiddenException();
        }
    }

    @RequestMapping("list")
    public String list(Model model) {
        final List<SubjectModelInterface> subjects = this.subjectsService.findAll();
        model.addAttribute("subjects", subjects);
        return "subjects/root";
    }

    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute(METHOD_TITLE_FIELD_NAME, "Create subject");
        final SubjectModelInterface subjectModel = new SubjectModel();
        subjectModel.setName("");
        subjectModel.setDescription("");
        model.addAttribute(SUBJECT_FIELD_NAME, subjectModel);
        return SUBJECTS_EDIT_TEMPLATE;
    }

    @PostMapping("create")
    @ResponseStatus(value = HttpStatus.MOVED_PERMANENTLY)
    public String create(SubjectDto subject,
                         Model model) {
        final SubjectModelInterface subjectModel = mapToModel(subject);
        subjectsService.save(subjectModel);
        final IssueStandardModelInterface issueStandardModel = new IssueStandardModel();
        issueStandardModel.setSubject(new SubjectModelEmpty(subjectModel.getId()));
        issueStandardsService.save(issueStandardModel);
        return SUBJECTS_LIST_URL;
    }

    @GetMapping("read/{id}")
    public String read(@PathVariable("id") Integer id,
                         Model model) {
        final SubjectModelInterface subjectModel = this.subjectsService.find(id,
                new SubjectsOptions().withIssueStandard(new IssueStandardsOptions()));
        final SubjectDto subject = mapToDto(subjectModel);
        model.addAttribute(SUBJECT_FIELD_NAME, subject);
        return "subjects/read";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable("id") Integer id,
                         Model model) {
        model.addAttribute(METHOD_TITLE_FIELD_NAME, "Edit subject");
        final SubjectDto subject = mapToDto(this.subjectsService.find(id,
                new SubjectsOptions().withIssueStandard(new IssueStandardsOptions())));
        model.addAttribute(SUBJECT_FIELD_NAME, subject);
        return SUBJECTS_EDIT_TEMPLATE;
    }

    @PostMapping("update/{id}")
    @ResponseStatus(value = HttpStatus.MOVED_PERMANENTLY)
    public String update(@PathVariable("id") Integer id,
                         @NotNull SubjectDto subject,
                         Model model) {
        subject.setId(id);
        checkPermissions(subject);
        subjectsService.save(mapToModel(subject));
        return SUBJECTS_LIST_URL;
    }

    @GetMapping("delete/{id}")
    public String deleteConfirmation(@PathVariable("id") Integer id,
                         Model model) {
        model.addAttribute("subjectId", id);
        return "subjects/delete";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer id,
                         Model model) {
        final SubjectsOptionsInterface subjectOptions =
                new SubjectsOptions().withIssueStandard(new IssueStandardsOptions());
        final SubjectModelInterface subjectModel = this.subjectsService.find(id, subjectOptions);
        checkPermissions(mapToDto(subjectModel));
        subjectsService.delete(subjectModel, subjectOptions);
        return SUBJECTS_LIST_URL;
    }
}
