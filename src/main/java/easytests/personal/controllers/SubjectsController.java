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
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


/**
 * @author vkpankov
 */
@Controller
@RequestMapping("/personal/subjects/")
public class SubjectsController extends AbstractPersonalController {

    private static final String SUBJECTS_LIST_URL = "redirect:/personal/subjects/list";

    private static final String SUBJECT_FIELD_NAME = "subject";

    private static final String SUBJECTS_EDIT_TEMPLATE = "subjects/form";

    private static final String METHOD_TYPE_FIELD_NAME = "methodType";

    private static final String METHOD_TYPE_CREATE = "create";

    private static final String METHOD_TYPE_UPDATE = "update";

    private static final String ERRORS_FIELD_NAME = "errors";

    @Autowired
    private SubjectsService subjectsService;

    @Autowired
    private IssueStandardsService issueStandardsService;

    private void checkPermissions(SubjectDto subject) {
        final SubjectModelInterface foundSubject = subjectsService.find(subject.getId());
        if (foundSubject.getUser().getId() != this.getCurrentUserModel().getId()) {
            throw new ForbiddenException();
        }
    }

    @RequestMapping("list")
    public String list(Model model) {
        final List<SubjectModelInterface> subjects = this.subjectsService.findByUser(this.getCurrentUserModel());
        model.addAttribute("subjects", subjects);
        return "subjects/root";
    }

    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute(METHOD_TYPE_FIELD_NAME, METHOD_TYPE_CREATE);
        final SubjectDto subject = new SubjectDto();
        subject.setName("");
        subject.setDescription("");
        model.addAttribute(SUBJECT_FIELD_NAME, subject);
        return SUBJECTS_EDIT_TEMPLATE;
    }

    @PostMapping("create")
    public String create(@Valid @NotNull SubjectDto subject,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute(METHOD_TYPE_FIELD_NAME, METHOD_TYPE_CREATE);
            model.addAttribute(SUBJECT_FIELD_NAME, subject);
            model.addAttribute(ERRORS_FIELD_NAME, bindingResult);
            return SUBJECTS_EDIT_TEMPLATE;
        }
        final SubjectModelInterface subjectModel = new SubjectModel();
        subject.mapInto(subjectModel);
        subjectModel.setUser(this.getCurrentUserModel());
        subjectsService.save(subjectModel);

        final IssueStandardModelInterface issueStandardModel = new IssueStandardModel();
        issueStandardModel.setSubject(new SubjectModelEmpty(subjectModel.getId()));
        issueStandardsService.save(issueStandardModel);

        return SUBJECTS_LIST_URL;
    }

    @GetMapping("{id}")
    public String read(@PathVariable("id") Integer id,
                         Model model) {
        final SubjectModelInterface subjectModel = this.subjectsService.find(id,
                new SubjectsOptions().withIssueStandard(new IssueStandardsOptions()));
        final SubjectDto subject = new SubjectDto();
        subject.mapFromModel(subjectModel);
        model.addAttribute(SUBJECT_FIELD_NAME, subject);
        return "subjects/read";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable("id") Integer id,
                         Model model) {
        model.addAttribute(METHOD_TYPE_FIELD_NAME, METHOD_TYPE_UPDATE);
        final SubjectModelInterface subjectModel = this.subjectsService.find(id,
                new SubjectsOptions().withIssueStandard(new IssueStandardsOptions()));
        final SubjectDto subject = new SubjectDto();
        subject.mapFromModel(subjectModel);
        model.addAttribute(SUBJECT_FIELD_NAME, subject);
        return SUBJECTS_EDIT_TEMPLATE;
    }

    @PostMapping("update/{id}")
    public String update(@Valid @NotNull SubjectDto subject,
                         BindingResult bindingResult,
                         Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute(METHOD_TYPE_FIELD_NAME, METHOD_TYPE_UPDATE);
            model.addAttribute(SUBJECT_FIELD_NAME, subject);
            model.addAttribute(ERRORS_FIELD_NAME, bindingResult);
            return SUBJECTS_EDIT_TEMPLATE;
        }
        checkPermissions(subject);
        final SubjectModelInterface subjectModel = new SubjectModel();
        subject.mapInto(subjectModel);
        subjectModel.setUser(this.getCurrentUserModel());
        subjectsService.save(subjectModel);
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

        final SubjectDto subjectDto = new SubjectDto();
        subjectDto.mapFromModel(subjectModel);
        checkPermissions(subjectDto);

        subjectsService.delete(subjectModel, subjectOptions);
        return SUBJECTS_LIST_URL;
    }
}
