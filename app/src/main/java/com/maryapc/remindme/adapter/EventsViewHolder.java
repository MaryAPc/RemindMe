package com.maryapc.remindme.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.maryapc.remindme.R;
import com.maryapc.remindme.model.Event;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.item_remind_text_view_title)
	TextView mTitleTextView;

	@BindView(R.id.item_remind_text_view_time)
	TextView mTimeTextView;

	@BindView(R.id.item_remind_image_view_notification)
	ImageView mNotifyImageView;

	private Event mEvent;


	public EventsViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}

	public void bind(Event event) {
		mEvent = event;
		/*Picasso.with(MyApplication.getInstance())
				.load(getImageNotify(event.getType()))
				.into(mNotifyImageView);*/
		mTitleTextView.setText(event.getTitle());
		mTimeTextView.setText(event.getTimeStamp());
	}

	public Event getEvent() {
		return mEvent;
	}

	/*private int getImageNotify(String type) {
		int res = 0;
		switch (type) {
			case NOTIFICATION:
				res = 0;
				break;
			case ALARM:
				res = 0;
				break;
			case WIDGET:
				res = 0;
				break;
		}
		return res;
	}*/
}
