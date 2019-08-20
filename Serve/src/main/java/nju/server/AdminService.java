package nju.server;

import nju.model.Course;
import nju.vo.chart.GPAItem;
import nju.vo.chart.TopicPercent;

import java.util.List;

/**
 * Created by thinkpad on 2019/2/25.
 */
public interface AdminService {

    public List<Course> getAllUncheckCourse();

    public void check(int courseid,int check);

    public List<Course> getAllUnpublishCourse();
    public void publish(int courseid,int publish);

    public double getStudentPercent();


    public List<TopicPercent> getAllTopicPercent();

    public List<Integer> getAllBroadcast();

    public List<List<Object[]>>  getAllGPA();
}
