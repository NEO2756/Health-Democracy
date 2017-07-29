package me.ritabrata.myhealth.helpers;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import me.ritabrata.myhealth.R;
import me.ritabrata.myhealth.activity.MedicineStatusActivity;

public class Notificationmassage extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent arg1) {


        showNotification(context);
    }

    private void showNotification(Context context) {
        Log.i("notification", "visible");

        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, new Intent(context, Notificationmassage.class), 0);

        Uri notificationTune = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationManager notif = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify = new Notification.Builder
                (context)
                .setContentTitle("Greens")
                .setContentText("Jaypee")
                .setAutoCancel(true)
                .setPriority(Notification.PRIORITY_HIGH)

                .setSound(notificationTune)
                .addAction(android.R.drawable.ic_menu_save,
                        "Medicine Taken",
                        PendingIntent.getActivity(context, 12345,
                                new Intent(context,
                                        MedicineStatusActivity.class),
                                PendingIntent.FLAG_ONE_SHOT))

                .addAction(android.R.drawable.ic_menu_save,
                        " Medicine Not Taken",
                        PendingIntent.getActivity(context, 12345,
                                new Intent(context,
                                        MedicineStatusActivity.class),
                                PendingIntent.FLAG_ONE_SHOT))

                .setSmallIcon(R.drawable.ic_launcher).build();

        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notif.notify(0, notify);

    }
}