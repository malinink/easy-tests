package easytests.personal.controllers;

import easytests.common.controllers.AbstractPersonalController;
import easytests.common.exceptions.ForbiddenException;
import easytests.common.exceptions.NotFoundException;
import easytests.core.models.IssueStandardModel;
import easytests.core.models.IssueStandardModelInterface;
import easytests.core.models.SubjectModel;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.empty.SubjectModelEmpty;
import easytests.core.options.IssueStandardsOptions;
import easytests.core.options.SubjectsOptions;
import easytests.core.options.SubjectsOptionsInterface;
import easytests.core.options.builder.SubjectsOptionsBuilder;
import easytests.core.services.IssueStandardsService;
import easytests.core.services.SubjectsService;
import easytests.personal.dto.SubjectDto;

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
@SuppressWarnings({"checkstyle:MultipleStringLiterals", "checkstyle:ClassDataAbstractionCoupling"})
@Controller
@RequestMapping("/personal/subjects/")
public class SubjectsController extends AbstractPersonalController {

    @Autowired
    private SubjectsService subjectsService;

    @Autowired
    private IssueStandardsService issueStandardsService;

    @Autowired
    private SubjectsOptionsBuilder subjectsOptionsBuilder;

    private void checkModel(SubjectModelInterface subjectModel) {
        if (subjectModel == null) {
            throw new NotFoundException();
        }
        if (!subjectModel.getUser().getId().equals(this.getCurrentUserModel().getId())) {
            throw new ForbiddenException();
        }
    }

    private SubjectModelInterface getSubjectModel(Integer id) {
        final SubjectModelInterface subjectModel = this.subjectsService.find(id);
        checkModel(subjectModel);
        return subjectModel;
    }

    private SubjectModelInterface getSubjectModel(Integer id, SubjectsOptionsInterface subjectsOptions) {
        final SubjectModelInterface subjectModel = this.subjectsService.find(id, subjectsOptions);
        checkModel(subjectModel);
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
        final SubjectDto subject = new SubjectDto();
        subject.setName("");
        subject.setDescription("");
        model.addAttribute("methodType", "create");
        model.addAttribute("subject", subject);
        return "subjects/form";
    }

    @PostMapping("create")
    public String create(@Valid @NotNull SubjectDto subject,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("methodType", "create");
            model.addAttribute("subject", subject);
            model.addAttribute("errors", bindingResult);
            return "subjects/form";
        }
        final SubjectModelInterface subjectModel = new SubjectModel();
        subject.mapInto(subjectModel);
        subjectModel.setUser(this.getCurrentUserModel());
        subjectsService.save(subjectModel);
        final IssueStandardModelInterface issueStandardModel = new IssueStandardModel();
        issueStandardModel.setSubject(new SubjectModelEmpty(subjectModel.getId()));
        issueStandardsService.save(issueStandardModel);
        return "redirect:/personal/subjects/list";
    }

    @GetMapping("{id}")
    public String read(@PathVariable("id") Integer id,
                       Model model) {

        final SubjectsOptionsInterface subjectsOptions =
                new SubjectsOptions().withIssueStandard(new IssueStandardsOptions());
        final SubjectModelInterface subjectModel = getSubjectModel(id, subjectsOptions);

        final SubjectDto subject = new SubjectDto();
        subject.map(subjectModel);
        model.addAttribute("subject", subject);
        model.addAttribute("issueStandardId", subjectModel.getIssueStandard().getId());
        return "subjects/view";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable("id") Integer id,
                         Model model) {
        model.addAttribute("methodType", "update");
        final SubjectModelInterface subjectModel = getSubjectModel(id);
        final SubjectDto subject = new SubjectDto();
        subject.map(subjectModel);
        model.addAttribute("subject", subject);
        return "subjects/form";
    }

    @PostMapping("update/{id}")
    public String update(@PathVariable("id") Integer subjectId,
                         @Valid @NotNull SubjectDto subject,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("methodType", "update");
            model.addAttribute("subject", subject);
            model.addAttribute("errors", bindingResult);
            return "subjects/form";
        }
        final SubjectModelInterface subjectModel = getSubjectModel(subjectId);
        subject.mapInto(subjectModel);
        subjectsService.save(subjectModel);
        return "redirect:/personal/subjects/list";
    }

    @GetMapping("delete/{id}")
    public String deleteConfirmation(@PathVariable("id") Integer id,
                                     Model model) {
        final SubjectDto subjectDto = new SubjectDto();
        final SubjectModelInterface subjectModel = getSubjectModel(id);
        subjectDto.map(subjectModel);
        model.addAttribute("subject", subjectDto);
        return "subjects/delete";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer subjectId,
                         SubjectDto subjectDto,
                         BindingResult bindingResult,
                         Model model) {
        final SubjectModelInterface subjectModel = getSubjectModel(subjectId, subjectsOptionsBuilder.forDelete());
        subjectsService.delete(subjectModel, subjectsOptionsBuilder.forDelete());
        return "redirect:/personal/subjects/list";
    }
}
