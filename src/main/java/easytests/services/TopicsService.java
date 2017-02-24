package easytests.services;

import easytests.entities.*;
import easytests.mappers.TopicsMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author loriens
 */

public class TopicsService {

    @Autowired
    private TopicsMapper topicsMapper;

    public List<TopicInterface> findAll() {
        return this.map(this.topicsMapper.findAll());
    }

    public TopicInterface find(Integer id) {
        return this.topicsMapper.find(id);
    }

    public void save(TopicInterface topic) {
        if (topic.getId() == null) {
            this.topicsMapper.insert(topic);
            return;
        }
        this.topicsMapper.update(topic);
    }

    public void delete(TopicInterface topic) {
        this.topicsMapper.delete(topic);
    }


    private List<TopicInterface> map(List<Topic> topictList) {
        final List<TopicInterface> resultTopicList = new ArrayList(topictList.size());
        for (Topic topic: topictList) {
            resultTopicList.add(topic);
        }
        return resultTopicList;
    }

}
