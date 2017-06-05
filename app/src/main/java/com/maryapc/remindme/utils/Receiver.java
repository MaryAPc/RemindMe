package com.maryapc.remindme.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.maryapc.remindme.ui.RemindActivity;

public class Receiver extends BroadcastReceiver {

	private NotificationManager mNotificationManager;

	@Override
	public void onReceive(Context context, Intent intent) {
		mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

		Intent notificationIntent = new Intent(context, RemindActivity.class);
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		stackBuilder.addParentStack(RemindActivity.class);
		stackBuilder.addNextIntent(notificationIntent);

		PendingIntent pendingIntent = stackBuilder.getPendingIntent(intent.getExtras().getInt("ID"), PendingIntent.FLAG_UPDATE_CURRENT);

		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		Notification notification = builder.setContentTitle(intent.getExtras().getString("Title"))
				.setSmallIcon(android.R.mipmap.sym_def_app_icon)
				.setContentText(String.valueOf(intent.getExtras().getInt("ID")))
				.setContentIntent(pendingIntent)
				.build();
		mNotificationManager.notify(intent.getExtras().getInt("ID"), notification);
	}
}
