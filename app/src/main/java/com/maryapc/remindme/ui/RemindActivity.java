package com.maryapc.remindme.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.maryapc.remindme.R;
import com.maryapc.remindme.adapter.ViewPagerAdapter;

import butterknife.BindView;

public class RemindActivity extends MvpAppCompatActivity {

	@BindView(R.id.activity_remind_toolbar)
	Toolbar mToolbar;

	@BindView(R.id.activity_remind_app_bar)
	AppBarLayout mAppBarLayout;

	@BindView(R.id.activity_remind_tab_layout)
	TabLayout mTabLayout;

	@BindView(R.id.activity_remind_view_pager)
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remind);

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
}
