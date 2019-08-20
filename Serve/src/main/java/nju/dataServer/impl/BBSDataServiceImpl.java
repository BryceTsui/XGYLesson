package nju.dataServer.impl;

import nju.dataServer.BBSDataService;
import nju.datahelper.HibernateHelper;
import nju.model.Course;
import nju.model.Reply;
import nju.model.SelectedCourse;
import nju.model.Topic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkpad on 2019/3/6.
 */
public class BBSDataServiceImpl implements BBSDataService {

    HibernateHelper<Topic> topicHibernateHelper = new HibernateHelper<>(Topic.class);
    HibernateHelper<SelectedCourse> selectedCourseHibernateHelper = new HibernateHelper<>(SelectedCourse.class);
    HibernateHelper<Reply> replyHibernateHelper = new HibernateHelper<>(Reply.class);
    HibernateHelper<Course> courseHibernateHelper = new HibernateHelper<>(Course.class);
    public List<Topic> getTopicByStudentName(String name){
        List<SelectedCourse> selectedCourses = selectedCourseHibernateHelper.fullMatchQuery("studentname",name);
        List<Topic> result = new ArrayList<>();
        for(int i=0;i<=selectedCourses.size()-1;i++){
            result.addAll(getTopicByCourseId(selectedCourses.get(i).getCourseid()));
        }

        return result;
    }

    public void saveTopic(Topic topic){
        topicHibernateHelper.save(topic);
    }
    public void saveReply(Reply reply){
        replyHibernateHelper.save(reply);
    }
    public List<Reply> getReplyByTopicId(int topicid){
        return replyHibernateHelper.fullMatchQuery("topicid",topicid);
    }

    public List<Topic> getTopicByCourseId(int CourseId){
        return topicHibernateHelper.fullMatchQuery("courseid",CourseId);
    }


    public Topic getTopicById(int id){
        return topicHibernateHelper.exactlyQuery("id",id);
    }
    public List<Topic> getTopicByTeacherName(String teachername){
        List<Course> courses =courseHibernateHelper.fullMatchQuery("teachername",teachername);

        List<Course> publihs = new ArrayList<>();

        for(Course c:courses){
            if(c.getCanpublish()==2){
                publihs.add(c);
            }
        }


        List<Topic> result = new ArrayList<>();
        for(int i=0;i<=publihs.size()-1;i++){
            result.addAll(getTopicByCourseId(publihs.get(i).getId()));
        }

        return result;


    }

    public List<Topic> getTopicByType(String type){


        return topicHibernateHelper.fullMatchQuery("type",type);
    }
}
