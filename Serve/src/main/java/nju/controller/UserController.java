package nju.controller;

import nju.model.User;
import nju.server.MailService;
import nju.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by thinkpad on 2019/2/27.
 */


@Controller
@RequestMapping("/j2ee/user")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    MailService mailService;

    @ResponseBody
    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST
    )
    public User login(String name,String password){

        return userService.login(name,password);
    }

    @ResponseBody
    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public User register(@RequestBody User user){


        return userService.register(user);
    }
    
    public String getAvatorByName(String name){

        return userService.getAvatorByName(name);
    }

    @ResponseBody
    @RequestMapping(
            value = "/update",
            method = RequestMethod.POST,
            produces = {"application/json; charset=UTF-8"})
    public User updateUser(@RequestBody User user){

        return userService.update(user);
    }

    @ResponseBody
    @RequestMapping(
            value = "/sendemail",
            method = RequestMethod.POST
    )
    public String sendEmail(String address){
        return mailService.send(address);
    }

    @ResponseBody
    @RequestMapping(
            value = "/notactive",
            method = RequestMethod.POST
    )
    public void notActive(String name){
        userService.notActive(name);
    }
}
