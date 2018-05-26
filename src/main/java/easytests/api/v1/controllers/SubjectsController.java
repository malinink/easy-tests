package easytests.api.v1.controllers;

import easytests.api.v1.exceptions.ForbiddenException;
import easytests.api.v1.exceptions.NotFoundException;
import easytests.api.v1.exceptions.UnidentifiedModelException;
import easytests.api.v1.mappers.SubjectsMapper;
import easytests.api.v1.models.Subject;
import easytests.core.models.SubjectModelInterface;
import easytests.core.models.UserModelInterface;
import easytests.core.options.SubjectsOptionsInterface;
import easytests.core.options.builder.SubjectsOptionsBuilderInterface;
import easytests.core.services.SubjectsServiceInterface;
import easytests.core.services.UsersServiceInterface;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author VeronikaRevjakina
 */
@RestController("SubjectsControllerV1")
@SuppressWarnings({"checkstyle:linelength", "checkstyle:MultipleStringLiterals"})
@RequestMapping("/v1/subjects")
public class SubjectsController extends AbstractController {

    @Autowired
    protected SubjectsServiceInterface subjectsService;

    @Autowired
    protected SubjectsOptionsBuilderInterface subjectsOptionsBuilder;

    @Autowired
    protected UsersServiceInterface usersService;

    @Autowired
    @Qualifier("SubjectsMapperV1")
    private SubjectsMapper subjectsMapper;

    @GetMapping("")
    public List<Subject> list(@RequestParam(name = "userId", required = true) Integer userId)
            throws NotFoundException, ForbiddenException {
        final UserModelInterface userModel = this.usersService.find(userId);

        if (userModel == null) {
            throw new NotFoundException();
        }
        if (!this.acl.hasAccess(userModel)) {
            throw new ForbiddenException();
        }

        final List<SubjectModelInterface> subjectsModels = this.subjectsService.findByUser(userModel);

        return subjectsModels
                .stream()
                .map(model -> this.subjectsMapper.map(model, Subject.class))
                .collect(Collectors.toList());
    }
    /**
     * create
     */

    @PutMapping("")
    public void update(@RequestBody Subject subject) throws UnidentifiedModelException, NotFoundException, ForbiddenException {

        if (subject.getId() == null) {
            throw new UnidentifiedModelException();
        }

        final SubjectModelInterface subjectModel = this.getSubjectModel(subject.getId());

        if (!this.acl.hasAccess(subjectModel.getUser())) {
            throw new ForbiddenException();
        }

        this.subjectsMapper.map(subject, subjectModel);

        this.subjectsService.save(subjectModel);
    }
    /**
     * show(subjectId)
     */

    @GetMapping("/{subjectId}")
    public Subject show(@PathVariable Integer subjectId) throws NotFoundException, ForbiddenException {
        final SubjectModelInterface subjectModel = this.getSubjectModel(subjectId);

        if (!this.acl.hasAccess(subjectModel)) {
            throw new ForbiddenException();
        }
        return this.subjectsMapper.map(subjectModel, Subject.class);
    }

    /**
     * delete(subjectId)
     */

    private SubjectModelInterface getSubjectModel(Integer id, SubjectsOptionsInterface subjectOptions)
            throws NotFoundException {
        final SubjectModelInterface subjectModel = this.subjectsService.find(id, subjectOptions);
        if (subjectModel == null) {
            throw new NotFoundException();
        }
        return subjectModel;
    }

    private SubjectModelInterface getSubjectModel(Integer id) throws NotFoundException {
        return this.getSubjectModel(id, this.subjectsOptionsBuilder.forAuth());
    }
}
