package id.delta.cekresi.realm.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.delta.cekresi.R;
import id.delta.cekresi.activities.MainActivity;
import id.delta.cekresi.realm.control.ResiController;
import id.delta.cekresi.realm.models.Resi;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by DELTA on 8/20/2016.
 */
public class ResiAdapter extends RealmRecyclerViewAdapter<Resi>{

    private Realm realm;
    final Context context;
    private LayoutInflater inflater;

    private List<Resi> items = new ArrayList<>();

    public ResiAdapter(Context context){
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.item_nama)TextView itemNama;@Bind(R.id.item_resi)TextView itemResi;
        @Bind(R.id.item_delete)ImageView itemDelete;
        @Bind(R.id.item_holder)LinearLayout linearLayout;
        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resi_tersimpan, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        realm = ResiController.getInstance().getRealm();
        final Resi resi = getItem(position);
        final String nomorResi = resi.getResi();
        final String nama = resi.getNama();
        final ViewHolder viewHolder = (ViewHolder)holder;

        viewHolder.itemNama.setText(nama);
        viewHolder.itemResi.setText(nomorResi);
        viewHolder.itemDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RealmResults<Resi>results = realm.where(Resi.class).findAll();
                Resi r = results.get(position);
                final String resi = r.getResi();
                realm.beginTransaction();
                results.remove(position);
                realm.commitTransaction();
                notifyDataSetChanged();

                Snackbar.make(view, "Data " + resi + " Telah Dihapus", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, MainActivity.class);

                if (nama.equals("jne")){
                    intent.putExtra("jne" ,nomorResi);
                }
                if (nama.equals("tiki")){
                    intent.putExtra("tiki" ,nomorResi);
                }
                if (nama.equals("pos")){
                    intent.putExtra("pos" ,nomorResi);
                }
                ((Activity)context).finish();
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        if(getRealmAdapter()!=null){
            return getRealmAdapter().getCount();
        }
        return 0;
    }
}
