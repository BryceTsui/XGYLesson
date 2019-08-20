package nju.dataServer;

import nju.model.Homework;

/**
 * Created by thinkpad on 2019/3/9.
 */
public interface HomeworkDataService {

    Homework getHomeworkById(int id);


    Homework save(Homework homework);

}
