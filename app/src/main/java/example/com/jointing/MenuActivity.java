package example.com.jointing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import example.com.jointing.adapter.MenuAdapter;

public class MenuActivity extends AppCompatActivity {
    private SharedPreferences pref;
    private AQuery aq;

    private String[] list = {"WO","JOINTING","USER","LOGOUT"};
    private String url_token;

    private int[] img = {
           R.drawable.icon_wo,
           R.drawable.icon_jointing,
           R.drawable.icon_user,
            android.R.drawable.ic_delete
    };

    private MenuAdapter menuAdapter;
    private GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        pref = getApplicationContext().getSharedPreferences("FILEINI", MODE_PRIVATE);
        url_token = "http://"+getResources().getString(R.string.ip_server)+"/jointing/joinmo_token.php";

        gridView = (GridView)findViewById(R.id.gridview);
        menuAdapter = new MenuAdapter(this,list,img);

        showmenu();
        sendinternet();
    }

    private void showmenu(){
        gridView.setAdapter(menuAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0:
                        Intent wo = new Intent(getApplicationContext(),WoActivity.class);
                        wo.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(wo);
                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                }
            }
        });
    }

    private void sendinternet(){
        String token = pref.getString("token","");
        String email = pref.getString("email","");

        Log.e("token",token);
        if(token != ""){
            //Toast.makeText(getApplicationContext(),token,Toast.LENGTH_SHORT).show();
            aq = new AQuery(this);
            aq.ajax(url_token+"?token="+token+"&email="+email,String.class,new AjaxCallback<String>(){
                @Override
                public void callback(String url, String object, AjaxStatus status) {
                    if (object != null) {
                        Log.e("result",object);
                    }
                }
            });
        }
    }

}
