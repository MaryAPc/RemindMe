package com.maryapc.remindme.di;

import com.maryapc.remindme.di.module.AppContextModule;
import com.maryapc.remindme.di.module.RealmModule;
import com.maryapc.remindme.ui.CurrentEventsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppContextModule.class, RealmModule.class})
public interface AppComponent {

	void inject(CurrentEventsFragment fragment);
}
