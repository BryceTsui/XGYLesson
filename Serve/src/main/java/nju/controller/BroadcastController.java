package nju.controller;

import nju.model.Broadcast;
import nju.server.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by thinkpad on 2019/3/13.
 */
@Controller
@RequestMapping("/j2ee/broadcast")
@CrossOrigin
public class BroadcastController {
    @Autowired
    CourseService courseService;

    @ResponseBody
    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST
    )
    public Broadcast createBroadcast(int courseid, String title, String content, String type){

        return courseService.createBroadcast(courseid, title, content, type);
    }
}
