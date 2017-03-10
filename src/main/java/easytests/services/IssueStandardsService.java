package easytests.services;

import easytests.entities.*;
import easytests.mappers.IssueStandardMapper;
import easytests.mappers.IssueStandardQuestionTypeOptionMapper;
import easytests.mappers.IssueStandardTopicPriorityMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author SingularityA
 */
public class IssueStandardService {

    @Autowired
    private IssueStandardMapper issueStandardMapper;

    @Autowired
    private IssueStandardTopicPriorityMapper topicPriorityMapper;

    @Autowired
    private IssueStandardQuestionTypeOptionMapper questionTypeOptionMapper;

    public List<IssueStandardInterface> findAll() {
        final List<IssueStandardInterface> issueStandards = this.map(this.issueStandardMapper.findAll());

        if (issueStandards != null) {
            List<IssueStandardTopicPriorityInterface> topicPriorities;
            List<IssueStandardQuestionTypeOptionInterface> questionTypeOptions;

            for (IssueStandardInterface issueStandard : issueStandards) {
                topicPriorities = this.map(this.topicPriorityMapper.findByIssueStandard(issueStandard));
                questionTypeOptions = this.map(this.questionTypeOptionMapper.findByIssueStandard(issueStandard));
                issueStandard
                        .setIssueStandardTopicPriorities(topicPriorities)
                        .setIssueStandardQuestionTypeOptions(questionTypeOptions);
            }
        }
        return issueStandards;
    }

    public IssueStandardInterface find(Integer id) {
        final IssueStandardInterface issueStandard = this.issueStandardMapper.find(id);

        if (issueStandard != null) {
            final List<IssueStandardTopicPriorityInterface> topicPriorities
                    = this.map(this.topicPriorityMapper.findByIssueStandard(issueStandard));
            final List<IssueStandardQuestionTypeOptionInterface> questionTypeOptions
                    = this.map(this.questionTypeOptionMapper.findByIssueStandard(issueStandard));

            issueStandard
                    .setIssueStandardTopicPriorities(topicPriorities)
                    .setIssueStandardQuestionTypeOptions(questionTypeOptions);
        }
        return issueStandard;
    }

    public IssueStandardInterface findBySubject(SubjectInterface subject) {
        final IssueStandardInterface issueStandard = this.issueStandardMapper.findBySubject(subject);

        if (issueStandard != null) {
            final List<IssueStandardTopicPriorityInterface> topicPriorities
                    = this.map(this.topicPriorityMapper.findByIssueStandard(issueStandard));
            final List<IssueStandardQuestionTypeOptionInterface> questionTypeOptions
                    = this.map(this.questionTypeOptionMapper.findByIssueStandard(issueStandard));

            issueStandard
                    .setIssueStandardTopicPriorities(topicPriorities)
                    .setIssueStandardQuestionTypeOptions(questionTypeOptions);
        }
        return issueStandard;
    }

    public void save(IssueStandardInterface issueStandard) {
        if (issueStandard.getId() != null) {
            // обновляем issueStandard
            this.issueStandardMapper.update(issueStandard);

            // обновляем topicPriorities
            for (IssueStandardTopicPriorityInterface topicPriority : issueStandard.getIssueStandardTopicPriorities()) {

                if (topicPriority.getId() != null) {
                    // при пустом значащем поле удаляем (сделать отдельную функцию ??? )
                    if (topicPriority.getPriority() != null) {
                        this.topicPriorityMapper.update(topicPriority);
                    } else {
                        this.topicPriorityMapper.delete(topicPriority);
                    }
                } else {
                    this.topicPriorityMapper.insert(topicPriority);
                }
            }

            // обновляем questionTypeOptions
            for (IssueStandardQuestionTypeOptionInterface
                    questionTypeOption : issueStandard.getIssueStandardQuestionTypeOptions()) {

                if (questionTypeOption.getId() != null) {
                    // при пустом значащем поле удаляем
                    if (questionTypeOption.getQuestionTypeId() != null) {
                        this.questionTypeOptionMapper.update(questionTypeOption);
                    } else {
                        this.questionTypeOptionMapper.delete(questionTypeOption);
                    }
                } else {
                    this.questionTypeOptionMapper.insert(questionTypeOption);
                }
            }

        } else {
            // создаем issueStandard
            this.issueStandardMapper.insert(issueStandard);

            // создаем topicPriorities
            for (IssueStandardTopicPriorityInterface topicPriority : issueStandard.getIssueStandardTopicPriorities()) {
                this.topicPriorityMapper.insert(topicPriority);
            }

            // создаем questionTypeOptions
            for (IssueStandardQuestionTypeOptionInterface
                    questionTypeOption : issueStandard.getIssueStandardQuestionTypeOptions()) {
                this.questionTypeOptionMapper.insert(questionTypeOption);
            }
        }
    }

    public void delete(IssueStandardInterface issueStandard) {
        this.issueStandardMapper.delete(issueStandard);
    }

    private <T> List<T> map(List<? extends T> objectList) {
        final List<T> resultObjectList = new ArrayList<>(objectList.size());
        for (T object: objectList) {
            resultObjectList.add(object);
        }
        return resultObjectList;
    }
}
