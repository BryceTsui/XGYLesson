package nju.server;

import nju.model.HomeworkResourse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by thinkpad on 2019/3/8.
 */
public interface FileService {

    public void uploadResourse(byte[] file,int courseid,String imgName);

    public void deleteResourse(int id);

    public void uploadHomeowrk(String studentname,int homeworkid,MultipartFile file);

    public String getHomeworkUrl(String studentname,int homeworkid,String filename);

    public String downloadAllHomework(int courseid,int homeowrkid);

    public List<HomeworkResourse> getHomeworkResourse(int homeworkid, String studentname);
}
