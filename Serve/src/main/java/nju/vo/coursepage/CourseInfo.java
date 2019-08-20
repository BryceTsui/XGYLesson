package nju.vo.coursepage;

import nju.model.Broadcast;
import nju.model.Homework;
import nju.model.Resourse;
import nju.vo.ShortTopic;

import java.util.List;

/**
 * Created by thinkpad on 2019/3/4.
 */
public class CourseInfo {
    int id;
    String name;

    //上课地点
    String location;
    String time;
    int ppt;
    int unfinished_homework;
    int new_topic;

    double score_common;
    double score_exam;
    double score_all;

    /**
     * 公告，分别为成绩公告，实验通知，作业通知，其他
     */
    /**
     * 公告的标题
     */
    List<Broadcast> broadcast0;
    List<Broadcast> broadcast1;
    List<Broadcast> broadcast2;
    List<Broadcast> broadcast3;


    List<ShortTopic> topics;


    List<Resourse> resources;
    List<Homework> Homeworks;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPpt() {
        return ppt;
    }

    public void setPpt(int ppt) {
        this.ppt = ppt;
    }

    public int getUnfinished_homework() {
        return unfinished_homework;
    }

    public void setUnfinished_homework(int unfinished_homework) {
        this.unfinished_homework = unfinished_homework;
    }

    public int getNew_topic() {
        return new_topic;
    }

    public void setNew_topic(int new_topic) {
        this.new_topic = new_topic;
    }

    public double getScore_common() {
        return score_common;
    }

    public void setScore_common(double score_common) {
        this.score_common = score_common;
    }

    public double getScore_exam() {
        return score_exam;
    }

    public void setScore_exam(double score_exam) {
        this.score_exam = score_exam;
    }

    public double getScore_all() {
        return score_all;
    }

    public void setScore_all(double score_all) {
        this.score_all = score_all;
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

    public List<Resourse> getResources() {
        return resources;
    }

    public void setResources(List<Resourse> resources) {
        this.resources = resources;
    }

    public List<Homework> getHomeworks() {
        return Homeworks;
    }

    public void setHomeworks(List<Homework> homeworks) {
        Homeworks = homeworks;
    }
}
