package com.example.bulic.fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.bulic.R;
import com.example.bulic.adapter.PlaylistItemAdapter;
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

public class Frg_Playlist extends BaseFragmentActivity{
	
	private ArrayList<YoutubeResponseModel> lstCatalogHottest;
	private int countResponse = 0;
	private String[] arrLstGroup;
	private ArrayList<String> lstUrl;
	private ProgressBar mProgressBar;
	
	public Frg_Playlist(Context mContext) {
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
		ArrayList<String> lstUrl = new ArrayList<String>();
		lstUrl.add(String.format(APIDefine.URL_GET_PLAYLIST, APIDefine.ID_PLAYLIST, 20));
		return lstUrl;
	}

	private void bindingData(){
		for (int j = 0; j < 20; j++) {
			PlaylistItemAdapter songItemAdapter = new PlaylistItemAdapter(mContext, lstCatalogHottest.get(0).items.get(j));
			rootLayout.addView(songItemAdapter);
		}
		mProgressBar.setVisibility(View.GONE);
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
		mProgressBar = (ProgressBar) rootView.findViewById(R.id.progress);
		mProgressBar.setVisibility(View.VISIBLE);
		mProgressBar.setSecondaryProgress(0);
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
			bindingData();
		}
	};
	
	@Override
	public void loadDataSaved(Bundle savedInstanceState) {
		lstCatalogHottest = savedInstanceState.getParcelableArrayList(KEY_STATE_LST_CATALOG);
		countResponse = savedInstanceState.getInt(KEY_STATE_COUNT_RESPONSE);
		bindingData();
	}
	
	@Override
	public void saveData(Bundle outState) {
		outState.putParcelableArrayList(KEY_STATE_LST_CATALOG, lstCatalogHottest);
		outState.putInt(KEY_STATE_COUNT_RESPONSE, countResponse);
	}
	
}
