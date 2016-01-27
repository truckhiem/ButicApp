package com.bulic.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.bulic.fragment.Frg_Hottest;
import com.bulic.fragment.Frg_Trending;
import com.example.bulic.R;
import com.google.api.services.youtube.YouTube.Videos.GetRating;

public class PagerAdapter extends FragmentStatePagerAdapter {
	
	private Context mContext;
	private Fragment frgHottest, frgTrending;
	private String[] lstPageTitle;
    
	public PagerAdapter(FragmentManager fm) {
        super(fm);
    }
	
	public PagerAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
        initData();
    }

    private void initData() {
		lstPageTitle = mContext.getResources().getStringArray(R.array.list_tab_bar);
		frgHottest = new Frg_Hottest(mContext);
		frgTrending = new Frg_Trending(mContext);
	}

	@Override
    public Fragment getItem(int i) {
		if(i == 0){
			return frgHottest;
		}
       return frgTrending;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return lstPageTitle[position];
    }
    
    
}