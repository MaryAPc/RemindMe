package com.maryapc.remindme.utils;

import android.content.Context;

import com.maryapc.remindme.model.Event;
import com.maryapc.remindme.model.NotifyType;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

	private static Realm sRealm;

	public RealmHelper(Context context){
		sRealm = Realm.getInstance(context);
	}

	public void create(String title, String date, NotifyType type){
		sRealm.beginTransaction();
		Event event = sRealm.createObject(Event.class);
		int nextID = (int) (sRealm.where(Event.class).max(("id") + 1));
		event.setId(nextID);
		event.setTitle(title);
		event.setDate(date);
		event.setType(type);
		sRealm.commitTransaction();
	}

	public void delete(int id) {
		sRealm.beginTransaction();
		RealmResults<Event> events = sRealm.where(Event.class).equalTo("id", id).findAll();
		if (events.size() != 0){
			for (Event event : events) {
				event.removeFromRealm();
			}
		}
		sRealm.commitTransaction();
	}

	public void close() {
		sRealm.close();
	}
}
