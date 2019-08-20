package nju.dataServer;

import nju.model.Reply;
import nju.model.Topic;

import java.util.List;

/**
 * Created by thinkpad on 2019/2/25.
 */
public interface BBSDataService {

    void saveTopic(Topic topic);
    void saveReply(Reply reply);
    public List<Topic> getTopicByStudentName(String name);

    public List<Topic> getTopicByTeacherName(String teachername);
    List<Reply> getReplyByTopicId(int topicid);

    List<Topic> getTopicByCourseId(int CourseId);

    Topic getTopicById(int id);

    List<Topic> getTopicByType(String type);
}
