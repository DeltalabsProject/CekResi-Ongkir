package id.delta.cekresi.fragmen;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.delta.cekresi.R;
import id.delta.cekresi.activities.TrackingActivity;
import id.delta.cekresi.realm.control.ResiController;
import id.delta.cekresi.realm.models.Resi;
import id.delta.cekresi.retrofit.api.ApiInterface;
import id.delta.cekresi.retrofit.models.resi.Data;
import id.delta.cekresi.retrofit.models.resi.Detail;
import id.delta.cekresi.retrofit.models.resi.JneResponse;
import id.delta.cekresi.retrofit.models.resi.Riwayat;
import id.delta.cekresi.retrofit.models.resi.Tujuan;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 8/19/16.
 */
public class PosFragment extends Fragment {
    @Bind (R.id.input_resi)
    EditText inputResi;
    @Bind(R.id.tombol_cek)
    Button btnCek;
    @Bind(R.id.tombol_simpan)Button btnSimpan;
    @Bind(R.id.title_input)
    TextView titInput;

    @Bind(R.id.hs_noresi)
    TextView hsResi;
    @Bind(R.id.hs_service)
            TextView hsService;
    @Bind(R.id.hs_status)
            TextView hsStatus;
    @Bind(R.id.hs_tanggal)
            TextView hsTanggal;

    @Bind(R.id.nama_asal) TextView namaAsal; @Bind(R.id.alamat_asal)TextView alamatAsal; @Bind(R.id.nama_tujuan)TextView namaTujuan; @Bind(R.id.alamat_tujuan)TextView alamatTujuan;

    @Bind(R.id.tombol_tracking)Button btnTracking;
    @Bind(R.id.img_scan)ImageView imgScan;
    private String toast;
    ProgressDialog pgDialog;
    List<Riwayat> riwayatList = new ArrayList<>();
    private Realm realm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_tab, container, false);
        ButterKnife.bind(this, view);
        titInput.setText("Input Resi POS INDONESIA");

        String nomorResi = getActivity().getIntent().getStringExtra("pos");
        inputResi.setText(nomorResi);


        pgDialog = new ProgressDialog(view.getContext());
        pgDialog.setIndeterminate(true);
        pgDialog.setCancelable(true);
        pgDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Toast.makeText(getActivity(), "Proses telah dibatalkan", Toast.LENGTH_LONG).show();
            }
        });

        this.realm = ResiController.with(this).getRealm();

        btnCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });

        imgScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scanBarcode();
            }
        });

        return view;
    }

    public void scanBarcode() {
        IntentIntegrator.forSupportFragment(this).initiateScan();
    }

    private void displayToast() {
        if(getActivity() != null && toast != null) {
            Toast.makeText(getActivity(), toast, Toast.LENGTH_LONG).show();
            toast = null;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode, data);
        if(result !=null){
            if(result.getContents()==null){

                toast = "Scan nomor resi dibatalkan";
            } else {
                toast = "Nomor Resi Anda : " + result.getContents();
                inputResi.setText(result.getContents());
            }

            // At this point we may or may not have a reference to the activity
            displayToast();
        }
    }

    private void getData(){
        pgDialog.setMessage("Sedang mengambil data...");
        pgDialog.show();
        final String resi = inputResi.getText().toString();

        ApiInterface apiInterface = ApiInterface.retrofit.create(ApiInterface.class);
        apiInterface.getJNEResponse("pos",resi).enqueue(new Callback<JneResponse>() {
            @Override
            public void onResponse(Call<JneResponse> call, Response<JneResponse> response) {
                pgDialog.dismiss();
              //  riwayatList.clear();
                JneResponse jneResponse = response.body();

                if(jneResponse.getStatus().equals("success")){
                    final Data data = jneResponse.getData();
                    Detail detail = data.getDetail();
                    Tujuan tujuan = detail.getTujuan();

                    hsResi.setText(detail.getNoResi());
                    hsService.setText(detail.getService());
                    hsStatus.setText(detail.getStatus());
                    hsTanggal.setText(detail.getTanggal());


                    if (detail.getTujuan()==null){
                        namaTujuan.setText(" ");
                        alamatTujuan.setText(" ");
                    }else {
                        if (tujuan.getNama()==null){
                            namaTujuan.setText(" ");
                        }else {
                            namaTujuan.setText(tujuan.getNama());
                        }

                        if (tujuan.getAlamat() == null) {
                            alamatTujuan.setText(" ");
                        }else {
                            alamatTujuan.setText(tujuan.getAlamat());
                        }
                    }

                    if (detail.getAsal()==null){
                        namaAsal.setText(" ");
                        namaAsal.setText(" ");
                    }else {
                        namaAsal.setText(detail.getAsal().getNama());
                        alamatAsal.setText(detail.getAsal().getAlamat());
                    }

                    final Resi realmresi = new Resi();
                    realmresi.setId(ResiController.getInstance().getAllResi().size()+System.currentTimeMillis());
                    realmresi.setResi(data.getDetail().getNoResi());
                    realmresi.setNama(jneResponse.getQuery().getPengirim());
                    try{
                        btnSimpan.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                realm.beginTransaction();
                                realm.copyToRealm(realmresi);
                                realm.commitTransaction();
                            }
                        });
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    try{
                        btnTracking.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(getActivity(), TrackingActivity.class)
                                        .putExtra("data", data));

                            }
                        });
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                 //   adapter = new RiwayatAdapter(getContext(), riwayatList);
                 //   recyclerView.setAdapter(adapter);

                }else {
                    Snackbar.make(getView(), "Data Tidak Tersedia", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                 //   Toast.makeText(getActivity(), "No Data!", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<JneResponse> call, Throwable t) {
                if(t instanceof SocketTimeoutException) {
                    Snackbar.make(getView(), "Request Timeout. Please try again!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                 //   Toast.makeText(getActivity(), "Request Timeout. Please try again!", Toast.LENGTH_LONG).show();
                }else{
                    Snackbar.make(getView(), "Connection Error!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    // Toast.makeText(getActivity(), "Connection Error!", Toast.LENGTH_LONG).show();
                }
                Log.i("FAILURE", t.toString());
            }


        });
    }
}
