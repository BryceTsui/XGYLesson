package nju.dataServer.impl;

import nju.dataServer.SelectedCourseDataService;
import nju.datahelper.HibernateHelper;
import nju.model.Course;
import nju.model.Homework;
import nju.model.Resourse;
import nju.model.SelectedCourse;
import nju.utils.CriteriaClause;
import nju.utils.CriteriaClauseImpl;
import nju.utils.QueryMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 2019/3/6.
 */
public class SelectedCourseDataServiceImpl implements SelectedCourseDataService {

    HibernateHelper<SelectedCourse> selectedCourseHibernateHelper = new HibernateHelper<>(SelectedCourse.class);
    HibernateHelper<Course> courseHibernateHelper = new HibernateHelper<>(Course.class);
    HibernateHelper<Resourse> resourseHibernateHelper = new HibernateHelper<>(Resourse.class);
    HibernateHelper<Homework> homeworkHibernateHelper = new HibernateHelper<>(Homework.class);
    public List<SelectedCourse> getSelectedCourseIdByStudentName(String name){


        return selectedCourseHibernateHelper.fullMatchQuery("studentname",name);
    }

    public Map<String,List<Course>> getCourseByStudentName(String name){

        List<SelectedCourse> selectedCourses = selectedCourseHibernateHelper.fullMatchQuery("studentname",name);
        Map<String,List<Course>> map = new HashMap<>();

        for(int i=0;i<=selectedCourses.size()-1;i++){
            Course course = courseHibernateHelper.exactlyQuery("id",selectedCourses.get(i).getCourseid());
            if(map.containsKey(course.getTerm())){
                map.get(course.getTerm()).add(course);
            }
            else{
                List<Course> newList = new ArrayList<>();
                newList.add(course);
                map.put(course.getTerm(),newList);
            }
        }

        return map;
    }

    public SelectedCourse getSelectedCourseById(int id){

        return selectedCourseHibernateHelper.exactlyQuery("id",id);
    }



    public List<Homework> getHomeworkByCourseId(int id){
        return homeworkHibernateHelper.fullMatchQuery("courseid",id);
    }

    public void deleteSelectedCourse(int id){

        selectedCourseHibernateHelper.delete("id",id);
    }
    public SelectedCourse addSelectedCourse(SelectedCourse selectedCourse){
        selectedCourseHibernateHelper.save(selectedCourse);

        return selectedCourse;
    }

    public void updateScore(String name,int courseid,double score_common,double score_exam,double score_all){
        CriteriaClause criteriaClause1 = CriteriaClauseImpl.createSingleValueQuery("studentname",name,QueryMethod.Full);
        CriteriaClause criteriaClause2 = CriteriaClauseImpl.createSingleValueQuery("courseid",courseid,QueryMethod.Full);
        ArrayList<CriteriaClause> criteriaClauses = new ArrayList<>();
        criteriaClauses.add(criteriaClause1);
        criteriaClauses.add(criteriaClause2);

        List<SelectedCourse> selectedCourses = selectedCourseHibernateHelper.multiCriteriaQuery(criteriaClauses);

        if(selectedCourses.size()==0) return;

        SelectedCourse selectedCourse = selectedCourses.get(0);

        selectedCourse.setScore_common((double)(Math.round(score_common*100)/100));
        selectedCourse.setScore_exam((double)(Math.round(score_exam*100)/100));
        selectedCourse.setScore_all((double)(Math.round(score_all*100)/100));

        selectedCourseHibernateHelper.update(selectedCourse);
    }
    public SelectedCourse getSelectedCourseByNameAndCourseId(String name,int courseid){

        List<SelectedCourse> selectedCourses = selectedCourseHibernateHelper.fullMatchQuery("studentname",name);

        for(SelectedCourse s:selectedCourses){
            if(s.getCourseid()==courseid){
                return  s;
            }
        }
        return null;
    }

}
