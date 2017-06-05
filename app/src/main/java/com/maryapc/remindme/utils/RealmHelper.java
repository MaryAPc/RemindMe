package com.maryapc.remindme.utils;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.maryapc.remindme.model.Event;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RealmHelper {

	private static Realm sRealm;

	public RealmHelper(Context context) {
		if (sRealm == null) {
			RealmConfiguration config = new RealmConfiguration.Builder(context)
					.deleteRealmIfMigrationNeeded()
					.build();
			sRealm = Realm.getInstance(config);
		}
	}

	public List<Event> getAllEvents() {
		sRealm.beginTransaction();
		RealmResults<Event> events = sRealm.where(Event.class).findAll();
		sRealm.commitTransaction();
		return events;
	}

	public int create(String title, String date, String type) {
		sRealm.beginTransaction();
		Event event = sRealm.createObject(Event.class);
		Number currentIdNum = sRealm.where(Event.class).max("id");
		int nextId;
		if (currentIdNum == null) {
			nextId = 1;
		} else {
			nextId = currentIdNum.intValue() + 1;
		}
		event.setId(nextId);
		event.setTitle(title);
		event.setTimeStamp(date);
		event.setType(type);
		event.setDone(false);
		sRealm.commitTransaction();
		return nextId;
	}

	public void complete(int id) {
		sRealm.beginTransaction();
		RealmResults<Event> events = sRealm.where(Event.class).equalTo("id", id).findAll();
		if (events.size() != 0) {
			for (Event event : events) {
				event.setDone(true);
				sRealm.copyToRealmOrUpdate(event);
			}
		}
		sRealm.commitTransaction();
	}

	public void delete(int id) {
		sRealm.beginTransaction();
		RealmResults<Event> events = sRealm.where(Event.class).equalTo("id", id).findAll();
		List<Event> eventList = new ArrayList<>();
		eventList.addAll(events);
		if (eventList.size() != 0) {
			for (Event event : eventList) {
				event.removeFromRealm();
			}
		}
		sRealm.commitTransaction();
	}

	public void close() {
		sRealm.close();
	}
}
