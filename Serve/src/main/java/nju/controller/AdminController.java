package nju.controller;

import nju.model.Course;
import nju.server.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by thinkpad on 2019/2/27.
 */
@Controller
@RequestMapping("/j2ee/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    AdminService adminService;

    @ResponseBody
    @RequestMapping(
            value = "/getuncheckcourse",
            method = RequestMethod.POST
    )
    public List<Course> getAllUncheckCourse(){

        //GET or POST
        return adminService.getAllUncheckCourse();
    }


    @ResponseBody
    @RequestMapping(
            value = "/getunpublishcourse",
            method = RequestMethod.POST
    )
    public List<Course> getAllUnpublishCourse(){
        return adminService.getAllUnpublishCourse();
    }

    @ResponseBody
    @RequestMapping(
            value = "/checkcourse",
            method = RequestMethod.POST
    )
    public void check(int courseid,int check){
        adminService.check(courseid, check);
    }

    @ResponseBody
    @RequestMapping(
            value = "/publishcourse",
            method = RequestMethod.POST
    )
    public void publish(int courseid,int publish){
        adminService.publish(courseid, publish);
    }

}
