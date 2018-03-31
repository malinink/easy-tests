package easytests.personal.controllers;

import easytests.common.controllers.AbstractCrudController;
import easytests.common.exceptions.ForbiddenException;
import easytests.common.exceptions.NotFoundException;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.TopicModel;
import easytests.core.models.TopicModelInterface;
import easytests.core.options.SubjectsOptionsInterface;
import easytests.core.options.TopicsOptionsInterface;
import easytests.core.options.builder.SubjectsOptionsBuilder;
import easytests.core.options.builder.TopicsOptionsBuilder;
import easytests.core.services.QuestionsService;
import easytests.core.services.SubjectsService;
import easytests.core.services.TopicsService;
import easytests.personal.dto.TopicDto;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author DoZor-80
 */
@SuppressWarnings({"checkstyle:MultipleStringLiterals", "checkstyle:ClassDataAbstractionCoupling"})
@Controller
@RequestMapping("/personal/subjects/{subjectId}/topics")
public class TopicsController extends AbstractCrudController {

    @Autowired
    private TopicsService topicsService;

    @Autowired
    private SubjectsService subjectsService;

    @Autowired
    private QuestionsService questionsService;

    @Autowired
    private TopicsOptionsBuilder topicsOptionsBuilder;

    @Autowired
    private SubjectsOptionsBuilder subjectsOptionsBuilder;

    private void checkModel(TopicModelInterface topicModel, Integer subjectId) {
        if (topicModel == null) {
            throw new NotFoundException();
        }
        if (!topicModel.getSubject().getId().equals(this.getCurrentSubjectModel(subjectId, false).getId())) {
            throw new ForbiddenException();
        }
    }

    private void checkModel(SubjectModelInterface subjectModel) {
        if (subjectModel == null) {
            throw new NotFoundException();
        }
        if (!subjectModel.getUser().getId().equals(this.getCurrentUserModel().getId())) {
            throw new ForbiddenException();
        }
    }

    private TopicModelInterface getTopicModel(Integer id, Integer subjectId) {
        final TopicsOptionsInterface topicsOptionsBuilder = this.topicsOptionsBuilder.forAuth();
        final TopicModelInterface topicModel = this.topicsService.find(id, topicsOptionsBuilder);
        checkModel(topicModel, subjectId);
        return topicModel;
    }

    private TopicModelInterface getTopicModel(Integer id, Integer subjectId, TopicsOptionsInterface topicsOptions) {
        final TopicModelInterface topicModel = this.topicsService.find(id, topicsOptions);
        checkModel(topicModel, subjectId);
        return topicModel;
    }

    private SubjectModelInterface getCurrentSubjectModel(Integer subjectId, boolean withTopics) {
        final SubjectsOptionsInterface subjectsOptions = this.subjectsOptionsBuilder.forAuth();
        final SubjectModelInterface subjectModel = subjectsService.find(subjectId, subjectsOptions);
        if (withTopics) {
            final List<TopicModelInterface> topics = this.topicsService.findBySubject(subjectModel);
            subjectModel.setTopics(topics);
        }
        checkModel(subjectModel);
        return subjectModel;
    }

    @GetMapping("")
    public String list(Model model, @PathVariable("subjectId") Integer subjectId) {
        final SubjectModelInterface subjectModel = getCurrentSubjectModel(subjectId, true);
        final List<TopicModelInterface> topics = subjectModel.getTopics();
        model.addAttribute("topics", topics);
        model.addAttribute("subjectId", subjectId);
        return "topics/list";
    }

    @GetMapping("{topicId}")
    public String read(Model model,
                       @PathVariable("subjectId") Integer subjectId,
                       @PathVariable("topicId") Integer topicId
    ) {
        final SubjectModelInterface subjectModel = getCurrentSubjectModel(subjectId, false);
        final TopicModelInterface topicModel = getTopicModel(topicId, subjectId);
        model.addAttribute("topic", topicModel);
        model.addAttribute("subjectId", subjectId);
        return "topics/view";
    }

    @GetMapping("create/")
    public String create(Model model, @PathVariable("subjectId") Integer subjectId) {
        final SubjectModelInterface subjectModel = getCurrentSubjectModel(subjectId, false);
        final TopicDto topic = new TopicDto();
        setCreateBehaviour(model);
        model.addAttribute("topic", topic);
        model.addAttribute("subjectId", subjectId);
        return "topics/form";
    }

    @PostMapping("create/")
    public String create(Model model,
                         @Valid @NotNull TopicDto topic,
                         BindingResult bindingResult,
                         @PathVariable("subjectId") Integer subjectId) {
        final SubjectModelInterface subjectModel = getCurrentSubjectModel(subjectId, false);
        if (bindingResult.hasErrors()) {
            setCreateBehaviour(model);
            model.addAttribute("topic", topic);
            model.addAttribute("subjectId", subjectId);
            model.addAttribute("errors", bindingResult);
            return "topics/form";
        }
        final TopicModelInterface topicModel = new TopicModel();
        topic.mapInto(topicModel);
        topicModel.setSubject(subjectModel);
        this.topicsService.save(topicModel);
        return "redirect:/personal/subjects/" + subjectId + "/topics/";
    }

    @GetMapping("update/{topicId}/")
    public String update(Model model,
                         @PathVariable Integer topicId,
                         @PathVariable("subjectId") Integer subjectId) {
        final SubjectModelInterface subjectModel = getCurrentSubjectModel(subjectId, false);
        final TopicModelInterface topicModel = this.getTopicModel(topicId, subjectId);
        final TopicDto topic = new TopicDto();
        topic.map(topicModel);
        setUpdateBehaviour(model);
        model.addAttribute("topic", topic);
        model.addAttribute("subjectId", subjectId);
        return "topics/form";
    }

    @PostMapping("update/{topicId}/")
    public String update(Model model,
                         @PathVariable Integer topicId,
                         @Valid @NotNull TopicDto topic,
                         BindingResult bindingResult,
                         @PathVariable("subjectId") Integer subjectId) {
        final SubjectModelInterface subjectModel = getCurrentSubjectModel(subjectId, false);
        final TopicModelInterface topicModel = this.getTopicModel(topicId, subjectId);
        if (bindingResult.hasErrors()) {
            setUpdateBehaviour(model);
            model.addAttribute("topic", topic);
            model.addAttribute("subjectId", subjectId);
            model.addAttribute("errors", bindingResult);
            return "topics/form";
        }
        topic.mapInto(topicModel);
        this.topicsService.save(topicModel);
        return "redirect:/personal/subjects/" + subjectId + "/topics/";
    }

    @GetMapping("delete/{topicId}")
    public String deleteConfirmation(Model model,
                                     @PathVariable("topicId") Integer topicId,
                                     @PathVariable("subjectId") Integer subjectId) {
        final SubjectModelInterface subjectModel = getCurrentSubjectModel(subjectId, false);
        final TopicModelInterface topicModel = this.getTopicModel(topicId, subjectId);
        model.addAttribute("subjectId", subjectId);
        return "topics/delete";
    }

    @PostMapping("delete/{topicId}")
    public String delete(Model model,
                         @PathVariable("topicId") Integer topicId,
                         @PathVariable("subjectId") Integer subjectId) {
        final SubjectModelInterface subjectModel = getCurrentSubjectModel(subjectId, false);
        final TopicModelInterface topicModel = getTopicModel(topicId, subjectId, topicsOptionsBuilder.forDelete());
        if (questionsService.findByTopic(topicModel).isEmpty()) {
            topicsService.delete(topicModel);
        } else {
            topicsService.delete(topicModel, this.topicsOptionsBuilder.forDelete());
        }
        return "redirect:/personal/subjects/" + subjectId + "/topics/";
    }
}
