package com.maryapc.remindme;

import com.arellomobile.mvp.MvpApplication;
import com.maryapc.remindme.di.AppComponent;
import com.maryapc.remindme.di.DaggerAppComponent;
import com.maryapc.remindme.di.module.AppContextModule;
import com.maryapc.remindme.di.module.RealmModule;

public class MyApplication extends MvpApplication {

	private static AppComponent sAppComponent;
	private static MyApplication sInstance;

	@Override
	public void onCreate() {
		super.onCreate();
		sInstance = this;

		sAppComponent = DaggerAppComponent.builder()
				.appContextModule(new AppContextModule(this))
				.realmModule(new RealmModule())
				.build();
	}

	public static MyApplication getInstance() {
		return sInstance;
	}

	public static AppComponent getAppComponent() {
		return sAppComponent;
	}
}

