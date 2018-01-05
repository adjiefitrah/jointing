package example.com.jointing;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

import example.com.jointing.entity.WoEntity;

public class InputWoActivity extends AppCompatActivity {
    private static final int JOINTING = 1;
    private String no_wo,sktm;
    private ArrayList<WoEntity> arr;

    private SharedPreferences pref;

    private TextView txt_nama, txt_jabatan, txt_area, txt_no_wo;
    private Spinner spin_sktm;
    private Button btn_next;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_wo);
        iniset();
        setdata();
    }

    private void iniset() {
        pref = getApplicationContext().getSharedPreferences("FILEINI", MODE_PRIVATE);
        arr = new ArrayList<WoEntity>();

        txt_nama = (TextView) findViewById(R.id.txt_nama);
        txt_jabatan = (TextView) findViewById(R.id.txt_jabatan);
        txt_area = (TextView) findViewById(R.id.txt_area);
        txt_no_wo = (TextView) findViewById(R.id.txt_no_wo);
        spin_sktm = (Spinner) findViewById(R.id.spin_sktm);
        btn_next = (Button) findViewById(R.id.btn_next);
    }

    private void setdata() {
        no_wo = getIntent().getStringExtra("no_wo");
        sktm = spin_sktm.getSelectedItem().toString();

        txt_nama.setText(pref.getString("nama", ""));
        txt_jabatan.setText(pref.getString("jabatan", ""));
        txt_area.setText(pref.getString("area", ""));
        txt_no_wo.setText(no_wo);

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goInputJointing();
            }
        });
    }

    private void goInputJointing(){
        sktm = spin_sktm.getSelectedItem().toString();

        Intent goInputJointing = new Intent(getApplicationContext(), InputJointingActivity.class);
        goInputJointing.putExtra("no_wo", no_wo + " / " + sktm + " / Gardu");
        startActivityForResult(goInputJointing, JOINTING);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case JOINTING:
                    final String no_seri = data.getStringExtra("no_seri");
                    final String nama_jointer = data.getStringExtra("nama_jointer");
                    final String koordinat_x = data.getStringExtra("koordinat_x");
                    final String koordinat_y = data.getStringExtra("koordinat_y");
                    final String foto_sekitar = data.getStringExtra("foto_sekitar");
                    final String foto_sesudah = data.getStringExtra("foto_sesudah");
                    final String foto_label = data.getStringExtra("foto_label");
                    final String foto_eviden = data.getStringExtra("foto_eviden");

                    final String merk = data.getStringExtra("merk");
                    final String tipe = data.getStringExtra("tipe");
                    final String konektor = data.getStringExtra("konektor");
                    final String jenis_kabel = data.getStringExtra("jenis_kabel");
                    arr.add(new WoEntity(no_seri,nama_jointer,koordinat_x,koordinat_y,foto_sekitar,foto_sesudah,foto_label,foto_eviden,merk,tipe,konektor,jenis_kabel));

                    alert();
                    break;

            }
        }

    }

    private void alert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Data berhasil disimpan, Anda mau Input Jointing lagi ?");
        builder.setTitle("Sukses");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                goInputJointing();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                for(int i=0; i<arr.size();i++){
                    Toast.makeText(getApplicationContext(),arr.get(i).getMerk(),Toast.LENGTH_SHORT).show();
                }
            }
        });
        AlertDialog alert = builder.create();
        alert.setIcon(android.R.drawable.ic_menu_add);
        alert.show();
    }
}
