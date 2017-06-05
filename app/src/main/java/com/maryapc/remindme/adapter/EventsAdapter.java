package com.maryapc.remindme.adapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maryapc.remindme.R;
import com.maryapc.remindme.model.Event;

public class EventsAdapter extends RecyclerView.Adapter<EventsViewHolder> {

	private EventsViewHolder mEventsViewHolder;
	private List<Event> mEvents = new ArrayList<>();
	private OnItemClickListener mOnItemClickListener;

	public interface OnItemClickListener {

		void onItemClick(View view, int position, Event event);
	}

	public EventsAdapter(List<Event> events, OnItemClickListener listener) {
		mEvents = events;
		mOnItemClickListener = listener;
	}

	@Override
	public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_remind, parent, false);
		mEventsViewHolder = new EventsViewHolder(view);
		return mEventsViewHolder;
	}

	@Override
	public void onBindViewHolder(EventsViewHolder holder, int position) {
		holder.bind(mEvents.get(position));
		holder.itemView.setOnClickListener(view -> mOnItemClickListener.onItemClick(view, position, holder.getEvent()));
	}

	@Override
	public int getItemCount() {
		return mEvents.size();
	}

	public void setCollection(@Nullable List<Event> events) {
		if (events == null) {
			mEvents = Collections.emptyList();
		} else {
			mEvents = events;
		}
		notifyDataSetChanged();
	}

	public void addItem(Event event) {
		int positionStart = mEvents.size() + 1;
		mEvents.add(event);
		notifyItemChanged(positionStart);
	}

	public void deleteItem(int position) {
		notifyItemRemoved(position);
	}
}
