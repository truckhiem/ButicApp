package com.example.bulic.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragmentActivity extends Fragment {
	public final String KEY_STATE_LST_CATALOG = "ListCatalog";
	public final String KEY_STATE_COUNT_RESPONSE = "CountResponse";
	public Context mContext;
	private View rootView;
	
	public BaseFragmentActivity(Context mContext){
		this.mContext = mContext;
	}

	@Override
    public View onCreateView(LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        // The last two arguments ensure LayoutParams are inflated
        // properly.
        rootView = inflater.inflate(getContentView(), container, false);
        initView(rootView);
        if(savedInstanceState == null){
        	initData();
		}else{
			loadDataSaved(savedInstanceState);			
		}
        
        return rootView;
    }
	
	public void loadDataSaved(Bundle savedInstanceState){}

	public abstract void initData();
	
	public abstract void initView(View rootView);

	public abstract int getContentView();
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    saveData(outState);
	}

	public void saveData(Bundle outState) {}
}
