package nju.server.impl;

import nju.dataServer.*;
import nju.dataServer.impl.*;
import nju.model.Course;
import nju.model.SelectedCourse;
import nju.model.User;
import nju.server.AdminService;
import nju.vo.chart.GPAItem;
import nju.vo.chart.TopicPercent;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkpad on 2019/3/11.
 */
@Service
public class AdminServiceImpl implements AdminService {

    CourseDataService courseDataService = new CourseDataServiceImpl();

    UserDataService userDataService = new UserDataServiceImpl();

    BBSDataService bbsDataService = new BBSDataServiceImpl();

    BroadcastDataService broadcastDataService = new BroadcastDataServiceImpl();

    SelectedCourseDataService selectedCourseDataService = new SelectedCourseDataServiceImpl();

    public List<Course> getAllUncheckCourse(){
        return courseDataService.getCourseByCheck(0);
    }

    public void check(int courseid,int check){
        Course course = courseDataService.getCourseById(courseid);
        course.setCancheck(check);

        courseDataService.updateCourse(course);
    }

    public void publish(int courseid,int publish){
        Course course = courseDataService.getCourseById(courseid);
        course.setCanpublish(publish);
        courseDataService.updateCourse(course);
    }

    public List<Course> getAllUnpublishCourse(){

        return courseDataService.getCourseByPublish(1);
    }

    public double getStudentPercent(){

        List<User> users= userDataService.getUserByType("student");
        List<User> users1 = userDataService.getUserByType("teacher");

        System.out.println(users.size());
        return ((double)users.size()/(users.size()+users1.size()));
    }


    public List<TopicPercent> getAllTopicPercent() {

        TopicPercent share = new TopicPercent();
        share.setName("分享");
        share.setValue(bbsDataService.getTopicByType("share").size());


        TopicPercent good = new TopicPercent();
        good.setName("精华");
        good.setValue(bbsDataService.getTopicByType("good").size());


        TopicPercent ask = new TopicPercent();
        ask.setName("提问");
        ask.setValue(bbsDataService.getTopicByType("ask").size());


        TopicPercent exp = new TopicPercent();
        exp.setName("实验");
        exp.setValue(bbsDataService.getTopicByType("exp").size());

        List<TopicPercent> list = new ArrayList<>();

        list.add(good);
        list.add(share);
        list.add(ask);
        list.add(exp);

        return list;

    }

    public List<Integer> getAllBroadcast(){

        //成绩作业实验其他
        int score = broadcastDataService.getBroadcastByType("score").size();

        int homework = broadcastDataService.getBroadcastByType("homework").size();

        int experiment = broadcastDataService.getBroadcastByType("experiment").size();

        int other = broadcastDataService.getBroadcastByType("other").size();

        List<Integer> list = new ArrayList<>();

        list.add(score);
        list.add(homework);
        list.add(experiment);
        list.add(other);
        return list;
    }

    public List<List<Object[]>> getAllGPA(){

        List<User> student = userDataService.getUserByType("student");

        List<List<Object[]>> result = new ArrayList<>();

        List<Object[]> list = new ArrayList<>();
        for(User u:student){

            List<SelectedCourse> selectedCourses = selectedCourseDataService.getSelectedCourseIdByStudentName(u.getName());
            Object[] objects = new Object[5];
            if(selectedCourses.size()!=0){
                int all= 0;
                int common = 0;
                for(SelectedCourse selectedCourse:selectedCourses){
                    all +=selectedCourse.getScore_all();
                    common+=selectedCourse.getScore_common();
                }

                double avarage = ((double)all)/selectedCourses.size();
                double avarage_common = ((double)common)/selectedCourses.size();
                objects[0] = avarage;
                objects[1] = avarage_common;
                objects[2] = selectedCourses.size();
                objects[3]= u.getName();
                objects[4] = 1990;

                list.add(objects);

            }
        }
        result.add(list);
        return result;
    }
}
