package nju.dataServer;

import nju.model.Course;
import nju.model.Homework;
import nju.model.Resourse;
import nju.model.SelectedCourse;

import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 2019/3/6.
 */
public interface SelectedCourseDataService {

    List<SelectedCourse> getSelectedCourseIdByStudentName(String name);
    Map<String,List<Course>> getCourseByStudentName(String name);
    SelectedCourse getSelectedCourseById(int id);

    List<Homework> getHomeworkByCourseId(int id);

    void deleteSelectedCourse(int id);

    SelectedCourse addSelectedCourse(SelectedCourse selectedCourse);

    void updateScore(String name,int courseid,double score_common,double score_exam,double score_all);

    SelectedCourse getSelectedCourseByNameAndCourseId(String name,int courseid);
}
