package nju.dataServer.impl;

import nju.dataServer.CourseDataService;
import nju.datahelper.HibernateHelper;
import nju.model.Course;
import nju.model.Resourse;
import nju.model.SelectedCourse;
import nju.model.User;
import nju.server.CourseService;
import nju.utils.ResultMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 2019/3/6.
 */
public class CourseDataServiceImpl implements CourseDataService {

    HibernateHelper<Course> courseHibernateHelper = new HibernateHelper<>(Course.class);
    HibernateHelper<Resourse> resourseHibernateHelper = new HibernateHelper<>(Resourse.class);

    HibernateHelper<SelectedCourse> selectedCourseHibernateHelper = new HibernateHelper<>(SelectedCourse.class);
    HibernateHelper<User> userHibernateHelper = new HibernateHelper<>(User.class);
    public Course getCourseById(int id){

        return courseHibernateHelper.exactlyQuery("id",id);
    }

    public Course createCourse(Course course){
        ResultMessage resultMessage = courseHibernateHelper.save(course);

        System.out.println(course.getId());
        if(resultMessage==ResultMessage.SUCCESS){
            return course;
        }
        else return null;

    }

    public List<Resourse> getResourseByCourseId(int id){
        return  resourseHibernateHelper.fullMatchQuery("courseid",id);
    }

    public List<Course> getCourseByCheck(int check){
        return courseHibernateHelper.fullMatchQuery("cancheck",check);
    }

    public List<Course> getCourseByPublish(int publish){

        return courseHibernateHelper.fullMatchQuery("canpublish",publish);
    }
    public void updateCourse(Course course){

        courseHibernateHelper.update(course);
    }



    public List<User> getAllUserByCourseId(int id){
        List<SelectedCourse> selectedCourses = selectedCourseHibernateHelper.fullMatchQuery("courseid",id);

        List<User> result = new ArrayList<>();
        for(int i=0;i<=selectedCourses.size()-1;i++){
            String name = selectedCourses.get(i).getStudentname();
            User user = userHibernateHelper.exactlyQuery("name",name);

            result.add(user);
        }

        return result;
    }
    public Map<String,List<Course>> getPublishedCourseByTeacherName(String teachername){

        Map<String,List<Course>> result = new HashMap<>();

        List<Course> list = courseHibernateHelper.fullMatchQuery("teachername",teachername);

        for(Course c:list){

            if(c.getCanpublish()==2) {

                if (result.containsKey(c.getTerm())) {
                    result.get(c.getName()).add(c);
                } else {
                    List<Course> courses = new ArrayList<>();
                    courses.add(c);
                    result.put(c.getTerm(), courses);
                }
            }
        }

        return result;
    }

    public List<Course> getPublishedCourseListByTeachername(String teachername){
        List<Course> result = new ArrayList<>();

        List<Course> courses = courseHibernateHelper.fullMatchQuery("teachername",teachername);

        for(Course c:courses){
            if(c.getCanpublish()==2){
                result.add(c);
            }
        }

        return result;

    }



}

