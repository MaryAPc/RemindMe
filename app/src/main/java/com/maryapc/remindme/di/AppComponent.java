package com.maryapc.remindme.di;

import com.maryapc.remindme.di.module.AppContextModule;
import com.maryapc.remindme.di.module.RealmModule;
import com.maryapc.remindme.presenter.ActivityPresenter;
import com.maryapc.remindme.presenter.CreateRemindPresenter;
import com.maryapc.remindme.presenter.CurrentEventsPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppContextModule.class, RealmModule.class})
public interface AppComponent {

	void inject(ActivityPresenter presenter);

	void inject(CurrentEventsPresenter presenter);

	void inject(CreateRemindPresenter presenter);
}
