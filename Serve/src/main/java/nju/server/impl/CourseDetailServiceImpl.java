package nju.server.impl;

import nju.dataServer.*;
import nju.dataServer.impl.*;
import nju.model.*;
import nju.server.CourseDetailService;
import nju.vo.ShortTopic;
import nju.vo.coursepage.CourseInfo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by thinkpad on 2019/3/7.
 */
@Service
public class CourseDetailServiceImpl implements CourseDetailService {

    CourseDataService courseDataService = new CourseDataServiceImpl();

    UserDataService userDataService  = new UserDataServiceImpl();
    BroadcastDataService broadcastDataService = new BroadcastDataServiceImpl();
    BBSDataService bbsDataService = new BBSDataServiceImpl();
    SelectedCourseDataService selectedCourseDataService = new SelectedCourseDataServiceImpl();
    public CourseInfo getInfo(String username, int courseid){

        User user = userDataService.getUserByName(username);

        if(user.getRole().equals("student")) {
            CourseInfo courseInfo = new CourseInfo();

            //初始化课程名
            //SelectedCourse selectedCourse = selectedCourseDataService.getSelectedCourseById(courseid);
            SelectedCourse selectedCourse = selectedCourseDataService.getSelectedCourseByNameAndCourseId(username, courseid);
            Course course = courseDataService.getCourseById(courseid);
            courseInfo.setName(course.getName());

            //初始化上课地点
            courseInfo.setLocation(course.getClassroom());

            courseInfo.setTime(course.getTime());

            courseInfo.setId(course.getId());

            //初始化ppt数量，未完成的作业数量，新的讨论话题数量
            List<Resourse> resourses = courseDataService.getResourseByCourseId(course.getId());
            courseInfo.setPpt(resourses.size());

            List<Topic> topics = bbsDataService.getTopicByCourseId(course.getId());
            courseInfo.setNew_topic(topics.size());

            List<Homework> homework = selectedCourseDataService.getHomeworkByCourseId(courseid);
            courseInfo.setUnfinished_homework(homework.size());

            //获得三个成绩
            courseInfo.setScore_common(selectedCourse.getScore_common());
            courseInfo.setScore_exam(selectedCourse.getScore_exam());
            courseInfo.setScore_all(selectedCourse.getScore_all());

            //初始化公告
            List<List<Broadcast>> broadcasts = broadcastDataService.getBroadcastByCourseId(course.getId());

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
            courseInfo.setBroadcast0(broadcasts.get(0));
            courseInfo.setBroadcast1(broadcasts.get(1));
            courseInfo.setBroadcast2(broadcasts.get(2));
            courseInfo.setBroadcast3(broadcasts.get(3));

            //初始化讨论区

            Comparator<Topic> topicComparator = new Comparator<Topic>() {
                @Override
                public int compare(Topic o1, Topic o2) {

                    return o2.getId() - o1.getId();
                }
            };
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
            courseInfo.setTopics(shortTopics);

            //初始化作业和PPT
            System.out.println(resourses.size());
            courseInfo.setResources(resourses);
            courseInfo.setHomeworks(homework);

            return courseInfo;
        }
        else{
            CourseInfo courseInfo = new CourseInfo();

            //初始化课程名
            //SelectedCourse selectedCourse = selectedCourseDataService.getSelectedCourseById(courseid);
         //   SelectedCourse selectedCourse = selectedCourseDataService.getSelectedCourseByNameAndCourseId(username, courseid);
            Course course = courseDataService.getCourseById(courseid);
            courseInfo.setName(course.getName());

            //初始化上课地点
            courseInfo.setLocation(course.getClassroom());

            courseInfo.setTime(course.getTime());

            courseInfo.setId(course.getId());

            //初始化ppt数量，未完成的作业数量，新的讨论话题数量
            List<Resourse> resourses = courseDataService.getResourseByCourseId(course.getId());
            courseInfo.setPpt(resourses.size());

            List<Topic> topics = bbsDataService.getTopicByCourseId(course.getId());
            courseInfo.setNew_topic(topics.size());

            List<Homework> homework = selectedCourseDataService.getHomeworkByCourseId(courseid);
            courseInfo.setUnfinished_homework(homework.size());

            //获得三个成绩
          //  courseInfo.setScore_common(selectedCourse.getScore_common());
           // courseInfo.setScore_exam(selectedCourse.getScore_exam());
           // courseInfo.setScore_all(selectedCourse.getScore_all());

            //初始化公告
            List<List<Broadcast>> broadcasts = broadcastDataService.getBroadcastByCourseId(course.getId());

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
            courseInfo.setBroadcast0(broadcasts.get(0));
            courseInfo.setBroadcast1(broadcasts.get(1));
            courseInfo.setBroadcast2(broadcasts.get(2));
            courseInfo.setBroadcast3(broadcasts.get(3));

            //初始化讨论区

            Comparator<Topic> topicComparator = new Comparator<Topic>() {
                @Override
                public int compare(Topic o1, Topic o2) {

                    return o2.getId() - o1.getId();
                }
            };
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
            courseInfo.setTopics(shortTopics);

            //初始化作业和PPT
            System.out.println(resourses.size());
            courseInfo.setResources(resourses);
            courseInfo.setHomeworks(homework);

            return courseInfo;
        }
    }
}
