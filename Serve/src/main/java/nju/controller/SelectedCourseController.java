package nju.controller;

import nju.server.SelectedCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by thinkpad on 2019/3/8.
 */


@Controller
@RequestMapping("/j2ee/selectedcourse")
@CrossOrigin
public class SelectedCourseController {
    @Autowired
    SelectedCourseService selectedCourseService;

    @ResponseBody
    @RequestMapping(
            value = "/quit",
            method = RequestMethod.POST
    )
    public void quitCourse(String name,int courseid){
        selectedCourseService.quitCourse(name,courseid);

    }

    @ResponseBody
    @RequestMapping(
            value = "/pick",
            method = RequestMethod.POST
    )
    public void pickCourse(int courseId,String studentName){
        selectedCourseService.pickCourse(courseId,studentName);
    }

}
