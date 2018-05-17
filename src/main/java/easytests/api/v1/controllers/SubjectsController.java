package easytests.api.v1.controllers;

import easytests.api.v1.mappers.SubjectsMapper;
import easytests.api.v1.models.Subject;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.empty.UserModelEmpty;
import easytests.core.options.builder.SubjectsOptionsBuilderInterface;
import easytests.core.services.SubjectsServiceInterface;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;


/**
 * @author VeronikaRevjakina
 */
@RestController("SubjectsControllerV1")
@SuppressWarnings("checkstyle:MultipleStringLiterals")
@RequestMapping("/v1/subjects")
public class SubjectsController {

    @Autowired
    protected SubjectsServiceInterface subjectsService;

    @Autowired
    private SubjectsOptionsBuilderInterface subjectsOptionsBuilder;

    @Autowired
    @Qualifier("SubjectsMapperV1")
    private SubjectsMapper subjectsMapper;


    @GetMapping("")
    public List<Subject> list(@RequestParam(name = "userId", required = true) Integer userId) {
        final List<SubjectModelInterface> subjectsModels = this.subjectsService.findByUser(new UserModelEmpty(userId));

        //todo: ACL

        return subjectsModels
                .stream()
                .map(model -> this.subjectsMapper.map(model, Subject.class))
                .collect(Collectors.toList());
    }
    /**
     * create
     */
    /**
     * update
     */
    /**
     * show(subjectId)
     */
    /**
     * delete(subjectId)
     */
}
