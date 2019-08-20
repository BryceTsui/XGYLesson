package nju.server.impl;

import nju.dataServer.CourseDataService;
import nju.dataServer.SelectedCourseDataService;
import nju.dataServer.impl.CourseDataServiceImpl;
import nju.dataServer.impl.SelectedCourseDataServiceImpl;
import nju.model.Course;
import nju.model.SelectedCourse;
import nju.server.SelectedCourseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by thinkpad on 2019/3/8.
 */
@Service
public class SelectedCourseServiceImpl implements SelectedCourseService {

    SelectedCourseDataService selectedCourseDataService = new SelectedCourseDataServiceImpl();
    CourseDataService courseDataService = new CourseDataServiceImpl();
    public void quitCourse(String name,int courseid){

        List<SelectedCourse> selectedCourseList = selectedCourseDataService.getSelectedCourseIdByStudentName(name);

        for(SelectedCourse s:selectedCourseList){
            System.out.println(s.getCourseid());
            if(s.getCourseid()==courseid){

                selectedCourseDataService.deleteSelectedCourse(s.getId());
            }
        }

    }

    public SelectedCourse pickCourse(int courseid,String studentname){
        Course course = courseDataService.getCourseById(courseid);

        SelectedCourse selectedCourse = new SelectedCourse();
        selectedCourse.setCourseid(course.getId());
        selectedCourse.setStudentname(studentname);

        return selectedCourseDataService.addSelectedCourse(selectedCourse);
    }
}
