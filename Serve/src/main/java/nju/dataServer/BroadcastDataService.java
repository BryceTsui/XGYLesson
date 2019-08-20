package nju.dataServer;

import nju.model.Broadcast;

import java.util.List;

/**
 * Created by thinkpad on 2019/3/6.
 */
public interface BroadcastDataService {

    List<List<Broadcast>> getBroadcastByCourseId(int courseId);
    public Broadcast createBroadCast(Broadcast broadcast);

    public List<Broadcast> getBroadcastByType(String type);
}
