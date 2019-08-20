package nju.server;

import nju.model.*;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.BufferedInputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 2019/2/25.
 */
public interface CourseService {

    List<SelectedCourse> getCourseIdByStudentName(String name);

    Course createCourse(Course course);
    public void publishCourse(int courseid);

    public List<Course> getAllCheckedCourse(String teachername);
    public List<Course> getAllPublishedCourseByTeacherName(String teachername);

    void uploadScore(BufferedInputStream in,int courseId,boolean ispublic);

    void copyCourse(int courseid,String term,String loation);

    void updateCourse(Course course);

    public List<User> getAllUserByCourseId(int id);

    public Map<String,List<Course>> getAllPublishedCourse();

    public Broadcast createBroadcast(int courseid, String title, String content, String type);

    public void createHomework( Homework homework);

}
