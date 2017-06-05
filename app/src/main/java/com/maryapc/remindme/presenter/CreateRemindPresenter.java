package com.maryapc.remindme.presenter;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.maryapc.remindme.MyApplication;
import com.maryapc.remindme.utils.RealmHelper;
import com.maryapc.remindme.utils.Receiver;
import com.maryapc.remindme.view.CreateRemindView;

import javax.inject.Inject;

@InjectViewState
public class CreateRemindPresenter extends MvpPresenter<CreateRemindView> {

	@Inject
	RealmHelper mRealmHelper;

	private Context mContext = MyApplication.getInstance();

	public CreateRemindPresenter() {
		MyApplication.getAppComponent().inject(this);
	}

	public void showAlertDialog(String message) {
		getViewState().showAlertDialog(message);
	}

	public void showTimePicker() {
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		getViewState().showTimePickerDialog(hour, minute);
	}

	public void showDatePicker() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		getViewState().showDatePickerDialog(day, month, year);
	}

	public void createRemind(String title, String remindType, String timeStamp, Calendar calendar) {
		int id = mRealmHelper.create(title, timeStamp, remindType);
		AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
		Intent intent = new Intent(mContext, Receiver.class);
		intent.putExtra("ID", id);
		intent.putExtra("Title", title);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, id, intent, 0);
		alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
		getViewState().finishActivity();
	}
}
