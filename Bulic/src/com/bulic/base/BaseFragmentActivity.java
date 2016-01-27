package com.bulic.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragmentActivity extends Fragment {
	
	public Context mContext;
	
	public BaseFragmentActivity(Context mContext){
		this.mContext = mContext;
	}

	@Override
    public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
		initData();
        View rootView = inflater.inflate(getContentView(), container, false);
        initView(rootView);
        return rootView;
    }
	
	public abstract void initData();
	
	public abstract void initView(View rootView);

	public abstract int getContentView();
}
