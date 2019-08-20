package nju.dataServer.impl;

import nju.dataServer.ResourseDataService;
import nju.datahelper.HibernateHelper;
import nju.model.HomeworkResourse;
import nju.model.Resourse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkpad on 2019/3/8.
 */
public class ResourseDataServiceImpl implements ResourseDataService {

    HibernateHelper<Resourse> resourseHibernateHelper = new HibernateHelper<>(Resourse.class);
    HibernateHelper<HomeworkResourse> homeworkResourseHibernateHelper = new HibernateHelper<>(HomeworkResourse.class);
    public void addResourse(Resourse resourse){
        resourseHibernateHelper.save(resourse);
    }

    public int getResourseNumByName(String name){

        return resourseHibernateHelper.fullMatchQuery("name",name).size();
    }

    public void deleteResourse(int id){
        resourseHibernateHelper.delete("id",id);
    }

    public Resourse getResourseById(int id){

        return resourseHibernateHelper.exactlyQuery("id",id);
    }

    public void addHomeworkResourse(HomeworkResourse resourse){
        homeworkResourseHibernateHelper.save(resourse);
    }

    public boolean isHomeworkExist(String studentname,int homeworkid,String filename){

        List<HomeworkResourse> list = homeworkResourseHibernateHelper.fullMatchQuery("studentname",studentname);

        for(HomeworkResourse h:list){

            if(h.getHomeworkid()==homeworkid&&h.getFilename().equals(filename)){
                return true;
            }
        }
        return false;
    }

    public List<HomeworkResourse> getHomeworkResourse(int homeworkid,String name){

        List<HomeworkResourse> homeworkResourses = homeworkResourseHibernateHelper.fullMatchQuery("studentname",name);

        List<HomeworkResourse> result = new ArrayList<>();
        for(HomeworkResourse h:homeworkResourses){

            if(h.getHomeworkid()==homeworkid){
                result.add(h);
            }
        }
        return result;
    }
}
