package nju.server;

import nju.model.Reply;
import nju.model.Topic;
import nju.vo.bbs.ReplyVO;
import nju.vo.bbs.TopicDetailVO;
import nju.vo.bbs.TopicVO;

import java.util.List;

/**
 * Created by thinkpad on 2019/2/25.
 */
public interface BBSService {

    public void createTopic(Topic topic);

    public ReplyVO reply(Reply reply);

    public List<Topic> getTopicByCourseId(int courseId);

    public List<Reply> getReplyByTopicId(int topicId);
    public List<TopicVO> getTopicVO(int courseid,String type);

    public TopicDetailVO getTopicDetail(int topicid);
}
