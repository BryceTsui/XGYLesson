package nju.server;

import nju.vo.coursepage.CourseInfo;

/**
 * Created by thinkpad on 2019/3/6.
 */
public interface CourseDetailService {

    CourseInfo getInfo(String username,int selectedCourseId);
}
