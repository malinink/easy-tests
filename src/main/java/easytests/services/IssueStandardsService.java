package easytests.services;

import easytests.entities.*;
import easytests.mappers.IssueStandardQuestionTypeOptionsMapper;
import easytests.mappers.IssueStandardTopicPrioritiesMapper;
import easytests.mappers.IssueStandardsMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author SingularityA
 */
public class IssueStandardsService {

    @Autowired
    private IssueStandardsMapper issueStandardsMapper;

    @Autowired
    private IssueStandardTopicPrioritiesMapper topicPriorityMapper;

    @Autowired
    private IssueStandardQuestionTypeOptionsMapper questionTypeOptionMapper;

    public List<IssueStandardInterface> findAll() {
        final List<IssueStandardInterface> issueStandards = this.map(this.issueStandardsMapper.findAll());

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
        final IssueStandardInterface issueStandard = this.issueStandardsMapper.find(id);

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
        final IssueStandardInterface issueStandard = this.issueStandardsMapper.findBySubject(subject);

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
            this.issueStandardsMapper.update(issueStandard);

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
            this.issueStandardsMapper.insert(issueStandard);

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
        this.issueStandardsMapper.delete(issueStandard);
    }

    private <T> List<T> map(List<? extends T> objectList) {
        final List<T> resultObjectList = new ArrayList<>(objectList.size());
        for (T object: objectList) {
            resultObjectList.add(object);
        }
        return resultObjectList;
    }
}
