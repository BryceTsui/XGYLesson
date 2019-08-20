package nju.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by thinkpad on 2019/3/6.
 */

@Entity
@Table(name = "selectedcourse")
public class SelectedCourse {


    @Id
    int id;
    int courseid;
    String studentname;

    double score_common=0;
    double score_exam=0;
    double score_all=0;


    public double getScore_common() {
        return score_common;
    }

    public void setScore_common(double score_common) {
        this.score_common = score_common;
    }

    public double getScore_exam() {
        return score_exam;
    }

    public void setScore_exam(double score_exam) {
        this.score_exam = score_exam;
    }

    public double getScore_all() {
        return score_all;
    }

    public void setScore_all(double score_all) {
        this.score_all = score_all;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }

    public String getStudentname() {
        return studentname;
    }

    public void setStudentname(String studentname) {
        this.studentname = studentname;
    }
}
