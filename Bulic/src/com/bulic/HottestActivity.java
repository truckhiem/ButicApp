package com.bulic;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bulic.adapter.PagerAdapter;
import com.bulic.adapter.SongItemAdapter;
import com.bulic.api.APIDefine;
import com.bulic.api.APIRequest;
import com.bulic.model.YoutubeResponseModel;
import com.bulic.utils.Contanst;
import com.bulic.utils.General;
import com.example.bulic.R;
import com.google.gson.Gson;

public class HottestActivity extends Activity{
	
	private String titleGroupName;
	private Context mContext;
	private TextView titleGroup;
	private PagerAdapter mPagerAdapter;
	private LinearLayout rootLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_catalog_item);
		initData();
		initView();
		bindingData();
		
	}
	
	private void bindingData() {
		titleGroup.setText(titleGroupName);		
		
		APIRequest request = new APIRequest(mContext);
		request.requestGET(defineUrlRequest(), mHandlerGetPlaylist);	
	}
	
	private String defineUrlRequest() {
		JSONArray arrHottestApiKey = null;
		String strHottestApiKey = General.readFileInRawFolder(mContext, R.raw.hottest_api_key);
		try {
			arrHottestApiKey = new JSONArray(strHottestApiKey);
			if(arrHottestApiKey != null){
				for (int i = 0; i < arrHottestApiKey.length(); i++) {
					JSONObject jsonObj = arrHottestApiKey.getJSONObject(i);
					if(!jsonObj.isNull(titleGroupName)){
						return String.format(APIDefine.URL_GET_ACTIVITY, jsonObj.get(titleGroupName),20);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "";
	}

	private void initView() {
		titleGroup = (TextView) findViewById(R.id.title_group);		
		rootLayout = (LinearLayout) findViewById(R.id.root_layout);
	}

	private void initData() {
		mContext = this;
		titleGroupName = getIntent().getStringExtra(Contanst.SendIntent.TitleGroup);
	}

	private Handler mHandlerGetPlaylist = new Handler(){
		@Override
		public void handleMessage(Message msg) {

			switch (msg.what) {
			case APIDefine.RESPONSE_OK:
				Gson gson = new Gson();
				YoutubeResponseModel lstModel = gson.fromJson(msg.obj.toString(), YoutubeResponseModel.class);
				for (int i = 0; i < lstModel.items.size(); i++) {
					SongItemAdapter songItemAdapter = new SongItemAdapter(mContext, lstModel.items.get(i));
					rootLayout.addView(songItemAdapter);
				}
				break;
			case APIDefine.RESPONSE_FAIL:
				break;
			default:
				break;
			}
		}
	};

}
