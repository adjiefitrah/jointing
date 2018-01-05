package example.com.jointing;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private AQuery aq;

    private SharedPreferences.Editor editor;
    private SharedPreferences pref;

    private String url_search_login;

    private EditText txt_username,txt_password;
    private Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = getApplicationContext().getSharedPreferences("FILEINI", MODE_PRIVATE);
        editor = pref.edit();

        url_search_login = "http://"+getResources().getString(R.string.ip_server)+"/api/email.php";

        txt_username = (EditText)findViewById(R.id.txt_username);
        txt_password = (EditText)findViewById(R.id.txt_password);
        btn_login = (Button)findViewById(R.id.btn_login);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.INTERNET) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.INTERNET},1);
        }

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
                //Toast.makeText(getApplicationContext(),"User tidak ditemukan",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void login(){
        aq = new AQuery(LoginActivity.this);
        ProgressDialog prodig = new ProgressDialog(LoginActivity.this);
        prodig.setMessage("Loading..");
        prodig.setCancelable(false);

        String email  = txt_username.getText().toString();
        String password  = txt_password.getText().toString();
        Log.e("URL",url_search_login+"?email="+email+"&password="+password);
        aq.progress(prodig).ajax(url_search_login+"?username="+email+"&password="+password,String.class,new AjaxCallback<String>(){
            @Override
            public void callback(String url, String object, AjaxStatus status) {

                if (object != null){
                    try {
                        JSONObject json = new JSONObject(object);
                        String status1 = json.getString("status");
                        if(status1.equals("1")){
                            JSONArray var_ray = json.getJSONArray("info");
                            for(int a=0; a< var_ray.length(); a++){
                                JSONObject b = var_ray.getJSONObject(a);
                                editor.putString("nama",b.getString("display_name"));
                                editor.putString("email",b.getString("mail"));
                                editor.putString("bidang",b.getString("department"));
                                editor.putString("area",b.getString("area"));
                                editor.commit();
                                //Toast.makeText(getApplicationContext(),b.getString("display_name"),Toast.LENGTH_SHORT).show();
                                Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                                Intent menu = new Intent(getApplicationContext(),MenuActivity.class);
                                menu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(menu);
                            }
                        }else{
                            Toast.makeText(getApplicationContext(),"User tidak ditemukan ",Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        Log.e("ERRORNYA==",e.getMessage());
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Koneksi Buruk !",Toast.LENGTH_SHORT).show();
                }
            }

        });

    }
}
