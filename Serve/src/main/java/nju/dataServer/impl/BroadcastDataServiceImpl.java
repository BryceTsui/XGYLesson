package nju.dataServer.impl;

import nju.dataServer.BroadcastDataService;
import nju.datahelper.HibernateHelper;
import nju.model.Broadcast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkpad on 2019/3/6.
 */
public class BroadcastDataServiceImpl implements BroadcastDataService {

    HibernateHelper<Broadcast> broadcastHibernateHelper = new HibernateHelper<>(Broadcast.class);


    public List<List<Broadcast>> getBroadcastByCourseId(int courseId){
        List<Broadcast> broadcasts = broadcastHibernateHelper.fullMatchQuery("courseid", courseId);

        List<List<Broadcast>> result = new ArrayList<>();

        for(int i=1;i<=4;i++) {
            result.add(new ArrayList<>());
        }



        for(int i=0;i<=broadcasts.size()-1;i++){

            Broadcast temp = broadcasts.get(i);
            if(temp.getType().equals("score")){

                result.get(0).add(temp);
            }
            else if(temp.getType().equals("experiment")){

                result.get(1).add(temp);
            }
            else if(temp.getType().equals("homework")){

                result.get(2).add(temp);
            }
            else{
                result.get(3).add(temp);
            }

        }


        return result;
    }

    public Broadcast createBroadCast(Broadcast broadcast){

        broadcastHibernateHelper.save(broadcast);

        return broadcast;
    }

    public List<Broadcast> getBroadcastByType(String type){

        return broadcastHibernateHelper.fullMatchQuery("type",type);
    }
}
