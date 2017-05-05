package easytests.personal.controllers;

import easytests.common.controllers.AbstractPersonalController;
import easytests.common.exceptions.ForbiddenException;
import easytests.common.exceptions.NotFoundException;
import easytests.models.SubjectModel;
import easytests.models.SubjectModelInterface;
import easytests.models.TopicModel;
import easytests.models.TopicModelInterface;
import easytests.options.TopicsOptionsInterface;
import easytests.personal.dto.TopicDto;
import easytests.personal.validators.TopicModelDtoValidator;
import easytests.services.SubjectsService;
import easytests.services.TopicsService;
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
@RequestMapping("/personal/topics/")
public class TopicsController extends AbstractPersonalController {

    @Autowired
    private TopicsService topicsService;

    @Autowired
    private SubjectsService subjectsService;

    private TopicModelDtoValidator topicModelDtoValidator = new TopicModelDtoValidator();

    private void checkModel(TopicModelInterface topicModel) {
        if (topicModel == null) {
            throw new NotFoundException();
        }
        if (!topicModel.getSubject().getUser().getId().equals(this.getCurrentUserModel().getId())) {
            throw new ForbiddenException();
        }
    }

    private TopicModelInterface getTopicModel(Integer id) {
        final TopicModelInterface topicModel = this.topicsService.find(id);
        checkModel(topicModel);
        return topicModel;
    }

    private TopicModelInterface getTopicModel(Integer id, TopicsOptionsInterface topicsOptions) {
        final TopicModelInterface topicModel = this.topicsService.find(id, topicsOptions);
        checkModel(topicModel);
        return topicModel;
    }

    //TODO: How to get Topics related to current User?
    @RequestMapping("list")
    public String list(Model model) {
        /**final List<SubjectModelInterface> subjects = this.subjectsService.findByUser(this.getCurrentUserModel());
        final List<TopicModelInterface> topics = this.topicsService.findBySubject(subjects);
        model.addAttribute("topics", topics);**/
        return "topics/list";
    }

    @GetMapping("create")
    public String create(Model model) {
        final TopicDto topic = new TopicDto();
        topic.setName("");
        model.addAttribute("methodType", "create");
        model.addAttribute("topic", topic);
        return "topics/form";
    }

    @PostMapping("create")
    public String create(@Valid @NotNull TopicDto topic,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("methodType", "create");
            model.addAttribute("topic", topic);
            model.addAttribute("errors", bindingResult);
            return "topics/form";
        }
        final TopicModelInterface topicModel = new TopicModel();
        final SubjectModelInterface subjectModel = new SubjectModel();
        topic.mapInto(topicModel);
        subjectModel.setUser(this.getCurrentUserModel());
        topicModel.setSubject(subjectModel);
        topicsService.save(topicModel);
        return "redirect:/personal/topics/list";
    }

    @GetMapping("{id}")
    public String read(@PathVariable("id") Integer id,
                       Model model) {
        final TopicModelInterface topicModel = getTopicModel(id);

        final TopicDto topic = new TopicDto();
        topic.map(topicModel);
        model.addAttribute("topic", topic);

        return "topics/read";
    }

    @GetMapping("update/{id}")
    public String update(@PathVariable("id") Integer id,
                         Model model) {
        model.addAttribute("methodType", "update");
        final TopicModelInterface topicModel = getTopicModel(id);
        final TopicDto topic = new TopicDto();
        topic.map(topicModel);
        model.addAttribute("topic", topic);
        return "topics/form";
    }

    @PostMapping("update/{id}")
    public String update(@PathVariable("id") Integer topicId,
                         @Valid @NotNull TopicDto topic,
                         BindingResult bindingResult,
                         Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("methodType", "update");
            model.addAttribute("topic", topic);
            model.addAttribute("errors", bindingResult);
            return "topics/form";
        }
        final TopicModelInterface topicModel = new TopicModel();
        final SubjectModelInterface subjectModel = new SubjectModel();
        topic.mapInto(topicModel);
        topicModel.setId(topicId);
        subjectModel.setUser(this.getCurrentUserModel());
        topicModel.setSubject(subjectModel);
        topicsService.save(topicModel);
        return "redirect:/personal/topics/list";
    }

    @GetMapping("delete/{id}")
    public String deleteConfirmation(@PathVariable("id") Integer id,
                                     Model model) {
        final TopicDto topicDto = new TopicDto();
        final TopicModelInterface topicModel = getTopicModel(id);
        topicDto.map(topicModel);
        model.addAttribute("topic", topicDto);
        return "topics/delete";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer topicId,
                         TopicDto topicDto,
                         BindingResult bindingResult,
                         Model model) {
        topicModelDtoValidator.validate(topicDto, bindingResult);
        final TopicModelInterface topicModel = getTopicModel(topicId);
        topicsService.delete(topicModel);
        return "redirect:/personal/topics/list";
    }
}
