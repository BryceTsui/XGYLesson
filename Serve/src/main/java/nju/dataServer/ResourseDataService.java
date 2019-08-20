package nju.dataServer;

import nju.model.Homework;
import nju.model.HomeworkResourse;
import nju.model.Resourse;

import java.util.List;

/**
 * Created by thinkpad on 2019/3/8.
 */
public interface ResourseDataService {

    void addResourse(Resourse resourse);

    int getResourseNumByName(String name);

    public void deleteResourse(int id);

    Resourse getResourseById(int id);

    void addHomeworkResourse(HomeworkResourse resourse);

    boolean isHomeworkExist(String studentname,int homeworkid,String filename);

    List<HomeworkResourse> getHomeworkResourse(int homeworkid,String name);


}
