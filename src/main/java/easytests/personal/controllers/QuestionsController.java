package easytests.personal.controllers;

import easytests.common.controllers.AbstractCrudController;
import easytests.common.exceptions.ForbiddenException;
import easytests.common.exceptions.NotFoundException;
import easytests.models.QuestionModel;
import easytests.models.QuestionModelInterface;
import easytests.models.QuestionTypeModelInterface;
import easytests.models.TopicModelInterface;
import easytests.options.QuestionsOptionsInterface;
import easytests.options.TopicsOptionsInterface;
import easytests.options.builder.QuestionsOptionsBuilder;
import easytests.options.builder.TopicsOptionsBuilder;
import easytests.personal.dto.QuestionModelDto;
import easytests.personal.validators.QuestionModelDtoValidator;
import easytests.services.QuestionTypesService;
import easytests.services.QuestionsService;
import easytests.services.TopicsService;
import java.util.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * @author firkhraag
 */
@Controller
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/personal/topics/{topicId}/questions")
public class QuestionsController extends AbstractCrudController {
    
    @Autowired
    private QuestionModelDtoValidator questionModelDtoValidator;

    @Autowired
    protected TopicsService topicsService;

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private QuestionTypesService questionTypesService;

    @Autowired
    private QuestionsOptionsBuilder questionsOptionsBuilder;
    
    @Autowired
    private TopicsOptionsBuilder topicsOptionsBuilder;

    @GetMapping("")
    public String list(Model model, @PathVariable("topicId") Integer topicId) {
        final List<QuestionModelInterface> questions = this.questionsService
            .findByTopic(this.getCurrentTopicModel(topicId));
        model.addAttribute("questions", questions);
        model.addAttribute("topicId", topicId);
        return "questions/list";
    }

    @GetMapping("{questionId}")
    public String read(
            Model model,
            @PathVariable("questionId") Integer questionId,
            @PathVariable("topicId") Integer topicId) {
        final TopicModelInterface topicModel = getCurrentTopicModel(topicId);
        final QuestionModelInterface questionModel = getQuestionModel(questionId, topicId, false);
        injectQuestionTypeModels(model);
        model.addAttribute("question", questionModel);
        model.addAttribute("topicId", topicId);
        return "questions/view";
    }

    @GetMapping("create/")
    public String create(Model model, @PathVariable("topicId") Integer topicId) {
        final TopicModelInterface topicModel = getCurrentTopicModel(topicId);
        injectQuestionTypeModels(model);
        final QuestionModelDto questionModelDto = new QuestionModelDto();
        setCreateBehaviour(model);
        model.addAttribute("question", questionModelDto);
        model.addAttribute("topicId", topicId);
        return "questions/form";
    }

    @PostMapping("create/")
    public String insert(
            Model model,
            @Valid @NotNull QuestionModelDto questionModelDto,
            BindingResult bindingResult,
            @PathVariable("topicId") Integer topicId) {
        final TopicModelInterface topicModel = getCurrentTopicModel(topicId);
        this.questionModelDtoValidator.validate(questionModelDto, bindingResult);
        if (bindingResult.hasErrors()) {
            injectQuestionTypeModels(model);
            setCreateBehaviour(model);
            model.addAttribute("question", questionModelDto);
            model.addAttribute("topicId", topicId);
            model.addAttribute("errors", bindingResult);
            return "questions/form";
        }
        final QuestionModelInterface questionModel = new QuestionModel();
        questionModelDto.mapInto(questionModel, questionTypesService);
        questionModel.setTopic(topicModel);
        this.questionsService.save(questionModel);
        return "redirect:/personal/topics/" + topicId + "/questions/";
    }

    @GetMapping("update/{questionId}/")
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

    @PostMapping("update/{questionId}/")
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

    @GetMapping("delete/{questionId}")
    public String deleteConfirmation(
            Model model,
            @PathVariable("questionId") Integer questionId,
            @PathVariable("topicId") Integer topicId) {
        final TopicModelInterface topicModel = getCurrentTopicModel(topicId);
        final QuestionModelInterface questionModel = getQuestionModel(questionId, topicId, false);
        model.addAttribute("topicId", topicId);
        return "questions/delete";
    }

    @PostMapping("delete/{questionId}/")
    public String delete(
            Model model,
            @PathVariable("questionId") Integer questionId,
            @PathVariable("topicId") Integer topicId) {
        final TopicModelInterface topicModel = getCurrentTopicModel(topicId);
        final QuestionModelInterface questionModel = getQuestionModel(questionId, topicId, true);
        questionsService.delete(questionModel, this.questionsOptionsBuilder.forDelete());
        return "redirect:/personal/topics/" + topicId + "/questions/";
    }

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

    private QuestionModelInterface getQuestionModel(Integer id, Integer topicId, Boolean forDelete) {
        final QuestionsOptionsInterface questionsOptions = this.questionsOptionsBuilder.forAuth();
        final QuestionModelInterface questionModel = this.questionsService.find(id, questionsOptions);
        checkModel(questionModel, topicId);
        if (forDelete) {
            return this.questionsService.find(id, this.questionsOptionsBuilder.forDelete());
        }
        return questionModel;
    }

    private void injectQuestionTypeModels(Model model) {
        final List<QuestionTypeModelInterface> questionTypes = this.questionTypesService.findAll();
        model.addAttribute("questionTypes", questionTypes);
    }
}
