package nju.controller;

import nju.server.AdminService;
import nju.vo.chart.GPAItem;
import nju.vo.chart.TopicPercent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by thinkpad on 2019/3/18.
 */

@Controller
@RequestMapping("/j2ee/chart")
@CrossOrigin
public class ChartController {

    @Autowired
    AdminService adminService;
    //admin

    @RequestMapping(value = "/get/studentpercent")
    @ResponseBody
    public double getStudentPercent(){


        return adminService.getStudentPercent()*100;
    }

    @RequestMapping(value = "/get/topicpercent/all")
    @ResponseBody
    public List<TopicPercent> getAllTopicPercent() {

        return adminService.getAllTopicPercent();
    }

    @RequestMapping(value = "/get/broadcastpercent/all")
    @ResponseBody
    public List<Integer> getAllBroadcast(){

        return adminService.getAllBroadcast();
    }

    @RequestMapping(value = "/get/gpa/all")
    @ResponseBody
    public List<List<Object[]>>  getAllGPA(){

        return adminService.getAllGPA();
    }

    //student


    //teacher


}
