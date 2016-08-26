package id.delta.cekresi.realm.adapter;

import android.content.Context;

import id.delta.cekresi.realm.models.Resi;
import io.realm.RealmResults;

/**
 * Created by DELTA on 8/20/2016.
 */
public class RealmResiAdapter extends RealmModelAdapter<Resi>{

    public RealmResiAdapter (Context context, RealmResults<Resi> realmResults, boolean automaticUpdate){
        super(context, realmResults, automaticUpdate);
    }
}
