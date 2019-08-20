package nju.controller;

import nju.model.Course;
import nju.model.Homework;
import nju.model.User;
import nju.server.CourseService;
import nju.server.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 2019/2/27.
 */


@Controller
@RequestMapping("/j2ee/course")
@CrossOrigin
public class CourseController {

    @Autowired
    CourseService courseService;
    @Autowired
    MailService mailService;

    @ResponseBody
    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public Course createCourse(@RequestBody Course course){

        return courseService.createCourse(course);
    }

    @RequestMapping(value = "/checkedcourse")
    @ResponseBody
    public List<Course> getAllCheckedCourse(String teachername){
        return courseService.getAllCheckedCourse(teachername);
    }

    @RequestMapping(value = "/publishedcourse/teachername")
    @ResponseBody
    public List<Course> getAllPublishedCourseByTeacherName(String teachername){
        System.out.println("lalala");
        return courseService.getAllPublishedCourseByTeacherName(teachername);
    }

    @RequestMapping(value = "/publishedcourse/all")
    @ResponseBody
    public Map<String,List<Course>> getAllPublishedCourse(){
        return courseService.getAllPublishedCourse();
    }

    @RequestMapping(value = "/publishcourse")
    @ResponseBody
    public void publishCourse(int courseid){
        System.out.println("111");
        courseService.publishCourse(courseid);
    }

    @RequestMapping(value = "/uploadscore")
    @ResponseBody
    public void uploadScore(MultipartFile multipartFile,int courseid,boolean ispublic){


        System.out.println("upload score");
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(multipartFile.getInputStream());
            courseService.uploadScore(bufferedInputStream,courseid,ispublic);


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/copycourse")
    @ResponseBody
    public void copyCourse(int courseid,String term,String location){

        courseService.copyCourse(courseid,term,location);

    }

    @ResponseBody
    @RequestMapping(
            value = "/updatecourse",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public void updateCourse(@RequestBody Course course){
        courseService.updateCourse(course);
    }


    @RequestMapping(value = "/getalluser")
    @ResponseBody
    public List<User> getAllUserByCourseId(int id){

        return courseService.getAllUserByCourseId(id);
    }

    @RequestMapping(value = "/send/group")
    @ResponseBody
    public void sendGroupMail(String content,String title,int courseid){

        mailService.sendGroupMail(content,title,courseid);
    }


    @ResponseBody
    @RequestMapping(
            value = "/create/homework",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public void createHomework(@RequestBody Homework homework){
        courseService.createHomework(homework);
    }



}
