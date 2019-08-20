package nju.server.impl;

import nju.dataServer.BroadcastDataService;
import nju.dataServer.CourseDataService;
import nju.dataServer.HomeworkDataService;
import nju.dataServer.SelectedCourseDataService;
import nju.dataServer.impl.BroadcastDataServiceImpl;
import nju.dataServer.impl.CourseDataServiceImpl;
import nju.dataServer.impl.HomeworkDataServiceImpl;
import nju.dataServer.impl.SelectedCourseDataServiceImpl;
import nju.model.*;
import nju.server.CourseService;
import nju.server.FileService;
import nju.utils.ExcelOperator;
import nju.utils.TimeUtil;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by thinkpad on 2019/3/6.
 */
@Service
public class CourseServiceImpl implements CourseService {



    CourseDataService courseDataService = new CourseDataServiceImpl();
    SelectedCourseDataService selectedCourseDataService = new SelectedCourseDataServiceImpl();

    BroadcastDataService broadcastDataService = new BroadcastDataServiceImpl();

    HomeworkDataService homeworkDataService = new HomeworkDataServiceImpl();
    public List<SelectedCourse> getCourseIdByStudentName(String name){

        return null;
    }

    public Course createCourse(Course course){
        return  courseDataService.createCourse(course);
    }

    public void publishCourse(int courseid){
        Course course = courseDataService.getCourseById(courseid);

        course.setId(0);

        course.setCanpublish(1);


        courseDataService.createCourse(course);
        copyResourse(courseid,course.getId());
    }

    public List<Course> getAllCheckedCourse(String teachername){
        List<Course> courses = courseDataService.getCourseByCheck(1);

        //且发布=0

        List<Course> result = new ArrayList<>();

        for(Course c:courses){
            if(c.getTeachername().equals(teachername)&&c.getCanpublish()==0){
                result.add(c);
            }
        }
        return result;
    }
    public List<Course> getAllPublishedCourseByTeacherName(String teachername){
        List<Course> courses =courseDataService.getCourseByPublish(2);
        List<Course> result = new ArrayList<>();

        for(Course c:courses){
            if(c.getTeachername().equals(teachername)){
                result.add(c);
            }
        }
        return result;
    }
    public void uploadScore(BufferedInputStream in, int courseId,boolean ispublic){

        try {
            String[][] data = ExcelOperator.getData(in, 1);

            if(ispublic){
                String header = "<table style=\"border-collapse: collapse; width: 100%;\" border=\"1\">\n" ;


                StringBuffer sb= new StringBuffer(header);

                for(int i=0;i<=data.length-1;i++){

                    sb.append("<tr>\n");
                    for(int j=0;j<=3;j++) {

                        sb.append("<td style=\"width: 25%;\">");
                        sb.append(data[i][j]);
                        sb.append("</td>\n");
                    }
                    sb.append("</tr>\n");
                }
                sb.append("</tbody>\n");
                sb.append("</table>");

                String content = sb.toString();

                System.out.println(content);

                createBroadcast(courseId,"成绩公开",content,"score");
            }

            for(int i=0;i<=data.length-1;i++){

                    String name = data[i][0];

                    double score_common = (data[i][1].length()==0)?0:Double.parseDouble(data[i][1]);
                    double score_exam = (data[i][2].length()==0)?0:Double.parseDouble(data[i][2]);
                    double score_all = (data[i][3].length()==0)?0:Double.parseDouble(data[i][3]);


                    System.out.println(name+" "+score_common+" "+score_exam+" "+score_all );
                    selectedCourseDataService.updateScore(name,courseId,score_common,score_exam,score_all);

            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void copyCourse(int courseid,String term,String loation){
        Course sourceCourse = courseDataService.getCourseById(courseid);
        Course targerCourse = new Course();

        targerCourse.setCancheck(0);
        targerCourse.setClassroom(loation);
        targerCourse.setName(sourceCourse.getName());
        targerCourse.setTeachername(sourceCourse.getTeachername());

        courseDataService.createCourse(targerCourse);
    }

    public void updateCourse(Course course){

        courseDataService.updateCourse(course);
    }

    public List<User> getAllUserByCourseId(int id){

       return courseDataService.getAllUserByCourseId(id);
    }

    public Map<String,List<Course>> getAllPublishedCourse(){
        List<Course> courses = courseDataService.getCourseByPublish(2);
        Map<String,List<Course>> map = new HashMap<>();

        for(Course c:courses){
            if(map.containsKey(c.getTerm())){
                map.get(c.getTerm()).add(c);
            }
            else{
                List<Course> temp = new ArrayList<>();
                temp.add(c);
                map.put(c.getTerm(),temp);
            }
        }
        return map;
    }
    public Broadcast createBroadcast(int courseid,String title,String content,String type){
        Broadcast broadcast = new Broadcast();
        TimeUtil timeUtil = new TimeUtil();

        broadcast.setContent(content);
        broadcast.setCourseid(courseid);
        broadcast.setTime(timeUtil.toString());
        broadcast.setTitle(title);
        broadcast.setType(type);

        return broadcastDataService.createBroadCast(broadcast);

    }

    private void copyResourse(int sourseid ,int targetid){
        String path = FileServiceImpl.path;
        File targetdir = new File(path+targetid);
        if(!targetdir.exists()){
           targetdir.mkdir();
        }
        File soursedir = new File(path+sourseid);

        FileService fileService = new FileServiceImpl();
        File[] files = soursedir.listFiles();
        for(int i=0;i<=files.length-1;i++) {

            try {
                fileService.uploadResourse(toByteArray2(files[i].getPath()), targetid, files[i].getName());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static byte[] toByteArray2(String filename)throws IOException {

        File f = new File(filename);
        if(!f.exists()){
            throw new FileNotFoundException(filename);
        }

        FileChannel channel = null;
        FileInputStream fs = null;
        try{
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int)channel.size());
            while((channel.read(byteBuffer)) > 0){
                // do nothing
//              System.out.println("reading");
            }
            return byteBuffer.array();
        }catch (IOException e) {
            e.printStackTrace();
            throw e;
        }finally{
            try{
                channel.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
            try{
                fs.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void createHomework( Homework homework){


        homeworkDataService.save(homework);

    }

    public static void main(String[] args){

        CourseServiceImpl courseService = new CourseServiceImpl();

        courseService.publishCourse(1);
    }

}
