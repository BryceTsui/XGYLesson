package nju.server.impl;

import nju.dataServer.*;
import nju.dataServer.impl.*;
import nju.model.*;
import nju.server.PersonalCenterService;
import nju.vo.ShortTopic;
import nju.vo.personalcenter.PersonInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by thinkpad on 2019/3/6.
 */
@Service
public class PersonalCenterServiceImpl implements PersonalCenterService{

    UserDataService userDataService = new UserDataServiceImpl();
    BroadcastDataService broadcastDataService = new BroadcastDataServiceImpl();
    SelectedCourseDataService selectedCourseDataService = new SelectedCourseDataServiceImpl();
    BBSDataService bbsDataService = new BBSDataServiceImpl();
    CourseDataService courseDataService = new CourseDataServiceImpl();
    public PersonInfo getStudentInfo(String studentName){

        PersonInfo personInfo = new PersonInfo();
        User user = userDataService.getUserByName(studentName);

        //初始化name和头像
        personInfo.setName(user.getName());
        personInfo.setAvator(user.getAvator());

        if(user.getRole().equals("student")) {

            //初始化课程
            personInfo.setCourses(selectedCourseDataService.getCourseByStudentName(studentName));


            //初始化公告
            List<SelectedCourse> selectedCourses = selectedCourseDataService.getSelectedCourseIdByStudentName(studentName);

            personInfo.setCourse_ing(selectedCourses.size());

            List<List<Broadcast>> broadcasts = new ArrayList<>();

            for (int i = 1; i <= 4; i++) {
                broadcasts.add(new ArrayList<>());
            }
            for (int i = 0; i <= selectedCourses.size() - 1; i++) {

                List<List<Broadcast>> temp = broadcastDataService.getBroadcastByCourseId(selectedCourses.get(i).getCourseid());

                broadcasts.get(0).addAll(temp.get(0));
                broadcasts.get(1).addAll(temp.get(1));
                broadcasts.get(2).addAll(temp.get(2));
                broadcasts.get(3).addAll(temp.get(3));

            }

            Comparator<Broadcast> comparator = new Comparator<Broadcast>() {
                @Override
                public int compare(Broadcast o1, Broadcast o2) {

                    return o2.getTime().compareTo(o1.getTime());
                }
            };

            broadcasts.get(0).sort(comparator);

            broadcasts.get(1).sort(comparator);
            broadcasts.get(2).sort(comparator);
            broadcasts.get(3).sort(comparator);

            personInfo.setBroadcast0(broadcasts.get(0));
            personInfo.setBroadcast1(broadcasts.get(1));
            personInfo.setBroadcast2(broadcasts.get(2));
            personInfo.setBroadcast3(broadcasts.get(3));


            //初始化讨论区

            Comparator<Topic> topicComparator = new Comparator<Topic>() {
                @Override
                public int compare(Topic o1, Topic o2) {

                    return o2.getId() - o1.getId();
                }
            };

            List<Topic> topics = bbsDataService.getTopicByStudentName(studentName);
            topics.sort(topicComparator);
            List<ShortTopic> shortTopics = new ArrayList<>();

            for (int i = 0; i <= topics.size() - 1; i++) {

                ShortTopic shortTopic = new ShortTopic();
                shortTopic.setAvator(userDataService.getUserByName(topics.get(i).getUsername()).getAvator());
                shortTopic.setTitle(topics.get(i).getTitle());
                shortTopic.setType(topics.get(i).getType());
                shortTopic.setUserName(topics.get(i).getUsername());
                shortTopics.add(shortTopic);
            }
            personInfo.setTopics(shortTopics);

        }
        else{
            //初始化课程
            personInfo.setCourses(courseDataService.getPublishedCourseByTeacherName(user.getName()));


            //初始化公告
            List<Course> courses = courseDataService.getPublishedCourseListByTeachername(user.getName());
            List<List<Broadcast>> broadcasts = new ArrayList<>();

            for (int i = 1; i <= 4; i++) {
                broadcasts.add(new ArrayList<>());
            }
            for (int i = 0; i <= courses.size() - 1; i++) {

                List<List<Broadcast>> temp = broadcastDataService.getBroadcastByCourseId(courses.get(i).getId());

                broadcasts.get(0).addAll(temp.get(0));
                broadcasts.get(1).addAll(temp.get(1));
                broadcasts.get(2).addAll(temp.get(2));
                broadcasts.get(3).addAll(temp.get(3));

            }

            Comparator<Broadcast> comparator = new Comparator<Broadcast>() {
                @Override
                public int compare(Broadcast o1, Broadcast o2) {

                    return o2.getTime().compareTo(o1.getTime());
                }
            };

            broadcasts.get(0).sort(comparator);

            broadcasts.get(1).sort(comparator);
            broadcasts.get(2).sort(comparator);
            broadcasts.get(3).sort(comparator);

            personInfo.setBroadcast0(broadcasts.get(0));
            personInfo.setBroadcast1(broadcasts.get(1));
            personInfo.setBroadcast2(broadcasts.get(2));
            personInfo.setBroadcast3(broadcasts.get(3));


            //初始化讨论区

            Comparator<Topic> topicComparator = new Comparator<Topic>() {
                @Override
                public int compare(Topic o1, Topic o2) {

                    return o2.getId() - o1.getId();
                }
            };

            List<Topic> topics = bbsDataService.getTopicByTeacherName(user.getName());
            topics.sort(topicComparator);
            List<ShortTopic> shortTopics = new ArrayList<>();

            for (int i = 0; i <= topics.size() - 1; i++) {

                ShortTopic shortTopic = new ShortTopic();
                shortTopic.setAvator(userDataService.getUserByName(topics.get(i).getUsername()).getAvator());
                shortTopic.setTitle(topics.get(i).getTitle());
                shortTopic.setType(topics.get(i).getType());
                shortTopic.setUserName(topics.get(i).getUsername());
                shortTopics.add(shortTopic);
            }
            personInfo.setTopics(shortTopics);
        }
        return personInfo;
    }

    public static void main(String[] args){

        PersonalCenterService personalCenterService = new PersonalCenterServiceImpl();
        PersonInfo personInfo = personalCenterService.getStudentInfo("xgy");

        System.out.println(personInfo.getTopics().size());
    }



}
