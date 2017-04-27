package com.maryapc.remindme.ui;

import java.util.List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.maryapc.remindme.MyApplication;
import com.maryapc.remindme.R;
import com.maryapc.remindme.adapter.EventsAdapter;
import com.maryapc.remindme.model.Event;
import com.maryapc.remindme.utils.RealmHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrentEventsFragment extends MvpAppCompatFragment implements View.OnClickListener, EventsAdapter.OnItemClickListener {

	@BindView(R.id.fragment_current_events_floating_button)
	FloatingActionButton mActionButton;

	@BindView(R.id.fragment_current_events_recycler_view)
	RecyclerView mEventRecyclerView;

	@Inject
	RealmHelper mRealmHelper;

	private EventsAdapter mEventsAdapter;
	private List<Event> mEvents;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		MyApplication.getAppComponent().inject(this);
		mEventsAdapter = new EventsAdapter(mEvents, this);
		mActionButton.setOnClickListener(this);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_current_events, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		ButterKnife.bind(this, view);
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.fragment_current_events_floating_button:
				break;
		}
	}

	@Override
	public void onItemClick(View view, int position) {

	}
}
