package nju.dataServer;

import nju.model.User;
import nju.utils.ResultMessage;

import java.util.List;

/**
 * Created by thinkpad on 2019/2/25.
 */
public interface UserDataService {

    public User getUserByName(String name);


    public ResultMessage insertUser(User user);

    public void updateUser(User user);

    public List<User> getUserByType(String type);

}
