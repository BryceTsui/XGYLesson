package nju.dataServer;

import nju.model.Course;
import nju.model.Resourse;
import nju.model.User;

import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 2019/2/25.
 */
public interface CourseDataService {



    Course getCourseById(int id);
    Course createCourse(Course course);

    public List<Resourse> getResourseByCourseId(int id);

    public List<Course> getCourseByCheck(int check);

    public List<Course> getCourseByPublish(int publish);

    void updateCourse(Course course);

    public List<User> getAllUserByCourseId(int id);

    public Map<String,List<Course>> getPublishedCourseByTeacherName(String teachername);

    public List<Course> getPublishedCourseListByTeachername(String teachername);
}
