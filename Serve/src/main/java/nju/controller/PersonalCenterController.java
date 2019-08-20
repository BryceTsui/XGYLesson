package nju.controller;

import nju.server.PersonalCenterService;
import nju.vo.personalcenter.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by thinkpad on 2019/3/6.
 */

@Controller
@RequestMapping("/j2ee/personalcenter")
@CrossOrigin
public class PersonalCenterController {


    @Autowired
    PersonalCenterService personalCenterService;

    @ResponseBody
    @RequestMapping(
            value = "/getInfo",
            method = RequestMethod.POST
    )
    public PersonInfo getInfo(String name){

        return personalCenterService.getStudentInfo(name);
    }
}
