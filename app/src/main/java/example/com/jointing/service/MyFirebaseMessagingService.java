package example.com.jointing.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import example.com.jointing.InputWoActivity;
import example.com.jointing.MenuActivity;
import example.com.jointing.R;
import example.com.jointing.WoActivity;

/**
 * Created by pc on 29/12/2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        String message = "";
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getData().toString());
        try {
            JSONObject json = new JSONObject(remoteMessage.getData().toString());
            Log.e(TAG, "Notification JSON " + json.toString());
            message = json.getString("message");
            Log.e(TAG,message);
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.getMessage());
        }

        showNotification(message);
    }


    private void showNotification(String message){
        Intent i = new Intent(this, WoActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                        .setAutoCancel(true)
                        .setContentTitle("Tugas Baru")
                        .setContentText(message)
                    .setSmallIcon(R.drawable.icon_wo)
                        .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0,builder.build());
    }
}
