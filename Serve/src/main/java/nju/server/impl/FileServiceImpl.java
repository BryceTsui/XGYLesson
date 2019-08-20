package nju.server.impl;

import nju.dataServer.CourseDataService;
import nju.dataServer.HomeworkDataService;
import nju.dataServer.ResourseDataService;
import nju.dataServer.impl.CourseDataServiceImpl;
import nju.dataServer.impl.HomeworkDataServiceImpl;
import nju.dataServer.impl.ResourseDataServiceImpl;
import nju.model.Course;
import nju.model.Homework;
import nju.model.HomeworkResourse;
import nju.model.Resourse;
import nju.server.FileService;
import nju.utils.TimeUtil;
import nju.utils.ZipHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by thinkpad on 2019/3/8.
 */
@Service
public class FileServiceImpl implements FileService {
    public final static String path = "E:\\Serve\\src\\main\\resources\\resourse\\";
    public final static String homeworkPath = "E:\\Serve\\src\\main\\resources\\homework\\";
    public final static String homeworkzipPath = "E:\\Serve\\src\\main\\resources\\homeworkzip\\";
    ResourseDataService resourseDataService = new ResourseDataServiceImpl();
    CourseDataService courseDataService = new CourseDataServiceImpl();
    HomeworkDataService homeworkDataService = new HomeworkDataServiceImpl();
    public void uploadResourse(byte[] file, int courseid, String fileName){
        try {
            System.out.println(fileName);

            int num = resourseDataService.getResourseNumByName(fileName);
            String appendex = "";
            if(num!=0){
                appendex = "("+num+")";

            }
            Resourse resourse = new Resourse();

            resourse.setCourseid(courseid);
            String[] arr = fileName.split("\\.");
            int index = fileName.lastIndexOf(".");
            String pureName = fileName.substring(0,index);

            fileName = pureName+appendex+"."+arr[arr.length-1];
            String type = arr[arr.length-1].toUpperCase();
            resourse.setName(fileName);
            resourse.setType(type);
            resourse.setUrl(path + courseid +"\\"+fileName);
            File targetFile = new File(path+ courseid);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            FileOutputStream out = new FileOutputStream(path + courseid +"\\"+fileName);
            out.write(file);
            out.flush();
            out.close();
            resourseDataService.addResourse(resourse);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteResourse(int id){

        Resourse resourse = resourseDataService.getResourseById(id);
        File file = new File(resourse.getUrl());
        file.delete();
        resourseDataService.deleteResourse(id);

    }

    public void uploadHomeowrk(String studentname,int homeworkid,MultipartFile file){

        if(resourseDataService.isHomeworkExist(studentname,homeworkid,file.getName())){
            return;
        }


        HomeworkResourse homeworkResourse = new HomeworkResourse();
        homeworkResourse.setFilename(file.getOriginalFilename());
        homeworkResourse.setHomeworkid(homeworkid);
        homeworkResourse.setStudentname(studentname);

        homeworkResourse.setTime(new TimeUtil().toString());
        Homework homework = homeworkDataService.getHomeworkById(homeworkResourse.getHomeworkid());

        Course course = courseDataService.getCourseById(homework.getCourseid());
        File targetFile = new File(homeworkPath);
        if (!targetFile.getParentFile().exists()) {

            targetFile.getParentFile().mkdirs();
        }

            if (!targetFile.exists()) {
            targetFile.mkdirs();
        }

        int courseId = course.getId();
        int homeworkId = homework.getId();

        try {
            File temp = new File(homeworkPath + courseId + "\\" + homeworkId+"\\"+studentname+"\\");

            if (!temp.getParentFile().exists()) {
                temp.getParentFile().mkdirs();
            }

            if(!temp.exists()){
                temp.mkdir();
            }
            FileOutputStream out = new FileOutputStream(homeworkPath + courseId + "\\" + homeworkId+"\\"+studentname+"\\"+file.getOriginalFilename());
            out.write(file.getBytes());
            out.flush();
            out.close();
            homeworkResourse.setUrl(homeworkPath + courseId + "\\" + homeworkId+"\\"+studentname+"\\"+file.getOriginalFilename());
            resourseDataService.addHomeworkResourse(homeworkResourse);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public String getHomeworkUrl(String studentname,int homeworkid,String filename){

        Homework homework = homeworkDataService.getHomeworkById(homeworkid);

        File dir = new File(homeworkPath+homework.getCourseid()+"\\" + homeworkid+"\\"+studentname+"\\"+filename);

        return dir.getPath();

    }


    public String downloadAllHomework(int courseid,int homeowrkid){
        String dirPath  = homeworkPath+courseid+"\\"+homeowrkid;

        String targetPath = homeworkzipPath;

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");//设置日期格式
        String date = df.format(new Date());
        String FileName = courseid+"_"+homeowrkid+"_"+date;


        try {

            ZipHelper.zip(dirPath, targetPath, FileName+".zip");
        }catch (Exception e){
            e.printStackTrace();
        }

        return targetPath+FileName+".zip";
    }

    public List<HomeworkResourse> getHomeworkResourse(int homeworkid,String studentname){

         return resourseDataService.getHomeworkResourse(homeworkid, studentname);


    }
}
