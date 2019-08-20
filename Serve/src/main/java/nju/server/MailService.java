package nju.server;

import nju.model.User;

import java.util.List;

/**
 * Created by thinkpad on 2019/3/7.
 */
public interface MailService {

    public String send(String address);
    public void sendString(String address,String content,String title);

    public  void sendGroupMail(String content,String title, int courseid);
}
