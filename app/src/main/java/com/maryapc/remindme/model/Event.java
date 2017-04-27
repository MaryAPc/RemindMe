package com.maryapc.remindme.model;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class Event extends RealmObject {

	@Required
	private int id;

	@Required
	private String title;

	@Required
	private String date;

	@Required
	private String type;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public NotifyType getType() {
		return NotifyType.valueOf(type);
	}

	public void setType(NotifyType val) {
		this.type = val.toString();
	}
}
