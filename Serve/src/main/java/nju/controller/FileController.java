package nju.controller;

import nju.model.HomeworkResourse;
import nju.server.FileService;
import nju.server.impl.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by thinkpad on 2019/2/27.
 */

@Controller
@RequestMapping("/j2ee/file")
@CrossOrigin
public class FileController {

    @Autowired
    FileService fileService;

    @RequestMapping(value="/download")//请求路径
    public void downloadResource( int courseid,String resoursename,HttpServletResponse response) {

            String dataDirectory = FileServiceImpl.path+courseid;//文件所在目录

            Path file = Paths.get(dataDirectory, resoursename);//文件对象

            if (Files.exists(file)) {

                response.setContentType("application/x-gzip");

                try {

                    response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(resoursename, "UTF-8"));


                    Files.copy(file, response.getOutputStream());//以输出流的形式对外输出提供下载

                } catch (IOException ex) {

                    ex.printStackTrace();

                }

            }

        }

    @RequestMapping(value = "/upload")
    @ResponseBody
    public void uploadResourse(MultipartFile[] multipartFiles, int courseid){


        for (MultipartFile f : multipartFiles) {
            try {
                String imgName = f.getOriginalFilename();//这里图片还是以原名命名
              //  String imgName = f.getName();
                System.out.println(imgName);
                String[] arr = imgName.split("\\\\");
                System.out.println(arr[arr.length-1]);
                //上传文件
                fileService.uploadResourse(f.getBytes(),courseid,arr[arr.length-1]);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public void deleteResourse(int id){

        fileService.deleteResourse(id);
    }

    @RequestMapping(value = "/homework/upload")
    @ResponseBody
    public void uploadHomework(String studentname,int homeworkid,MultipartFile[] file){

        for(int i=0;i<=file.length-1;i++)
        fileService.uploadHomeowrk(studentname,homeworkid,file[i]);
    }

    @RequestMapping(value="/homework/download")
    @ResponseBody
    public void DownloadHomework(String studentname,int homeworkid,String name,HttpServletResponse response){

        String path = fileService.getHomeworkUrl(studentname, homeworkid,name);

        Path file = Paths.get(path);//文件对象
        String filename = new File(path).getName();

        if (Files.exists(file)) {

            response.setContentType("application/x-gzip");

            try {

                response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));


                Files.copy(file, response.getOutputStream());//以输出流的形式对外输出提供下载

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

    }

    @RequestMapping(value = "/homework/downlaodall")
    @ResponseBody
    public void downloadAllHomework(int courseid,int homeworkid,HttpServletResponse response){


        System.out.println("gethere");
        String url = fileService.downloadAllHomework(courseid,homeworkid);
        System.out.println(url);
        Path file = Paths.get(url);//文件对象
        String filename = new File(url).getName();

        if (Files.exists(file)) {
            System.out.println("yes");

            response.setContentType("application/x-gzip");

            try {

                response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));


                Files.copy(file, response.getOutputStream());//以输出流的形式对外输出提供下载

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }


    }


    @RequestMapping(value = "/homework/resourse")
    @ResponseBody
    public List<HomeworkResourse> getHomeworkResourse(int homeworkid, String studentname){

        System.out.println(homeworkid+" "+studentname);
        return fileService.getHomeworkResourse(homeworkid, studentname);
    }



}
