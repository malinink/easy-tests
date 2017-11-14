package easytests.integration.services;

import easytests.core.models.*;
import easytests.core.models.empty.IssueStandardModelEmpty;
import easytests.core.models.empty.ModelsListEmpty;
import easytests.core.models.empty.UserModelEmpty;
import easytests.core.services.SubjectsService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author vkpankov
 */
public class SubjectsServiceTest extends AbstractServiceTest {

    @Autowired
    private SubjectsService subjectsService;

    private SubjectModelInterface createSubjectModel(Integer id, String name, Integer userId) {

        final SubjectModelInterface subjectModel = new SubjectModel();
        subjectModel.setId(id);
        subjectModel.setName(name);
        subjectModel.setTopics(new ModelsListEmpty());
        subjectModel.setIssueStandard(new IssueStandardModelEmpty());
        subjectModel.setUser(new UserModelEmpty(userId));
        subjectModel.setIssues(new ModelsListEmpty());

        return subjectModel;
    }

    @Test
    public void testSaveModel() throws Exception {

        final SubjectModelInterface subjectModel = new SubjectModel();
        subjectModel.setName("test111");
        subjectModel.setTopics(new ModelsListEmpty());
        subjectModel.setIssueStandard(new IssueStandardModelEmpty());
        subjectModel.setUser(new UserModelEmpty(1));
        subjectModel.setIssues(new ModelsListEmpty());
        this.subjectsService.save(subjectModel);

        final SubjectModelInterface foundedSubjectModel = this.subjectsService.find(subjectModel.getId());

        Assert.assertEquals(subjectModel, foundedSubjectModel);

    }

    @Test
    public void testFindPresentModel() throws Exception {
        final Integer id = 1;
        final SubjectModelInterface subjectModel = this.createSubjectModel(id, "Subject1" , 1);

        final SubjectModelInterface foundedSubjectModel = this.subjectsService.find(id);

        Assert.assertEquals(subjectModel.getId(), foundedSubjectModel.getId());
        Assert.assertEquals(subjectModel.getName(), foundedSubjectModel.getName());
    }

    @Test
    public void testFindAbsentModel() throws Exception {
        final Integer id = 10;
        final SubjectModelInterface subjectModel = this.subjectsService.find(id);

        Assert.assertEquals(null, subjectModel);
    }
}
