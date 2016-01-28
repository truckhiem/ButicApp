package com.example.bulic.fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;

import com.example.bulic.R;
import com.example.bulic.adapter.SongItemAdapter;
import com.example.bulic.adapter.TitleGroupAdapter;
import com.example.bulic.api.APIDefine;
import com.example.bulic.api.APIRequest;
import com.example.bulic.base.BaseFragmentActivity;
import com.example.bulic.model.SongModel;
import com.example.bulic.model.YoutubeResponseModel;
import com.example.bulic.utils.General;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

public class Frg_Trending extends BaseFragmentActivity{
	
	private ArrayList<YoutubeResponseModel> lstCatalogHottest;
	private int countResponse = 0;
	private String[] arrLstGroup;
	private ArrayList<String> lstUrl;
	
	public Frg_Trending(Context mContext) {
		super(mContext);
	}

	private LinearLayout rootLayout;
	
	@Override
	public void initData() {
		lstCatalogHottest = new ArrayList<YoutubeResponseModel>();
		lstUrl = defineUrlRequest();
		getDataFromYoutube();
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
					lstUrl.add(String.format(APIDefine.URL_GET_PLAYLIST, jsonObj.get(arrLstGroup[i]),3));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return lstUrl;
	}

	private void bindingData(){
		TitleGroupAdapter groupAdapter = new TitleGroupAdapter(mContext, arrLstGroup[countResponse]);
		rootLayout.addView(groupAdapter);
		for (int j = 0; j < 3; j++) {
			SongItemAdapter songItemAdapter = new SongItemAdapter(mContext, lstCatalogHottest.get(countResponse).items.get(j));
			rootLayout.addView(songItemAdapter);
		}
		countResponse++;
		if(countResponse >= arrLstGroup.length)
			return;
		getDataFromYoutube();
	}
	
	private void getDataFromYoutube(){
		APIRequest request = new APIRequest(mContext);
		request.requestGET(lstUrl.get(countResponse), mHandlerGetPlaylist);
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
			switch (msg.what) {
			case APIDefine.RESPONSE_OK:
				Gson gson = new Gson();
				YoutubeResponseModel youtubeResponseModel = gson.fromJson(msg.obj.toString(), YoutubeResponseModel.class);
				lstCatalogHottest.add(youtubeResponseModel);
				break;
			case APIDefine.RESPONSE_FAIL:
				break;
			}
			if(countResponse < arrLstGroup.length){
				bindingData();
			}
		}
	};
	
}
