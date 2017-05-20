package easytests.personal.controllers;

import easytests.common.controllers.AbstractCrudController;
import easytests.models.*;
import easytests.options.AnswersOptions;
import easytests.options.AnswersOptionsInterface;
import easytests.options.QuestionsOptionsInterface;
import easytests.options.builder.AnswersOptionsBuilder;
import easytests.options.builder.QuestionsOptionsBuilder;
import easytests.personal.dto.AnswerDto;
import easytests.personal.dto.AnswerListDto;
import easytests.personal.validators.AnswerDtoValidator;
import easytests.services.AnswersService;
import easytests.services.QuestionTypesService;
import easytests.services.QuestionsService;
import java.util.*;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author rezenbekk
 */
@Controller
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/personal/topics/{topicId}/questions/{questionId}/update_answers/")
public class AnswersController extends AbstractCrudController {

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private QuestionsOptionsBuilder questionsOptionsBuilder;

    @Autowired
    private QuestionTypesService questionTypesService;

    @Autowired
    private AnswersOptionsBuilder answersOptionsBuilder;

    @Autowired
    private AnswersService answersService;

    @Autowired
    private AnswerDtoValidator answerDtoValidator;

    @GetMapping("")
    public String update(
            Model model,
            @PathVariable("questionId") Integer questionId,
            @PathVariable("topicId") Integer topicId) {
        final QuestionModelInterface questionModel = getQuestionModel(questionId, false);
        injectQuestionTypeModels(model);
        final AnswerListDto answerDtoList = new AnswerListDto();
        answerDtoList.map(questionModel);
        setUpdateBehaviour(model);
        model.addAttribute("questionId", questionId);
        model.addAttribute("question", questionModel);
        model.addAttribute("answerDtoList", answerDtoList);
        model.addAttribute("topicId", topicId);
        return "answers/form";
    }

    @PostMapping("")
    public String save(
            Model model,
            @PathVariable("questionId") Integer questionId,
            @Valid AnswerListDto answerDtoList,
            BindingResult bindingResult,
            @PathVariable("topicId") Integer topicId) {
        final QuestionModelInterface questionModel = this.getQuestionModel(questionId, false);
        final List<AnswerModelInterface> oldAnswerList = questionModel.getAnswers();
        final List<AnswerDto> newAnswerList = new ArrayList<>();
        if (answerDtoList.getAnswersList() != null) {
            newAnswerList.addAll(answerDtoList.getAnswersList());
        }
        for (AnswerDto answerDto
                : newAnswerList) {
            this.answerDtoValidator.validate(answerDto, bindingResult);
        }
        this.answerDtoValidator.validateWithQuestionType(answerDtoList.getAnswersList(),
                questionModel.getQuestionType().getId(), bindingResult);
        if (bindingResult.hasErrors()) {
            injectQuestionTypeModels(model);
            setUpdateBehaviour(model);
            final AnswerListDto oldDtoList = new AnswerListDto();
            oldDtoList.map(questionModel);
            model.addAttribute("answerDtoList", oldDtoList);
            model.addAttribute("topicId", topicId);
            model.addAttribute("question", questionModel);

            model.addAttribute("questionId", questionId);
            model.addAttribute("errors", bindingResult);

            System.out.println("\nHAS ERRORS");
            System.out.println(bindingResult + "\n\n");

            return "answers/form";
        }

        for (Integer i = 0; i < newAnswerList.size(); i++) {
            final AnswerDto answerDto = newAnswerList.get(i);
            final AnswerModelInterface answer = new AnswerModel();
            System.out.println(answerDto);
            answerDto.mapInto(answer);
            this.answersService.save(answer);
        }

        this.deleteDeadAnswers(newAnswerList, oldAnswerList);

        return "redirect:/personal/topics/" + topicId + "/questions/" + questionId + "/update_answers/";
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    private void deleteDeadAnswers(List<AnswerDto> newAnswers,
                                   List<AnswerModelInterface> oldAnswers) {

        final List<Integer> newAnswersIds = new ArrayList<>(newAnswers.size());

        newAnswersIds.addAll(newAnswers.stream().map(AnswerDto::getId)
                .collect(Collectors.toList()));
        for (AnswerModelInterface oldAnswer : oldAnswers) {
            if (!newAnswersIds.contains(oldAnswer.getId())) {
                System.out.println("DELETION: " + oldAnswer);
                this.answersService.delete(oldAnswer);
            }
        }
    }

    private QuestionModelInterface getQuestionModel(Integer id, Boolean forDelete) {
        final QuestionsOptionsInterface questionsOptions = this.questionsOptionsBuilder
                .forAuth()
                .withAnswers(new AnswersOptions());
        final QuestionModelInterface questionModel = this.questionsService.find(id, questionsOptions);
        //checkModel(questionModel, topicId);
        if (forDelete) {
            return this.questionsService.find(id, this.questionsOptionsBuilder.forDelete());
        }
        return questionModel;
    }

    private void injectQuestionTypeModels(Model model) {
        final List<QuestionTypeModelInterface> questionTypes = this.questionTypesService.findAll();
        model.addAttribute("questionTypes", questionTypes);
    }

    private List<AnswerModelInterface> getAnswerModelList(QuestionModelInterface questionModel) {
        final AnswersOptionsInterface answersOptions = this.answersOptionsBuilder.forAuth();
        final List<AnswerModelInterface> answersList = answersService.findByQuestion(questionModel, answersOptions);

        return answersList;
    }
}
