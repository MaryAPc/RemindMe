package com.maryapc.remindme.view;

import java.util.List;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.maryapc.remindme.model.Event;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface CurrentEventsView extends MvpView {

	void setEvents(List<Event> allEvents);

	void showEmptyList();

	void showInfoToast(String message);

	void updateList(int position, int id);
}
