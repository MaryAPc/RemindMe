package com.maryapc.remindme.ui;

import java.util.Calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.maryapc.remindme.R;
import com.maryapc.remindme.model.NotifyType;
import com.maryapc.remindme.presenter.CreateRemindPresenter;
import com.maryapc.remindme.view.CreateRemindView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateRemindActivity extends MvpAppCompatActivity implements View.OnClickListener, CreateRemindView {

	@BindView(R.id.activity_create_remind_edit_text_name)
	EditText mTitleEditText;

	@BindView(R.id.activity_create_remind_radio_button_notif)
	RadioButton mNotifRadioButton;

	@BindView(R.id.activity_create_remind_radio_button_alarm)
	RadioButton mAlarmRadioButton;

	@BindView(R.id.activity_create_remind_radio_button_widget)
	RadioButton mWidgetRadioButton;

	@BindView(R.id.activity_create_remind_button_create)
	Button mCreateButton;

	@BindView(R.id.activity_create_remind_button_time)
	Button mTimeButton;

	@BindView(R.id.activity_create_remind_button_date)
	Button mDateButton;

	@BindView(R.id.activity_create_remind_text_view_time)
	TextView mTimeTextView;

	@BindView(R.id.activity_create_remind_text_view_date)
	TextView mDateTextView;

	@InjectPresenter
	CreateRemindPresenter mPresenter;

	private Calendar mCalendar;

	private String mRemindType;
	private String mTime;
	private String mDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_remind);
		ButterKnife.bind(this);

		mCalendar = Calendar.getInstance();
		mCreateButton.setOnClickListener(this);
		mTitleEditText.setOnClickListener(this);
		mAlarmRadioButton.setOnClickListener(this);
		mNotifRadioButton.setOnClickListener(this);
		mWidgetRadioButton.setOnClickListener(this);
		mTimeButton.setOnClickListener(this);
		mDateButton.setOnClickListener(this);
		mTimeTextView.setOnClickListener(this);
		mDateTextView.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.activity_create_remind_radio_button_notif:
				mRemindType = NotifyType.NOTIFICATION.name();
				break;
			case R.id.activity_create_remind_radio_button_alarm:
				mRemindType = NotifyType.ALARM.name();
				break;
			case R.id.activity_create_remind_radio_button_widget:
				mRemindType = NotifyType.WIDGET.name();
				break;
			case R.id.activity_create_remind_button_time:
				mPresenter.showTimePicker();
				break;
			case R.id.activity_create_remind_button_date:
				mPresenter.showDatePicker();
				break;
			case R.id.activity_create_remind_text_view_time:
				mPresenter.showTimePicker();
				break;
			case R.id.activity_create_remind_text_view_date:
				mPresenter.showDatePicker();
				break;
			case R.id.activity_create_remind_button_create:
				if (mTitleEditText.getText().length() == 0) {
					mPresenter.showAlertDialog(getString(R.string.alert_name));
				} else if (mRemindType == null) {
					mPresenter.showAlertDialog(getString(R.string.alert_type));
				} else if (mTime == null) {
					mPresenter.showAlertDialog(getString(R.string.alert_time));
				} else if (mDate == null) {
					mPresenter.showAlertDialog(getString(R.string.alert_date));
				} else {
					String timeStamp = mTime + " " + mDate;
					mPresenter.createRemind(mTitleEditText.getText().toString(), mRemindType, timeStamp, mCalendar);
				}
				break;
		}
	}

	@Override
	public void showAlertDialog(String message) {
		new AlertDialog.Builder(this)
				.setTitle(R.string.alert_error)
				.setMessage(message)
				.setPositiveButton(android.R.string.ok, null)
				.show();
	}

	@Override
	public void showTimePickerDialog(int hour, int minute) {
		TimePickerDialog timePickerDialog = new TimePickerDialog(this, (timePicker, selectedHour, selectedMinute) -> {
			mTimeButton.setVisibility(View.INVISIBLE);
			mTimeTextView.setVisibility(View.VISIBLE);
			mCalendar.set(Calendar.HOUR_OF_DAY, selectedHour);
			mCalendar.set(Calendar.MINUTE, selectedMinute);
			mCalendar.set(Calendar.SECOND, 0);
			mCalendar.set(Calendar.MILLISECOND, 0);
			mTime = "Время: " + selectedHour + ":" + selectedMinute;
			mTimeTextView.setText(mTime);
		}, hour, minute, true);
		timePickerDialog.show();
	}

	@Override
	public void showDatePickerDialog(int day, int month, int year) {
		DatePickerDialog timePickerDialog = new DatePickerDialog(this, (datepicker, selectedyear, selectedmonth, selectedday) -> {
			mDateButton.setVisibility(View.GONE);
			mDateTextView.setVisibility(View.VISIBLE);
			mCalendar.set(Calendar.DAY_OF_MONTH, selectedday);
			mCalendar.set(Calendar.MONTH, selectedmonth);
			mCalendar.set(Calendar.YEAR, selectedyear);
			mDate = "Дата: " + selectedday + "." + selectedmonth + "." + selectedyear;
			mDateTextView.setText(mDate);
		}, year, month, day);
		timePickerDialog.show();
	}

	@Override
	public void finishActivity() {
		finish();
	}
}

