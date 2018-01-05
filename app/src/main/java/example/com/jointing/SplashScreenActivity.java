package example.com.jointing;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {
    private SharedPreferences.Editor editor;
    private SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        pref = getApplicationContext().getSharedPreferences("FILEINI", MODE_PRIVATE);
        settimer();
    }

    private void settimer(){
        Thread logoTimer = new Thread() {
            public void run(){
                try{
                    int logoTimer = 0;
                    while(logoTimer < 3000){
                        sleep(100);
                        logoTimer = logoTimer +100;
                    }

                    String email = pref.getString("email", "");

                    if (email=="") {
                        Intent goToLogin = new Intent(getApplicationContext(),LoginActivity.class);
                        goToLogin.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(goToLogin);
                    }
                    else {
                        Intent gotomenu = new Intent(getApplicationContext(),MenuActivity.class);
                        gotomenu.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(gotomenu);
                    }

                }
                catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                finally{
                    finish();
                }
            }
        };
        logoTimer.start();
    }
}
