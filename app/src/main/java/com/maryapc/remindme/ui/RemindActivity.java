package com.maryapc.remindme.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.maryapc.remindme.R;
import com.maryapc.remindme.adapter.ViewPagerAdapter;
import com.maryapc.remindme.presenter.ActivityPresenter;
import com.maryapc.remindme.view.ActivityView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RemindActivity extends MvpAppCompatActivity implements View.OnClickListener, ActivityView {

	@BindView(R.id.activity_remind_toolbar)
	Toolbar mToolbar;

	@BindView(R.id.activity_remind_app_bar)
	AppBarLayout mAppBarLayout;

	@BindView(R.id.activity_remind_tab_layout)
	TabLayout mTabLayout;

	@BindView(R.id.activity_remind_view_pager)
	ViewPager mViewPager;

	@BindView(R.id.activity_remind_floating_button)
	FloatingActionButton mActionButton;

	@InjectPresenter
	ActivityPresenter mPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remind);
		ButterKnife.bind(this);
		mActionButton.setOnClickListener(this);
		setSupportActionBar(mToolbar);
		if (mViewPager != null) {
			ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
			adapter.addFragment(new CurrentEventsFragment(), "Текущие");
			adapter.addFragment(new DoneEventsFragment(), "Прошедшие");
			mViewPager.setAdapter(adapter);
		}
		mToolbar.setTitle("Напомни мне!");
		mTabLayout.setupWithViewPager(mViewPager);
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.activity_remind_floating_button:
				mPresenter.createRemind();
				break;
		}
	}

	@Override
	public void showCreateActivity() {
		Intent intent = new Intent(RemindActivity.this, CreateRemindActivity.class);
		startActivity(intent);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mPresenter.closeDB();
	}
}
