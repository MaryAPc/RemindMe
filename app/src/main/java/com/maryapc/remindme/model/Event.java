package com.maryapc.remindme.model;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class Event extends RealmObject {

	private int id;

	@Required
	private String title;

	@Required
	private String timeStamp;

	@Required
	private String type;

	private boolean isDone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean done) {
		isDone = done;
	}
}
