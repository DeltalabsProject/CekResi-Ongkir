package id.delta.cekresi.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.delta.cekresi.R;
import id.delta.cekresi.realm.adapter.RealmResiAdapter;
import id.delta.cekresi.realm.adapter.ResiAdapter;
import id.delta.cekresi.realm.control.ResiController;
import id.delta.cekresi.realm.models.Resi;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by DELTA on 8/20/2016.
 */
public class ResiActivity extends AppCompatActivity {
    @Bind(R.id.appbar_toolbar)Toolbar toolbar;
    @Bind(R.id.recyclerResi)RecyclerView recyclerView;
   // @Bind(R.id.dialog_resi)EditText addResi;
   // @Bind(R.id.tombol_simpan)Button btnSimpan;

    String[] layanan = {"JNE", "TIKI", "POS INDONESIA"};


    Realm realm;
    ResiAdapter adapter;
    LayoutInflater inflater;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resi);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        isiTampilan();

    }

    private void isiTampilan(){
        setupRecycler();
        this.realm = ResiController.with(this).getRealm();
        ResiController.with(this).refresh();
        setRealmAdapter(ResiController.with(this).getAllResi());
    }

    private void setupRecycler(){
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ResiAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    public void setRealmAdapter(RealmResults<Resi> resi){
        RealmResiAdapter realmResiAdapter = new RealmResiAdapter(this.getApplicationContext(), resi, true);
        adapter.setRealmAdapter(realmResiAdapter);
        adapter.notifyDataSetChanged();
    }

    private void hapusData(){
        ResiController.with(this).clearAll();
        setRealmAdapter(ResiController.with(this).getAllResi());
    }

    private void tambahData(){
        inflater = this.getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_tambah_data, null);
        final EditText addResi = (EditText)view.findViewById(R.id.dialog_resi) ;
        final Button btnSimpan = (Button)view.findViewById(R.id.tombol_simpan);
        final Spinner spinner = (Spinner)view.findViewById(R.id.dialog_spinner);



        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.show();

        final Resi resi = new Resi();
        resi.setId(ResiController.getInstance().getAllResi().size()+System.currentTimeMillis());

        ArrayAdapter<String> adapters = new ArrayAdapter<String>(ResiActivity.this, android.R.layout.simple_spinner_item, layanan);
        adapters.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapters);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        resi.setNama("jne");
                        break;
                    case 1:
                        resi.setNama("tiki");
                        break;
                    case 2:
                        resi.setNama("pos");
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resi.setResi(addResi.getText().toString());

                if (addResi.getText()==null || addResi.getText().toString().equals("") ){
                    Toast.makeText(ResiActivity.this, "Harap isi nomor resi Anda", Toast.LENGTH_LONG).show();
                }else {
                    realm.beginTransaction();
                    realm.copyToRealm(resi);
                    realm.commitTransaction();
                    adapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(ResiController.getInstance().getAllResi().size() - 1);
                    dialog.dismiss();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_resi, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            tambahData();
            return true;
        }

        if (id == R.id.action_clear){
            hapusData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this, MainActivity.class));
        this.finish();
    }
}
