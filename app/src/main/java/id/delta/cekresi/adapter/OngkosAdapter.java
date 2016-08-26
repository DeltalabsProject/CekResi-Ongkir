package id.delta.cekresi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.delta.cekresi.R;
import id.delta.cekresi.retrofit.models.ongkir.Ongko;
import id.delta.cekresi.retrofit.models.resi.Riwayat;

/**
 * Created by Administrator on 8/19/16.
 */
public final class OngkosAdapter extends RecyclerView.Adapter<OngkosAdapter.ViewHolder> {
    Context context;
    List<Ongko> ongkosList = new ArrayList<>();

    public OngkosAdapter(Context context, List<Ongko> ongkosList){
        this.context = context;
        this.ongkosList = ongkosList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ongkos, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Ongko ongkos = ongkosList.get(position);
        holder.itEtd.setText(ongkos.getEtd());
        holder.itKiriman.setText(ongkos.getKiriman());
        holder.itLayanan.setText(ongkos.getLayanan());
        holder.itTarif.setText(ongkos.getTarif());
    }

    @Override
    public int getItemCount() {
        return ongkosList.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.item_layanan)TextView itLayanan;@Bind(R.id.item_etd)TextView itEtd; @Bind(R.id.item_kiriman)TextView itKiriman;
        @Bind(R.id.item_tarif)TextView itTarif;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
