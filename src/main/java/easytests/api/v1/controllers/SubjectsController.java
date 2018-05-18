package easytests.api.v1.controllers;

import easytests.api.v1.mappers.SubjectsMapper;
import easytests.core.options.builder.SubjectsOptionsBuilderInterface;
import easytests.core.services.SubjectsServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    /**
     * list
     */
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
