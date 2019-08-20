package nju.dataServer.impl;

import nju.dataServer.HomeworkDataService;
import nju.datahelper.HibernateHelper;
import nju.model.Homework;

/**
 * Created by thinkpad on 2019/3/9.
 */
public class HomeworkDataServiceImpl implements HomeworkDataService {

    HibernateHelper<Homework> homeworkHibernateHelper = new HibernateHelper<>(Homework.class);
    public Homework getHomeworkById(int id){

        return homeworkHibernateHelper.exactlyQuery("id",id);
    }

    public Homework save(Homework homework){
        homeworkHibernateHelper.save(homework);

        return homework;

    }


}
