package com.inbm.inbmstarter.inbm.firebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.google.firebase.messaging.RemoteMessage;
import com.inbm.inbmstarter.MainActivity;
import com.inbm.inbmstarter.R;
import com.inbm.inbmstarter.inbm.App;
import com.inbm.inbmstarter.inbm._gson;
import com.inbm.inbmstarter.inbm._log;
import com.inbm.inbmstarter.inbm._shared;

import org.json.JSONObject;

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    private static final String TAG = "FirebaseMsgService";
    private static String CHANNEL_ID = "channel-01";
    private int count = 0;
    private int NOTIFICATION_ID = 0;

    public static boolean notificationEnabled = false;

    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//        createNotificationChannel();
        _log.e(String.valueOf(remoteMessage));
    }

    /**
     * Notification Channel By Version O++..
     */
    public static void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "채팅 메세지";
            String description = "채팅 메세지 알림 설정부분 입니다.";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = App.getStaticContext().getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
                _log.e("createNotiChannel!!");
            }
        }
    }

    /**
     * Norification Create
     *
     * @param title
     * @param notification
     */
    private void sendDataMessage(Context context, String title, final _notification_ notification) {
        // Listener 가 살아있을 떄 안옴
        NotificationManager mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notifyIntent = new Intent(context, MainActivity.class);
        notifyIntent.putExtra("chat_room_id", notification.getChat_room_id());
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

//        노티피케이션
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_add)
                .setContentTitle(title)
//                .setWhen(notification.getSend_time())
                .setGroup(notification.getChat_room_id())
                .setStyle(new NotificationCompat.BigTextStyle().bigText(notification.getMessage()))
//                .setContentText(notification.getMultiMessage()+"12341234")
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_SOUND | NotificationCompat.DEFAULT_VIBRATE);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(notifyIntent);

        PendingIntent contentIntent = stackBuilder.getPendingIntent(
                0,
                PendingIntent.FLAG_ONE_SHOT
        );

        mBuilder.setContentIntent(contentIntent);
        mBuilder.setPriority(NotificationCompat.PRIORITY_MAX);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            mBuilder.setFullScreenIntent(contentIntent, true);
        }

        mNotificationManager.notify(notification.getChat_room_id().hashCode(), mBuilder.build());

        notificationEnabled = true;
    }

}
