package com.maryapc.remindme.di.module;

import android.content.Context;

import com.maryapc.remindme.utils.RealmHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RealmModule {

	@Provides
	@Singleton
	RealmHelper providesRealmHelper(Context context) {
		return new RealmHelper(context);
	}
}
