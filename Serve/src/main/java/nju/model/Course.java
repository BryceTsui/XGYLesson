package nju.model;

import javax.persistence.*;

/**
 * Created by thinkpad on 2019/2/25.
 */

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String teachername;
    String classroom;
    String term;

    String starttime;
    String endtime;
    String time;


    //0是未审批，1是审批通过，2是审批不通过
    int cancheck =0;

    //0是未发布，1是发布请求，2是审批通过已经发布，3是审批未通过
    int canpublish =0;

    int studentnum;
    int creditnum;


    public int getStudentnum() {
        return studentnum;
    }

    public void setStudentnum(int studentnum) {
        this.studentnum = studentnum;
    }

    public int getCreditnum() {
        return creditnum;
    }

    public void setCreditnum(int creditnum) {
        this.creditnum = creditnum;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCanpublish() {
        return canpublish;
    }

    public void setCanpublish(int publish) {
        this.canpublish = publish;
    }

    public int getCancheck() {
        return cancheck;
    }

    public void setCancheck(int check) {
        this.cancheck = check;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}
