package com.bulic.fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;

import com.bulic.adapter.SongItemAdapter;
import com.bulic.adapter.TitleGroupAdapter;
import com.bulic.api.APIDefine;
import com.bulic.api.APIRequest;
import com.bulic.base.BaseFragmentActivity;
import com.bulic.model.YoutubeResponseModel;
import com.bulic.model.SongModel;
import com.bulic.utils.General;
import com.example.bulic.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class Frg_Hottest extends BaseFragmentActivity{
	
	private ArrayList<YoutubeResponseModel> lstCatalogHottest;
	private int countResponse = 0;
	private String[] arrLstGroup;
	
	public Frg_Hottest(Context mContext) {
		super(mContext);
	}

	private LinearLayout rootLayout;
	
	@Override
	public void initData() {
		lstCatalogHottest = new ArrayList<YoutubeResponseModel>();
		ArrayList<String> lstUrl = defineUrlRequest();
		
		for (int i = 0; i < lstUrl.size(); i++) {
			APIRequest request = new APIRequest(mContext);
			request.requestGET(lstUrl.get(i), mHandlerGetPlaylist);	
		}
	}

	private ArrayList<String> defineUrlRequest() {
		JSONArray arrHottestApiKey = null;
		ArrayList<String> lstUrl = new ArrayList<String>();
		String strHottestApiKey = General.readFileInRawFolder(mContext, R.raw.hottest_api_key);
		try {
			arrHottestApiKey = new JSONArray(strHottestApiKey);
		
			arrLstGroup = getResources().getStringArray(R.array.lst_hottest);
			if(arrHottestApiKey != null){
				for (int i = 0; i < arrHottestApiKey.length(); i++) {
					JSONObject jsonObj = arrHottestApiKey.getJSONObject(i);
					lstUrl.add(String.format(APIDefine.URL_GET_ACTIVITY, jsonObj.get(arrLstGroup[i]),3));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return lstUrl;
	}

	private void bindingData() {
		rootLayout.removeAllViews();
		for (int i = 0; i < lstCatalogHottest.size(); i++) {
			TitleGroupAdapter groupAdapter = new TitleGroupAdapter(mContext, arrLstGroup[i]);
			rootLayout.addView(groupAdapter);
			for (int j = 0; j < 3; j++) {
				SongItemAdapter songItemAdapter = new SongItemAdapter(mContext, lstCatalogHottest.get(i).items.get(j));
				rootLayout.addView(songItemAdapter);
			}
		}
	}

	@Override
	public int getContentView() {
		return R.layout.frg_hottest;
	}

	@Override
	public void initView(View rootView) {
		rootLayout = (LinearLayout) rootView.findViewById(R.id.root_layout);
	}
	
	private Handler mHandlerGetPlaylist = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			countResponse ++;
			switch (msg.what) {
			case APIDefine.RESPONSE_OK:
				Gson gson = new Gson();
				YoutubeResponseModel youtubeResponseModel = gson.fromJson(msg.obj.toString(), YoutubeResponseModel.class);
				lstCatalogHottest.add(youtubeResponseModel);
				break;
			case APIDefine.RESPONSE_FAIL:
				break;
			}
			if(countResponse == arrLstGroup.length){
				bindingData();
			}
		}
	};
	
}
