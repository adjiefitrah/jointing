package example.com.jointing;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import example.com.jointing.adapter.WoAdapter;

public class WoActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private ArrayList<String> wo;

    private TextView txt_nama,txt_jabatan,txt_area;
    private RecyclerView rv_wo;
    private RecyclerView.LayoutManager layoutManager;
    private WoAdapter woAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wo);
        iniset();
        setdata();

        woAdapter = new WoAdapter(getApplicationContext(),wo);
        layoutManager = new LinearLayoutManager(this);
        rv_wo.setLayoutManager(layoutManager);
        rv_wo.setAdapter(woAdapter);
    }

    private void iniset(){
        pref = getApplicationContext().getSharedPreferences("FILEINI", MODE_PRIVATE);
        wo = new ArrayList<>();

        txt_nama = (TextView)findViewById(R.id.txt_nama);
        txt_jabatan = (TextView)findViewById(R.id.txt_jabatan);
        txt_area = (TextView)findViewById(R.id.txt_area);
        rv_wo = (RecyclerView)findViewById(R.id.rv_wo);
    }

    private void setdata(){
        txt_nama.setText(pref.getString("nama",""));
        txt_jabatan.setText(pref.getString("jabatan",""));
        txt_area.setText(pref.getString("area",""));

        wo.add("WO DIS 54010001");
        wo.add("WO DIS 54010002");
        wo.add("WO DIS 54010003");
        wo.add("WO DIS 54010004");
    }
}
