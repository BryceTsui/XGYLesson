package nju.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by thinkpad on 2019/3/8.
 */
@Entity
@Table(name = "homeworkresourse")
public class HomeworkResourse {

    @Id
    int id;
    int homeworkid;

    String studentname;
    String time;
    String filename;
    String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHomeworkid() {
        return homeworkid;
    }

    public void setHomeworkid(int homeworkid) {
        this.homeworkid = homeworkid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentnname) {
        this.studentname = studentnname;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
