package nju.controller;

import nju.server.CourseDetailService;
import nju.vo.coursepage.CourseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by thinkpad on 2019/3/7.
 */
@Controller
@RequestMapping("/j2ee/coursedetail")
@CrossOrigin
public class CourseDetailController {

    @Autowired
    CourseDetailService courseDetailService;

    @ResponseBody
    @RequestMapping(
            value = "/getInfo",
            method = RequestMethod.POST
    )
    public CourseInfo getInfo(String username,int courseid){

        return courseDetailService.getInfo(username,courseid);
    }
}
