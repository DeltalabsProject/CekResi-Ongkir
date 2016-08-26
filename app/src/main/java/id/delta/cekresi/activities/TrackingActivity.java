package id.delta.cekresi.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.delta.cekresi.R;
import id.delta.cekresi.adapter.RiwayatAdapter;
import id.delta.cekresi.retrofit.models.resi.Data;
import id.delta.cekresi.retrofit.models.resi.Riwayat;

/**
 * Created by Administrator on 8/19/16.
 */
public class TrackingActivity extends AppCompatActivity {

    @Bind(R.id.recyclerRiwayat)RecyclerView recyclerView;
    List<Riwayat> riwayatList = new ArrayList<>();
    RiwayatAdapter adapter;
    Data data;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); ca-app-pub-7580328266607219/4779471282
        setContentView(R.layout.activity_tracking);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ButterKnife.bind(this);
        setupRecycler();
        data = (Data)getIntent().getSerializableExtra("data");
        getRiwayat();
    }

    private void getRiwayat(){
        for (int i = 0; i <data.getRiwayat().size(); i++){
            Riwayat riwayat = data.getRiwayat().get(i);
            riwayatList.add(riwayat);
        }
    }

    private void setupRecycler(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new RiwayatAdapter(this, riwayatList);
        recyclerView.setAdapter(adapter);
    }
}
