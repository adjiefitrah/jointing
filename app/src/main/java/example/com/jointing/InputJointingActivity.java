package example.com.jointing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import id.zelory.compressor.Compressor;
import id.zelory.compressor.FileUtil;

public class InputJointingActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences pref;
    private Uri fileUri;

    private  final int FOTO_SEKITAR = 1;
    private  final int FOTO_SESUDAH = 2;
    private  final int FOTO_LABEL = 3;
    private  final int FOTO_EVIDEN = 4;

    private TextView txt_nama,txt_jabatan,txt_area,txt_no_wo,txt_koordinator_x,txt_koordinator_y,txt_foto_sekitar,txt_foto_sesudah,txt_foto_label,txt_foto_eviden;
    private EditText txt_no_seri,txt_nama_jointer;
    private Spinner spin_merk,spin_tipe,spin_konektor,spin_jenis_kabel;
    private Button btn_foto_sekitar,btn_foto_sesudah,btn_foto_label,btn_foto_eviden,btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_jointing);
        permission();
        iniset();
        setdata();
    }

    private void permission(){
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            //Toast.makeText(getApplicationContext().'PERMISI TIDAK DIIJINKAN',Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.CAMERA},1);
        }

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            //Toast.makeText(getApplicationContext().'PERMISI TIDAK DIIJINKAN',Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            //Toast.makeText(getApplicationContext().'PERMISI TIDAK DIIJINKAN',Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            //Toast.makeText(getApplicationContext().'PERMISI TIDAK DIIJINKAN',Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION},1);
        }

        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            //Toast.makeText(getApplicationContext().'PERMISI TIDAK DIIJINKAN',Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
    }

    private void iniset(){
        pref = getApplicationContext().getSharedPreferences("FILEINI", MODE_PRIVATE);

        txt_nama = (TextView)findViewById(R.id.txt_nama);
        txt_jabatan = (TextView)findViewById(R.id.txt_jabatan);
        txt_area = (TextView)findViewById(R.id.txt_area);
        txt_no_wo = (TextView)findViewById(R.id.txt_no_wo);
        txt_koordinator_x = (TextView)findViewById(R.id.txt_koordinat_x);
        txt_koordinator_y = (TextView)findViewById(R.id.txt_koordinat_y);
        txt_foto_sekitar = (TextView)findViewById(R.id.txt_foto_sekitar);
        txt_foto_sesudah = (TextView)findViewById(R.id.txt_foto_sesudah);
        txt_foto_label = (TextView)findViewById(R.id.txt_foto_label);
        txt_foto_eviden = (TextView)findViewById(R.id.txt_foto_eviden);

        txt_no_seri = (EditText) findViewById(R.id.txt_no_seri);
        txt_nama_jointer = (EditText) findViewById(R.id.txt_nama_jointer);

        spin_merk = (Spinner)findViewById(R.id.spin_merk);
        spin_tipe = (Spinner)findViewById(R.id.spin_tipe);
        spin_konektor = (Spinner)findViewById(R.id.spin_konektor);
        spin_jenis_kabel = (Spinner)findViewById(R.id.spin_jenis_kabel);

        btn_foto_sekitar = (Button)findViewById(R.id.btn_foto_sekitar);
        btn_foto_sesudah = (Button)findViewById(R.id.btn_foto_sesudah);
        btn_foto_label = (Button)findViewById(R.id.btn_foto_label);
        btn_foto_eviden = (Button)findViewById(R.id.btn_foto_eviden);
        btn_save = (Button)findViewById(R.id.btn_save);

        btn_foto_sekitar.setOnClickListener(this);
        btn_foto_sesudah.setOnClickListener(this);
        btn_foto_label.setOnClickListener(this);
        btn_foto_eviden.setOnClickListener(this);
        btn_save.setOnClickListener(this);
    }

    private void setdata(){
        final String no_wo = getIntent().getStringExtra("no_wo");

        txt_nama.setText(pref.getString("nama",""));
        txt_jabatan.setText(pref.getString("jabatan",""));
        txt_area.setText(pref.getString("area",""));
        txt_no_wo.setText(no_wo);
    }

    private void submit(){
        final String no_seri = txt_no_seri.getText().toString();
        final String nama_jointer = txt_nama_jointer.getText().toString();
        final String koordinat_x = txt_koordinator_x.getText().toString();
        final String koordinat_y = txt_koordinator_y.getText().toString();
        final String foto_sekitar = txt_foto_sekitar.getText().toString();
        final String foto_sesudah = txt_foto_sesudah.getText().toString();
        final String foto_label = txt_foto_label.getText().toString();
        final String foto_eviden = txt_foto_eviden.getText().toString();

        final String merk = spin_merk.getSelectedItem().toString();
        final String tipe = spin_tipe.getSelectedItem().toString();
        final String konektor = spin_konektor.getSelectedItem().toString();
        final String jenis_kabel = spin_jenis_kabel.getSelectedItem().toString();

        Intent home = new Intent();
        home.putExtra("no_seri",no_seri);
        home.putExtra("nama_jointer",nama_jointer);
        home.putExtra("koordinat_x",koordinat_x);
        home.putExtra("koordinat_y",koordinat_y);
        home.putExtra("foto_sekitar",foto_sekitar);
        home.putExtra("foto_sesudah",foto_sesudah);
        home.putExtra("foto_label",foto_label);
        home.putExtra("foto_eviden",foto_eviden);

        home.putExtra("merk",merk);
        home.putExtra("tipe",tipe);
        home.putExtra("konektor",konektor);
        home.putExtra("jenis_kabel",jenis_kabel);

        setResult(RESULT_OK,home);
        finish();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btn_foto_sekitar:
                ambil_foto("foto_sekitar.JPEG",FOTO_SEKITAR);
                break;
            case R.id.btn_foto_sesudah:
                ambil_foto("foto_sesudah.jpeg",FOTO_SESUDAH);
                break;
            case R.id.btn_foto_label:
                ambil_foto("foto_label.jpeg",FOTO_LABEL);
                break;
            case R.id.btn_foto_eviden:
                ambil_foto("foto_eviden.jpeg",FOTO_EVIDEN);
                break;
            case R.id.btn_save:
                submit();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String path = fileUri.getPath();
        if (requestCode == FOTO_SEKITAR && resultCode == RESULT_OK) {
            txt_foto_sekitar.setText(path);
            compress();
        }else if(requestCode == FOTO_SESUDAH && resultCode == RESULT_OK){
            txt_foto_sesudah.setText(path);
            compress();
        }else if(requestCode == FOTO_LABEL && resultCode == RESULT_OK){
            txt_foto_label.setText(path);
            compress();
        }else if(requestCode == FOTO_EVIDEN && resultCode == RESULT_OK){
            txt_foto_eviden.setText(path);
            compress();
        }
    }
    //----------------------------------------------------------------compress--------------------------------------------------
    private void galleryAddPic(Uri fileUri) {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        mediaScanIntent.setData(fileUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private void compress(){
        try {
            File img = FileUtil.from(this,fileUri);
            File compressImage = new Compressor.Builder(this)
                    .setMaxWidth(640)
                    .setMaxHeight(480)
                    .setQuality(90)
                    .setCompressFormat(Bitmap.CompressFormat.JPEG)
                    .setDestinationDirectoryPath(Environment.getExternalStorageDirectory().getPath().toString()+"/jointing")
                    .build()
                    .compressToFile(img);
            Log.e("COMPRES","11");
            galleryAddPic(Uri.fromFile(compressImage));
        }catch (IOException e) {
            //Toast.makeText(getApplicationContext(),"gagal",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
//----------------------------------------------------------------ambil foto--------------------------------------------------
    private void ambil_foto(String nama, int foto){
        fileUri = getOutputMediaFileUri(nama);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,fileUri);
        startActivityForResult(intent,foto);
    }

    private File getOuputMediaFile(String NamaFileFoto) {
        String root = Environment.getExternalStorageDirectory().getPath().toString();
        File myDir = new File(root + "/jointing");
        myDir.mkdirs();
        File mediaFile = new File(myDir.getPath(),NamaFileFoto);
        return mediaFile;
    }

    private Uri getOutputMediaFileUri(String NamaFileFoto) {
        if(Build.VERSION.SDK_INT >= 24 ){
            return  FileProvider.getUriForFile(InputJointingActivity.this,
                    BuildConfig.APPLICATION_ID +".provider",getOuputMediaFile(NamaFileFoto));
        }else{
            return Uri.fromFile(getOuputMediaFile(NamaFileFoto));
        }
    }

}
