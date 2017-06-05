package com.maryapc.remindme.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface CreateRemindView extends MvpView {

	void showAlertDialog(String message);

	void showTimePickerDialog(int hour, int minute);

	void showDatePickerDialog(int day, int month, int year);

	void finishActivity();
}
