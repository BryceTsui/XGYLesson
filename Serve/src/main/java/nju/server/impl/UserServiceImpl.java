package nju.server.impl;

import nju.dataServer.UserDataService;
import nju.dataServer.impl.UserDataServiceImpl;
import nju.model.User;
import nju.server.UserService;
import nju.utils.ResultMessage;
import org.springframework.stereotype.Service;

/**
 * Created by thinkpad on 2019/3/4.
 */
@Service
public class UserServiceImpl implements UserService {

    UserDataService userDataService = new UserDataServiceImpl();
    public User login(String name, String password){

        System.out.println(name);
        User user = userDataService.getUserByName(name);


        if(user==null) return null;
        if(password.equals(user.getPassword())&&user.getActive()==0) return user;
        else return null;
    }

    public User register(User user){

        if(ResultMessage.SUCCESS==userDataService.insertUser(user)) return user;
        else return null;

    }

    public User getUserByName(String name){

        return null;
    }


    public String  getAvatorByName(String name){

        User user = userDataService.getUserByName(name);
        if(user==null) return "";
        else return user.getAvator();
    }

    public void notActive(String name){
        User user = userDataService.getUserByName(name);
        user.setActive(1);
        userDataService.updateUser(user);

    }

    public User update(User user){
        userDataService.updateUser(user);
        return user;
    }
}
