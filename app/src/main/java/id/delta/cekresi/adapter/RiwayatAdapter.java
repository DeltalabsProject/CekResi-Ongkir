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
import id.delta.cekresi.retrofit.models.resi.Riwayat;

/**
 * Created by Administrator on 8/19/16.
 */
public final class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.ViewHolder> {
    Context context;
    List<Riwayat> riwayatList = new ArrayList<>();

    public RiwayatAdapter(Context context, List<Riwayat> riwayatList){
        this.context = context;
        this.riwayatList = riwayatList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_riwayat, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Riwayat riwayat = riwayatList.get(position);
        holder.rwStatus.setText(riwayat.getStatus());
        holder.rwKeterangan.setText(riwayat.getKeterangan());
        holder.rwLokasi.setText(riwayat.getLokasi());
        holder.rwTanggal.setText(riwayat.getTaggal());
    }

    @Override
    public int getItemCount() {
        return riwayatList.size();
    }

    public static final class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.rw_keterangan)TextView rwKeterangan;@Bind(R.id.rw_lokasi)TextView rwLokasi; @Bind(R.id.rw_tanggal)TextView rwTanggal;
        @Bind(R.id.rw_status)TextView rwStatus;
      //  @Bind(R.id.rw_waktu)TextView rwWaktu;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
