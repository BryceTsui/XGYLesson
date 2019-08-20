package nju.controller;

import nju.model.Reply;
import nju.model.Topic;
import nju.server.BBSService;
import nju.vo.bbs.ReplyVO;
import nju.vo.bbs.TopicDetailVO;
import nju.vo.bbs.TopicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by thinkpad on 2019/2/27.
 */

@Controller
@RequestMapping("/j2ee/bbs")
@CrossOrigin
public class BBSController {


    @Autowired
    BBSService bbsService;

    @ResponseBody
    @RequestMapping(
            value = "/create/topic",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public void createTopic(@RequestBody Topic topic){

        System.out.println(topic.getCourseid());
        bbsService.createTopic(topic);
    }

    @ResponseBody
    @RequestMapping(
            value = "/create/reply",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public ReplyVO reply(@RequestBody  Reply reply){

        return bbsService.reply(reply);
    }

    @ResponseBody
    @RequestMapping(
            value = "/gettopic/courseid",
            method = RequestMethod.POST
    )
    public List<Topic> getTopicByCourseId(int courseId){
        return bbsService.getTopicByCourseId(courseId);
    }

    @ResponseBody
    @RequestMapping(
            value = "/getreply",
            method = RequestMethod.POST
    )
    public List<Reply> getReplyByTopicId(int topicId){
        return bbsService.getReplyByTopicId(topicId);
    }


    @ResponseBody
    @RequestMapping(
            value = "/gettopicvo",
            method = RequestMethod.POST
    )
    public List<TopicVO> getTopicVO(int courseid,String type){

        return bbsService.getTopicVO(courseid,type);
    }

    @ResponseBody
    @RequestMapping(
            value = "/gettopic/detail",
            method = RequestMethod.POST
    )
    public TopicDetailVO getTopicDetail(int topicid){

        System.out.println("topicid"+topicid);
        return bbsService.getTopicDetail(topicid);
    }


}
