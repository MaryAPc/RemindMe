package com.maryapc.remindme.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.maryapc.remindme.MyApplication;
import com.maryapc.remindme.utils.RealmHelper;
import com.maryapc.remindme.view.ActivityView;

import javax.inject.Inject;

@InjectViewState
public class ActivityPresenter extends MvpPresenter<ActivityView> {

	@Inject
	RealmHelper mRealmHelper;

	public ActivityPresenter() {
		MyApplication.getAppComponent().inject(this);
	}

	public void createRemind() {
		getViewState().showCreateActivity();
	}

	public void closeDB() {
		mRealmHelper.close();
	}
}
