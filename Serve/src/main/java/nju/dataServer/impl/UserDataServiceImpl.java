package nju.dataServer.impl;

import nju.dataServer.UserDataService;
import nju.datahelper.HibernateHelper;
import nju.model.User;
import nju.utils.ResultMessage;

import java.util.List;

/**
 * Created by thinkpad on 2019/2/27.
 */
public class UserDataServiceImpl implements UserDataService {


    HibernateHelper<User> helper = new HibernateHelper<>(User.class);
    public User getUserByName(String name){

       return helper.exactlyQuery("name",name);
    }

    public ResultMessage insertUser(User user){

        System.out.println(user.getEmail());
        return helper.save(user);

    }

    public void updateUser(User user){
        helper.update(user);
    }

    public List<User> getUserByType(String type){

        return helper.fullMatchQuery("role",type);
    }

}
