package easytests.personal.controllers;

import easytests.common.controllers.AbstractPersonalController;
import easytests.common.exceptions.ForbiddenException;
import easytests.common.exceptions.NotFoundException;
import easytests.models.IssueStandardModel;
import easytests.models.IssueStandardModelInterface;
import easytests.models.SubjectModel;
import easytests.models.SubjectModelInterface;
import easytests.models.empty.SubjectModelEmpty;
import easytests.options.IssueStandardsOptions;
import easytests.options.SubjectsOptions;
import easytests.options.SubjectsOptionsInterface;
import easytests.personal.dto.SubjectDto;
import easytests.personal.validators.SubjectModelDtoValidator;
import easytests.services.IssueStandardsService;
import easytests.services.SubjectsService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


/**
 * @author vkpankov
 */
@SuppressWarnings("ClassDataAbstractionCoupling")
@RequestMapping("/personal/subjects/")
public class SubjectsController extends AbstractPersonalController {

    private static final String SUBJECTS_LIST_URL = "redirect:/personal/subjects/list";

    private static final String SUBJECT_FIELD_NAME = "subject";

    private static final String ISSUE_STANDARD_ID_FIELD_NAME = "issueStandardId";

    private static final String SUBJECTS_EDIT_TEMPLATE = "subjects/form";

    private static final String METHOD_TYPE_FIELD_NAME = "methodType";

    private static final String METHOD_TYPE_CREATE = "create";

    private static final String METHOD_TYPE_UPDATE = "update";

    private static final String ERRORS_FIELD_NAME = "errors";

    @Autowired
    private SubjectsService subjectsService;

    @Autowired
    private IssueStandardsService issueStandardsService;

    private SubjectModelDtoValidator subjectModelDtoValidator = new SubjectModelDtoValidator();

    private void checkPermissions(SubjectDto subject) {
        final SubjectModelInterface foundSubject = subjectsService.find(subject.getId());
        if (!foundSubject.getUser().getId().equals(this.getCurrentUserModel().getId())) {
            throw new ForbiddenException();
        }
    }

    private SubjectModelInterface getSubjectModel(Integer id) {
        final SubjectModelInterface subjectModel = this.subjectsService.find(id);
        if (subjectModel == null) {
            throw new NotFoundException();
        }
        return subjectModel;
    }

    private SubjectModelInterface getSubjectModel(Integer id, SubjectsOptionsInterface subjectsOptions) {
        final SubjectModelInterface subjectModel = this.subjectsService.find(id, subjectsOptions);
        if (subjectModel == null) {
            throw new NotFoundException();
        }
        return subjectModel;
    }

    @RequestMapping("list")
    public String list(Model model) {
        final List<SubjectModelInterface> subjects = this.subjectsService.findByUser(this.getCurrentUserModel());
        model.addAttribute("subjects", subjects);
        return "subjects/list";
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

        final SubjectsOptionsInterface subjectsOptions =
                new SubjectsOptions().withIssueStandard(new IssueStandardsOptions());
        final SubjectModelInterface subjectModel = getSubjectModel(id, subjectsOptions);

        final SubjectDto subject = new SubjectDto();
        subject.map(subjectModel);
        checkPermissions(subject);
        model.addAttribute(SUBJECT_FIELD_NAME, subject);
        model.addAttribute(ISSUE_STANDARD_ID_FIELD_NAME, subjectModel.getIssueStandard().getId());
        return "subjects/read";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable("id") Integer id,
                         Model model) {
        model.addAttribute(METHOD_TYPE_FIELD_NAME, METHOD_TYPE_UPDATE);
        final SubjectModelInterface subjectModel = getSubjectModel(id);
        final SubjectDto subject = new SubjectDto();
        subject.map(subjectModel);
        checkPermissions(subject);
        model.addAttribute(SUBJECT_FIELD_NAME, subject);
        return SUBJECTS_EDIT_TEMPLATE;
    }

    @PostMapping("update/{id}")
    public String update(@PathVariable("id") Integer routeId,
                         @Valid @NotNull SubjectDto subject,
                         BindingResult bindingResult,
                         Model model) {
        subject.setRouteId(routeId);
        subjectModelDtoValidator.validate(subject, bindingResult);
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
        final SubjectDto subjectDto = new SubjectDto();
        final SubjectModelInterface subjectModel = getSubjectModel(id);
        subjectDto.map(subjectModel);
        checkPermissions(subjectDto);
        model.addAttribute(SUBJECT_FIELD_NAME, subjectDto);
        return "subjects/delete";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer routeId,
                         SubjectDto subjectDto,
                         BindingResult bindingResult,
                         Model model) {
        subjectDto.setRouteId(routeId);
        subjectModelDtoValidator.validate(subjectDto, bindingResult);
        checkPermissions(subjectDto);
        final SubjectsOptionsInterface subjectOptions =
                new SubjectsOptions().withIssueStandard(new IssueStandardsOptions());
        final SubjectModelInterface subjectModel = getSubjectModel(subjectDto.getId(), subjectOptions);
        subjectsService.delete(subjectModel, subjectOptions);
        return SUBJECTS_LIST_URL;
    }
}
