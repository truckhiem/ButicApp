package com.bulic;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.widget.TextView;

import com.bulic.adapter.PagerAdapter;
import com.example.bulic.R;

public class MainActivity extends FragmentActivity{
	
	private Context mContext;
	private PagerAdapter mPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mContext = this;
		initPager();
	}
	
	private void initPager(){
		mPagerAdapter = new PagerAdapter(getSupportFragmentManager(), mContext);
		ViewPager mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mPagerAdapter);
        
        PagerTabStrip mPagerTabStrip = (PagerTabStrip) findViewById(R.id.pager_title_strip);
        mPagerTabStrip.setGravity(Gravity.LEFT);
	}
}
