package com.example.bulic.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.bulic.R;
import com.example.bulic.fragment.Frg_Hottest;
import com.example.bulic.fragment.Frg_Playlist;
import com.example.bulic.fragment.Frg_Trending;
import com.google.api.services.youtube.YouTube.Videos.GetRating;

public class PagerAdapter extends FragmentStatePagerAdapter {
	
	private Context mContext;
	private Fragment frgHottest, frgTrending, frgPlaylist;
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
		frgPlaylist = new Frg_Playlist(mContext);
	}

	@Override
    public Fragment getItem(int i) {
		switch (i) {
		case 0:
			return frgHottest;
		case 1:
			 return frgTrending;
		}
		return frgPlaylist;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return lstPageTitle[position];
    }
    
    
}