package nju.server.impl;

import nju.dataServer.BBSDataService;
import nju.dataServer.UserDataService;
import nju.dataServer.impl.BBSDataServiceImpl;
import nju.dataServer.impl.UserDataServiceImpl;
import nju.model.Reply;
import nju.model.Topic;
import nju.model.User;
import nju.server.BBSService;
import nju.utils.TimeUtil;
import nju.vo.bbs.ReplyVO;
import nju.vo.bbs.TopicDetailVO;
import nju.vo.bbs.TopicVO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkpad on 2019/3/12.
 */
@Service
public class BBSServiceImpl implements BBSService {

    BBSDataService bbsDataService = new BBSDataServiceImpl();
    UserDataService userDataService = new UserDataServiceImpl();
    public void createTopic(Topic topic){

        TimeUtil timeUtil = new TimeUtil();
        topic.setTime(timeUtil.toString());
        bbsDataService.saveTopic(topic);
    }

    public ReplyVO reply(Reply reply){
        TimeUtil timeUtil = new TimeUtil();
        reply.setTime(timeUtil.toString());
        bbsDataService.saveReply(reply);

        ReplyVO replyVO = new ReplyVO();
        replyVO.setUsername(reply.getUsername());
        replyVO.setTime(reply.getTime());
        replyVO.setContent(reply.getContent());
        User user = userDataService.getUserByName(reply.getUsername());
        replyVO.setAvator(user.getAvator());
        return replyVO;
    }

    public List<Topic> getTopicByCourseId(int courseId){

        return bbsDataService.getTopicByCourseId(courseId);
    }

    public List<Reply> getReplyByTopicId(int topicId){
        return bbsDataService.getReplyByTopicId(topicId);
    }

    public List<TopicVO> getTopicVO(int courseid,String type){
        System.out.println(type);
        List<Topic> topics = bbsDataService.getTopicByCourseId(courseid);

        List<TopicVO> topicVOS = new ArrayList<>();
        for(Topic t:topics){

            if(!type.equals("all")&&(!t.getType().equals(type))){
                continue;
            }
            TopicVO topicVO = new TopicVO();
            topicVO.setContent(t.getDescription());
            topicVO.setAuthor_id(t.getUsername());
            topicVO.setId(t.getId());
            topicVO.setTab(t.getType());
            topicVO.setTitle(t.getTitle());
            topicVO.setLast_reply_at(t.getTime());
            User user = userDataService.getUserByName(t.getUsername());
            topicVO.setAvator(user.getAvator());

            topicVOS.add(topicVO);
        }
        System.out.println(topicVOS.size());

        return topicVOS;
    }

    public TopicDetailVO getTopicDetail(int topicid){
        Topic topic = bbsDataService.getTopicById(topicid);

        TopicDetailVO topicDetailVO = new TopicDetailVO();
        topicDetailVO.setAuthor_id(topic.getUsername());
        User user = userDataService.getUserByName(topic.getUsername());
        topicDetailVO.setAvator(user.getAvator());
        topicDetailVO.setContent(topic.getDescription());
        topicDetailVO.setId(topic.getId());
        topicDetailVO.setLast_reply_at(topic.getTime());
        topicDetailVO.setTab(topic.getType());
        topicDetailVO.setTitle(topic.getTitle());


        List<Reply> replies = bbsDataService.getReplyByTopicId(topicid);

        List<ReplyVO> replyVOS = new ArrayList<>();

        for(Reply r:replies){
            ReplyVO replyVO = new ReplyVO();
            User user1 = userDataService.getUserByName(r.getUsername());

            replyVO.setAvator(user1.getAvator());
            replyVO.setContent(r.getContent());
            replyVO.setTime(r.getTime());
            replyVO.setUsername(user1.getName());

            replyVOS.add(replyVO);
        }

        topicDetailVO.setReplies(replyVOS);

        return topicDetailVO;


    }
}
