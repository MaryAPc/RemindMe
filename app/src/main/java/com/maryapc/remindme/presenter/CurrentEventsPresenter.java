package com.maryapc.remindme.presenter;

import java.util.List;

import android.content.Context;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.maryapc.remindme.MyApplication;
import com.maryapc.remindme.R;
import com.maryapc.remindme.model.Event;
import com.maryapc.remindme.utils.RealmHelper;
import com.maryapc.remindme.view.CurrentEventsView;

import javax.inject.Inject;

@InjectViewState
public class CurrentEventsPresenter extends MvpPresenter<CurrentEventsView> {

	@Inject
	RealmHelper mRealmHelper;

	private Context mContext = MyApplication.getInstance();

	public CurrentEventsPresenter() {
		MyApplication.getAppComponent().inject(this);
	}

	public void setList() {
		List<Event> eventList = mRealmHelper.getAllEvents();
		if (eventList.size() == 0) {
			getViewState().showEmptyList();
		} else {
			getViewState().setEvents(eventList);
		}
	}

	public void deleteRemind(int eventId) {
		mRealmHelper.delete(eventId);
		getViewState().showInfoToast(mContext.getString(R.string.remind_was_deleted));
	}

	public void update(int position, int id) {
		getViewState().updateList(position, id);
	}
}
