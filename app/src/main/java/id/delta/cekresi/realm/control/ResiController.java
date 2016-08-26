package id.delta.cekresi.realm.control;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import id.delta.cekresi.realm.models.Resi;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by DELTA on 8/20/2016.
 */
public class ResiController {
    private static ResiController instance;
    private final Realm realm;

    public ResiController(Application application){
        realm = Realm.getDefaultInstance();
    }

    public static ResiController with (Fragment fragment){
        if(instance == null){
            instance = new ResiController(fragment.getActivity().getApplication());
        } return instance;
    }

    public static ResiController with (Activity activity){
        if(instance == null){
            instance = new ResiController(activity.getApplication());
        } return instance;
    }

    public static ResiController with (Application application){
        if(instance == null){
            instance = new ResiController(application);
        } return instance;
    }

    public static ResiController getInstance(){
        return instance;
    }

    public Realm getRealm(){
        return realm;
    }

    public void refresh (){
        realm.refresh();
    }

    public void clearAll(){
        realm.beginTransaction();
        realm.clear(Resi.class);
        realm.commitTransaction();
    }



    public RealmResults<Resi>getAllResi(){
        return realm.where(Resi.class).findAll();
    }

    public Resi getResi(String id){
        return realm.where(Resi.class).equalTo("id", id).findFirst();
    }

    public RealmResults<Resi>queryedResi(){
        return realm.where(Resi.class)
                .contains("resi","120417418697")
             //   .or()
             //   .contains("resi", "120417418697")
                .findAll();
    }


}
