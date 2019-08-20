package nju.server;

import nju.vo.personalcenter.PersonInfo;

/**
 * Created by thinkpad on 2019/3/6.
 */
public interface PersonalCenterService {

    PersonInfo getStudentInfo(String studentName);


}
