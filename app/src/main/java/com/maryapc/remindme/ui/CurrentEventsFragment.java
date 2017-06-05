package com.maryapc.remindme.ui;

import java.util.ArrayList;
import java.util.List;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.maryapc.remindme.MyApplication;
import com.maryapc.remindme.R;
import com.maryapc.remindme.adapter.EventsAdapter;
import com.maryapc.remindme.model.Event;
import com.maryapc.remindme.presenter.CurrentEventsPresenter;
import com.maryapc.remindme.view.CurrentEventsView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrentEventsFragment extends MvpAppCompatFragment implements CurrentEventsView, EventsAdapter.OnItemClickListener {

	@BindView(R.id.fragment_current_events_recycler_view)
	RecyclerView mEventRecyclerView;

	@BindView(R.id.fragment_current_events_text_view_no_events)
	TextView mNoEventsTextView;

	@InjectPresenter
	CurrentEventsPresenter mPresenter;

	private EventsAdapter mEventsAdapter;
	private Context mContext = MyApplication.getInstance();
	private int mStart;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mEventsAdapter = new EventsAdapter(new ArrayList<>(), this);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_current_events, container, false);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		ButterKnife.bind(this, view);
		LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
		DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mContext, layoutManager.getOrientation());
		mEventRecyclerView.addItemDecoration(dividerItemDecoration);
		mEventRecyclerView.setLayoutManager(layoutManager);
		mEventRecyclerView.setAdapter(mEventsAdapter);
		mEventRecyclerView.getRecycledViewPool().setMaxRecycledViews(view.getLayerType(), 0);
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
	public void onResume() {
		super.onResume();
		mPresenter.setList();
	}

	@Override
	public void onItemClick(View view, int position, Event event) {
		LinearLayout editLayout = (LinearLayout) view.findViewById(R.id.item_remind_linear_layout_edit_panel);
		CardView cardView = (CardView) view.findViewById(R.id.item_remind_card_view);
		if (editLayout.getVisibility() == View.GONE) {
			mStart = cardView.getHeight();
			Animation animFadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
			editLayout.startAnimation(animFadeIn);
			editLayout.setVisibility(View.VISIBLE);
			ValueAnimator mAnimator = slideAnimator(cardView, mStart, getFullHeight(cardView) - 144);
			mAnimator.start();
		} else {
			ValueAnimator mAnimator = slideAnimator(cardView, getFullHeight(cardView) - 144, mStart);
			mAnimator.start();
			Animation animFadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
			editLayout.startAnimation(animFadeOut);
			editLayout.setVisibility(View.GONE);
		}
		ImageView editImageView = (ImageView) view.findViewById(R.id.item_remind_image_view_edit);
		ImageView deleteImageView = (ImageView) view.findViewById(R.id.item_remind_image_view_delete);
		editImageView.setOnClickListener(view1 -> Toast.makeText(getActivity(), "edit", Toast.LENGTH_SHORT).show());

		deleteImageView.setOnClickListener(view12 -> mPresenter.update(position, event.getId()));

	}

	public static int getFullHeight(ViewGroup layout) {
		int specWidth = View.MeasureSpec.makeMeasureSpec(0 /* any */, View.MeasureSpec.UNSPECIFIED);
		int specHeight = View.MeasureSpec.makeMeasureSpec(0 /* any */, View.MeasureSpec.UNSPECIFIED);


		layout.measure(specWidth, specHeight);
		int totalHeight = 0;//layout.getMeasuredHeight();
		int initialVisibility = layout.getVisibility();
		layout.setVisibility(View.VISIBLE);
		int numberOfChildren = layout.getChildCount();
		for (int i = 0; i < numberOfChildren; i++) {
			View child = layout.getChildAt(i);
			if (child instanceof ViewGroup) {
				totalHeight += getFullHeight((ViewGroup) child);
			} else {
				int desiredWidth = View.MeasureSpec.makeMeasureSpec(layout.getWidth(),
						View.MeasureSpec.AT_MOST);
				child.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
				totalHeight += child.getMeasuredHeight();
			}

		}
		layout.setVisibility(initialVisibility);
		return totalHeight;
	}

	private ValueAnimator slideAnimator(CardView cardView, int start, int end) {
		ValueAnimator animator = ValueAnimator.ofInt(start, end);
		animator.addUpdateListener(valueAnimator -> {
			int value = (Integer) valueAnimator.getAnimatedValue();
			ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
			layoutParams.height = value;
			cardView.setLayoutParams(layoutParams);
		});
		return animator;
	}

	@Override
	public void setEvents(List<Event> allEvents) {
		mEventsAdapter.setCollection(allEvents);
	}

	@Override
	public void showEmptyList() {
		mNoEventsTextView.setVisibility(View.VISIBLE);
	}

	@Override
	public void showInfoToast(String message) {
		Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void updateList(int position, int id) {
		mEventsAdapter.deleteItem(position);
		mPresenter.deleteRemind(id);
	}
}
