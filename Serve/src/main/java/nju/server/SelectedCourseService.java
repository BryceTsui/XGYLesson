package nju.server;

import nju.model.SelectedCourse;

/**
 * Created by thinkpad on 2019/3/8.
 */
public interface SelectedCourseService {

    public void quitCourse(String name,int courseid);

    public SelectedCourse pickCourse(int courseid,String studentname);
}
