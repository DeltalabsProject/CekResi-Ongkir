package id.delta.cekresi.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.delta.cekresi.R;
import id.delta.cekresi.adapter.OngkosAdapter;
import id.delta.cekresi.adapter.RiwayatAdapter;
import id.delta.cekresi.realm.adapter.ResiAdapter;
import id.delta.cekresi.retrofit.api.ApiInterface;
import id.delta.cekresi.retrofit.models.ongkir.OngkirResponse;
import id.delta.cekresi.retrofit.models.ongkir.Ongko;
import id.delta.cekresi.retrofit.models.resi.Riwayat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 8/25/16.
 */
public class OngkirActivity extends AppCompatActivity {

    @Bind(R.id.input_berat)TextView inBerat; @Bind(R.id.input_dari)TextView  inDari; @Bind(R.id.input_ke)TextView inKe; @Bind(R.id.tombol_cek)Button btnCek;
    @Bind(R.id.spinner_layanan)Spinner spLayanan; @Bind(R.id.recyclerOngkir)RecyclerView recyclerOngkir; @Bind(R.id.tombol_cekulang)TextView btnCekUlang;
    @Bind(R.id.layout_input)CardView lyInput; @Bind(R.id.layout_hasil)CardView lyHasil;@Bind(R.id.title_hasil)TextView titHasil;@Bind(R.id.error)TextView error;
    String service;
    ProgressDialog pgDialog;
    OngkosAdapter adapter;
    List<Ongko> ongkosList = new ArrayList<>();
    String[] layanan = {"JNE", "TIKI"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ongkir);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        pgDialog = new ProgressDialog(this);
        pgDialog.setIndeterminate(true);
        pgDialog.setCancelable(true);
        pgDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Toast.makeText(OngkirActivity.this, "Proses telah dibatalkan", Toast.LENGTH_LONG).show();
            }
        });

        isiTampilan();
    }

    private void isiTampilan(){
        ArrayAdapter<String> adapters = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, layanan);
        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLayanan.setAdapter(adapters);
        spLayanan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        service = "jne";
                        break;
                    case 1:
                        service = "tiki";
                        Toast.makeText(OngkirActivity.this, "Maaf, layanan belum tersedia", Toast.LENGTH_LONG).show();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        setupRecycler();
        btnCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inBerat.getText().toString().equals("") || inBerat.getText().toString().equals("") || inBerat.getText().toString().equals("")){
                    Toast.makeText(OngkirActivity.this, "Harap isi data dengan benar", Toast.LENGTH_LONG).show();
                }else {
                    getData();
                }
            }
        });
        lyHasil.setVisibility(View.GONE);
    }

    private void setupRecycler(){
        recyclerOngkir.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new OngkosAdapter(this, ongkosList);
        recyclerOngkir.setAdapter(adapter);
    }

    private void getData(){
        pgDialog.setMessage("Sedang mengambil data...");
        pgDialog.show();
        final String dari = inDari.getText().toString();
        final String ke = inKe.getText().toString();
        final String berat = inBerat.getText().toString();

        ApiInterface apiInterface = ApiInterface.retrofit.create(ApiInterface.class);
        apiInterface.getOngkir(service,dari,ke,berat).enqueue(new Callback<OngkirResponse>() {
            @Override
            public void onResponse(Call<OngkirResponse> call, Response<OngkirResponse> response) {
                pgDialog.dismiss();
                ongkosList.clear();
                lyHasil.setVisibility(View.VISIBLE);
                OngkirResponse ongkirResponse = response.body();
                if(ongkirResponse.getStatus().equals("success")){
                    for (int i = 0; i <ongkirResponse.getOngkos().size(); i++){
                        Ongko ongkos = ongkirResponse.getOngkos().get(i);
                        ongkosList.add(ongkos);
                    }
                    error.setVisibility(View.GONE);
                    lyInput.setVisibility(View.GONE);
                    btnCekUlang.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            lyInput.setVisibility(View.VISIBLE);
                            lyHasil.setVisibility(View.GONE);
                            ongkosList.clear();
                        }
                    });
                }else {
                    lyInput.setVisibility(View.GONE);
                    error.setVisibility(View.VISIBLE);
                    error.setText(ongkirResponse.getMessage());
                 //   lyTitle.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onFailure(Call<OngkirResponse> call, Throwable t) {
                pgDialog.dismiss();
                if(t instanceof SocketTimeoutException) {
                   Toast.makeText(OngkirActivity.this, "Request Timeout. Please try again!", Toast.LENGTH_LONG).show();

                }else{
                    Toast.makeText(OngkirActivity.this, "Connection Error!", Toast.LENGTH_LONG).show();
                }
                Log.i("FAILURE", t.toString());
            }
        });

    }
}
