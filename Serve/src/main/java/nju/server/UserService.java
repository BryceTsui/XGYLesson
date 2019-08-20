package nju.server;

import nju.model.User;

/**
 * Created by thinkpad on 2019/2/25.
 */

public interface UserService {
    public User login(String name, String password);

    public User register(User user);

    public User getUserByName(String name);

    public String  getAvatorByName(String name);

    public void notActive(String name);

    public User update(User user);
}
