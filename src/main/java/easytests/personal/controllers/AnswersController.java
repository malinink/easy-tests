package easytests.personal.controllers;

import easytests.common.controllers.AbstractCrudController;
import easytests.common.exceptions.NotFoundException;
import easytests.models.*;
import easytests.options.AnswersOptionsInterface;
import easytests.options.QuestionsOptionsInterface;
import easytests.options.builder.AnswersOptionsBuilder;
import easytests.options.builder.QuestionsOptionsBuilder;
import easytests.personal.dto.AnswerDto;
import easytests.personal.validators.AnswerDtoValidator;
import easytests.services.AnswersService;
import easytests.services.QuestionTypesService;
import easytests.services.QuestionsService;
import java.util.*;
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
        final List<AnswerModelInterface> answersList = getAnswerModelList(questionModel);
        final List<AnswerDto> answerDtoList = this.getAnswerDtoList(answersList);
        Collections.sort(answerDtoList, Comparator.comparingInt(AnswerDto::getSerialNumber));
        setUpdateBehaviour(model);
        model.addAttribute("questionId", questionId);
        model.addAttribute("question", questionModel);
        model.addAttribute("topicId", topicId);
        model.addAttribute("answerDtoList", answerDtoList);
        return "answers/form";
    }

    @PostMapping("")
    public String save(
            Model model,
            @PathVariable("questionId") Integer questionId,
            @Valid /*@ModelAttribute("answerDtoList")*/ ArrayList<AnswerDto> answerDtoList,
            BindingResult bindingResult,
            @PathVariable("topicId") Integer topicId) {
        final QuestionModelInterface questionModel = this.getQuestionModel(questionId, false);
        final List<AnswerModelInterface> oldAnswerList = questionModel.getAnswers();
        //TODO: схоронить все полученные ответы, если ответа не получили, он удаляется
        final List<AnswerModelInterface> answerList = this.getAnswerModelList(answerDtoList);
        System.out.println("DTO LIST SIZE: " + answerDtoList.size());
        for (AnswerDto answerDto
                : answerDtoList) {
            System.out.println(answerDto.getTxt());
            this.answerDtoValidator.validate(answerDto, bindingResult);
        }
        this.answerDtoValidator.validateWithQuestionType(answerDtoList,
                questionModel.getQuestionType().getId(), bindingResult);
        if (bindingResult.hasErrors()) {
            setUpdateBehaviour(model);
            model.addAttribute("answerDtoList", answerDtoList);
            model.addAttribute("topicId", topicId);
            model.addAttribute("questionId", questionId);
            model.addAttribute("errors", bindingResult);

            return "answers/form";
        }

        for (Integer i = 0; i < answerDtoList.size(); i++) {
            final AnswerDto answerDto = answerDtoList.get(i);
            final AnswerModelInterface answer = answerList.get(i);
            answerDto.mapInto(answer);
            this.answersService.save(answer);
        }
        return "redirect:/personal/topics/" + topicId + "/questions/" + questionId + "/update_answers/";
    }
    /*
    @GetMapping("update")
    public String update(
            Model model,
            @PathVariable Integer questionId,
            @PathVariable("topicId") Integer topicId) {
        final TopicModelInterface topicModel = getCurrentTopicModel(topicId);
        final QuestionModelInterface questionModel = this.getQuestionModel(questionId, topicId, false);
        injectQuestionTypeModels(model);
        final QuestionModelDto questionModelDto = new QuestionModelDto();
        questionModelDto.map(questionModel);
        setUpdateBehaviour(model);
        model.addAttribute("question", questionModelDto);
        model.addAttribute("topicId", topicId);
        return "questions/form";
    }
    */
    /*
    @PostMapping("update")
    public String save(
            Model model,
            @PathVariable Integer questionId,
            @Valid @NotNull QuestionModelDto questionModelDto,
            BindingResult bindingResult,
            @PathVariable("topicId") Integer topicId) {
        final TopicModelInterface topicModel = getCurrentTopicModel(topicId);
        final QuestionModelInterface questionModel = this.getQuestionModel(questionId, topicId, false);
        this.questionModelDtoValidator.validate(questionModelDto, bindingResult);
        if (bindingResult.hasErrors()) {
            injectQuestionTypeModels(model);
            setUpdateBehaviour(model);
            model.addAttribute("question", questionModelDto);
            model.addAttribute("topicId", topicId);
            model.addAttribute("errors", bindingResult);
            return "questions/form";
        }
        questionModelDto.mapInto(questionModel, questionTypesService);
        this.questionsService.save(questionModel);
        return "redirect:/personal/topics/" + topicId + "/questions/";
    }
    */
    /*
    private TopicModelInterface getCurrentTopicModel(Integer topicId) {
        final TopicsOptionsInterface topicsOptions = this.topicsOptionsBuilder.forAuth();
        final TopicModelInterface topicModel = topicsService.find(topicId, topicsOptions);
        checkModel(topicModel);
        return topicModel;
    }

    private void checkModel(QuestionModelInterface questionModel, Integer topicId) {
        if (questionModel == null) {
            throw new NotFoundException();
        }
        if (!questionModel.getTopic().getId().equals(this.getCurrentTopicModel(topicId).getId())) {
            throw new ForbiddenException();
        }
    }

    private void checkModel(TopicModelInterface topicModel) {
        if (topicModel == null) {
            throw new NotFoundException();
        }
        if (!topicModel.getSubject().getUser().getId().equals(this.getCurrentUserModel().getId())) {
            throw new ForbiddenException();
        }
    }
    */
    private void checkModel(AnswerModelInterface answerModel, Integer questionId) {
        if (answerModel == null) {
            throw new NotFoundException();
        }
    }

    private QuestionModelInterface getQuestionModel(Integer id, Boolean forDelete) {
        final QuestionsOptionsInterface questionsOptions = this.questionsOptionsBuilder.forAuth();
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

    private AnswerModelInterface getAnswerModel(Integer id, Integer questionId) {
        final AnswersOptionsInterface answersOptions = this.answersOptionsBuilder.forAuth();
        final AnswerModelInterface answerModel = this.answersService.find(id, answersOptions);

        return answerModel;
    }

    private List<AnswerModelInterface> getAnswerModelList(Integer questionId) {
        final QuestionModelInterface questionModel = questionsService.find(questionId);
        final AnswersOptionsInterface answersOptions = this.answersOptionsBuilder.forAuth();
        final List<AnswerModelInterface> answersList = answersService.findByQuestion(questionModel, answersOptions);

        return answersList;
    }

    private List<AnswerModelInterface> getAnswerModelList(QuestionModelInterface questionModel) {
        final AnswersOptionsInterface answersOptions = this.answersOptionsBuilder.forAuth();
        final List<AnswerModelInterface> answersList = answersService.findByQuestion(questionModel, answersOptions);

        return answersList;
    }

    private List<AnswerModelInterface> getAnswerModelList(List<AnswerDto> answerDtoList) {
        final List<AnswerModelInterface> answerList = new ArrayList<AnswerModelInterface>();
        for (AnswerDto answerDto
                : answerDtoList) {
            final AnswerModel answer = new AnswerModel();
            answerDto.mapInto(answer);
            answerList.add(answer);
        }
        return answerList;
    }
    
    private List<AnswerDto> getAnswerDtoList(List<AnswerModelInterface> answersList) {
        final List<AnswerDto> answerDtoList = new ArrayList<AnswerDto>();
        for (AnswerModelInterface answer
                : answersList) {
            final AnswerDto answerDto = new AnswerDto();
            answerDto.map(answer);
            answerDtoList.add(answerDto);
        }
        return answerDtoList;
    }
}
