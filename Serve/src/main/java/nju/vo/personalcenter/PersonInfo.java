package nju.vo.personalcenter;

import nju.model.Broadcast;
import nju.model.Course;
import nju.vo.ShortTopic;

import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 2019/3/4.
 */
public class PersonInfo {
    String avator;
    String name;
    int course_ing;
    int course_ed;
    int course_future;


    Map<String,List<Course>> courses;


    /**
     * 公告的标题
     */
    List<Broadcast> broadcast0;
    List<Broadcast> broadcast1;
    List<Broadcast> broadcast2;
    List<Broadcast> broadcast3;


    List<ShortTopic> topics;

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourse_ing() {
        return course_ing;
    }

    public void setCourse_ing(int course_ing) {
        this.course_ing = course_ing;
    }

    public int getCourse_ed() {
        return course_ed;
    }

    public void setCourse_ed(int course_ed) {
        this.course_ed = course_ed;
    }

    public int getCourse_future() {
        return course_future;
    }

    public void setCourse_future(int course_future) {
        this.course_future = course_future;
    }

    public Map<String, List<Course>> getCourses() {
        return courses;
    }

    public void setCourses(Map<String, List<Course>> courses) {
        this.courses = courses;
    }

    public List<Broadcast> getBroadcast0() {
        return broadcast0;
    }

    public void setBroadcast0(List<Broadcast> broadcast0) {
        this.broadcast0 = broadcast0;
    }

    public List<Broadcast> getBroadcast1() {
        return broadcast1;
    }

    public void setBroadcast1(List<Broadcast> broadcast1) {
        this.broadcast1 = broadcast1;
    }

    public List<Broadcast> getBroadcast2() {
        return broadcast2;
    }

    public void setBroadcast2(List<Broadcast> broadcast2) {
        this.broadcast2 = broadcast2;
    }

    public List<Broadcast> getBroadcast3() {
        return broadcast3;
    }

    public void setBroadcast3(List<Broadcast> broadcast3) {
        this.broadcast3 = broadcast3;
    }

    public List<ShortTopic> getTopics() {
        return topics;
    }

    public void setTopics(List<ShortTopic> topics) {
        this.topics = topics;
    }

    public void print(){
        System.out.println("avator: "+avator);
        System.out.println("name: "+name);
        System.out.println("course_ing:"+course_ing);
        System.out.println("course_end"+course_ed);
        System.out.println("course_future"+course_future);
        System.out.println("courses_size:"+courses.size());



    }

}
