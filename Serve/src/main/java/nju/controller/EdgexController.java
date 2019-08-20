package nju.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by thinkpad on 2019/6/26.
 */
@Controller
@RequestMapping("/thinkpad")
@CrossOrigin
public class EdgexController {

    @PutMapping(value = "/warning" )
    @ResponseBody
    public String uploadResourse( String gz){

        System.out.println(gz);
        return "2";

    }
}
