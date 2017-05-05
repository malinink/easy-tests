package easytests.personal.controllers;

import easytests.common.controllers.AbstractPersonalController;
import easytests.common.exceptions.ForbiddenException;
import easytests.common.exceptions.NotFoundException;
import easytests.models.*;
import easytests.options.*;
import easytests.personal.dto.AnswerDto;
import easytests.personal.validators.AnswerDtoValidator;
import easytests.services.AnswersService;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


/**
 * @author rezenbekk
 */
@SuppressWarnings({"checkstyle:MultipleStringLiterals", "checkstyle:ClassDataAbstractionCoupling"})
@Controller
@RequestMapping("/personal/answers/")
public class AnswersController extends AbstractPersonalController {

    @Autowired
    private AnswersService answersService;

    private AnswerDtoValidator answerDtoValidator = new AnswerDtoValidator();

    private void checkModel(AnswerModelInterface answerModel) {
        if (answerModel == null) {
            throw new NotFoundException();
        }
        if (!answerModel.getQuestion().getTopic().getSubject().getUser()
                .getId().equals(this.getCurrentUserModel().getId())) {
            throw new ForbiddenException();
        }
    }

    private AnswerModelInterface getAnswerModel(Integer id) {
        final AnswerModelInterface answerModel = this.answersService.find(id);
        checkModel(answerModel);
        return answerModel;
    }

    private AnswerModelInterface getAnswerModel(Integer id, AnswersOptionsInterface answersOptions) {
        final AnswerModelInterface answerModel = this.answersService.find(id, answersOptions);
        checkModel(answerModel);
        return answerModel;
    }

    //TODO: get ALL answers related to current User
    @RequestMapping("list")
    public String list(Model model) {
        final List<AnswerModelInterface> answers = this.answersService.findByQuestion();
        model.addAttribute("answers", answers);
        return "answers/list";
    }

    @GetMapping("create")
    public String create(Model model) {
        final AnswerDto answer = new AnswerDto();
        answer.setTxt("");
        answer.setRight(Boolean.FALSE);
        answer.setQuestionId(0);
        model.addAttribute("methodType", "create");
        model.addAttribute("answer", answer);
        return "answers/form";
    }

    @PostMapping("create")
    public String create(@Valid @NotNull AnswerDto answer,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("methodType", "create");
            model.addAttribute("answer", answer);
            model.addAttribute("errors", bindingResult);
            return "answers/form";
        }
        final AnswerModelInterface answerModel = new AnswerModel();
        answer.mapInto(answerModel);
        answerModel.setQuestion(answersService.find(answer.getQuestionId()).getQuestion());
        answersService.save(answerModel);
        return "redirect:/personal/answers/list";
    }

    @GetMapping("{id}")
    public String read(@PathVariable("id") Integer id,
                       Model model) {

        final AnswersOptionsInterface answersOptions =
                new AnswersOptions();
        final AnswerModelInterface answerModel = getAnswerModel(id, answersOptions);

        final AnswerDto answer = new AnswerDto();
        answer.map(answerModel);
        model.addAttribute("answer", answer);
        return "answers/read";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable("id") Integer id,
                         Model model) {
        model.addAttribute("methodType", "update");
        final AnswerModelInterface answerModel = getAnswerModel(id);
        final AnswerDto answer = new AnswerDto();
        answer.map(answerModel);
        model.addAttribute("answer", answer);
        return "answers/form";
    }

    @PostMapping("update/{id}")
    public String update(@PathVariable("id") Integer answerId,
                         @Valid @NotNull AnswerDto answer,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("methodType", "update");
            model.addAttribute("answer", answer);
            model.addAttribute("errors", bindingResult);
            return "answers/form";
        }
        final AnswerModelInterface answerModel = new AnswerModel();
        answer.mapInto(answerModel);
        answerModel.setId(answerId);
        answerModel.setQuestion(answersService.find(answer.getQuestionId()).getQuestion());
        answersService.save(answerModel);
        return "redirect:/personal/answers/list";
    }

    @GetMapping("delete/{id}")
    public String deleteConfirmation(@PathVariable("id") Integer id,
                                     Model model) {
        final AnswerDto answerDto = new AnswerDto();
        final AnswerModelInterface answerModel = getAnswerModel(id);
        answerDto.map(answerModel);
        model.addAttribute("answer", answerDto);
        return "answers/delete";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer answerId,
                         AnswerDto answerDto,
                         BindingResult bindingResult,
                         Model model) {
        answerDtoValidator.validate(answerDto, bindingResult);
        final AnswersOptionsInterface answersOptions =
                new AnswersOptions();
        final AnswerModelInterface answerModel = getAnswerModel(answerId, answersOptions);
        answersService.delete(answerModel, answersOptions);
        return "redirect:/personal/answers/list";
    }
}
