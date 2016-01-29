package com.example.bulic.adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bulic.HottestActivity;
import com.example.bulic.R;
import com.example.bulic.api.APIDefine;
import com.example.bulic.utils.Contanst;
import com.example.bulic.utils.General;

public class TitleGroupAdapter extends LinearLayout{
	
	private Context mContext;
	
	public TitleGroupAdapter(Context context) {
		super(context);
		this.mContext = context;
	}
	
	public TitleGroupAdapter(final Context context, final String titleGroup){
		super(context);
		this.mContext = context;
		LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.item_title_group, this, true);
		
		TextView txtGroupTitle = (TextView) view.findViewById(R.id.txt_group_title);
		txtGroupTitle.setText(titleGroup);
		ImageView btnOpenCatalog = (ImageView) view.findViewById(R.id.btn_open_catalog);
		btnOpenCatalog.setColorFilter(getResources().getColor(R.color.text_red));
		btnOpenCatalog.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, HottestActivity.class);
				intent.putExtra(Contanst.SendIntent.PlaylistID, defineUrlRequest(titleGroup));
				intent.putExtra(Contanst.SendIntent.TitleGroup, titleGroup);
				context.startActivity(intent);
			}
		});
		btnOpenCatalog.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_CANCEL:
				case MotionEvent.ACTION_UP:
					((ImageView)v).setColorFilter(getResources().getColor(R.color.text_red));					
					break;

				default:
					((ImageView)v).setColorFilter(getResources().getColor(R.color.white));
					break;
				}
				return false;
			}
		});
	}
	
	private String defineUrlRequest(String titleGroup) {
		JSONArray arrHottestApiKey = null;
		String strHottestApiKey = General.readFileInRawFolder(mContext, R.raw.hottest_api_key);
		try {
			arrHottestApiKey = new JSONArray(strHottestApiKey);
			if(arrHottestApiKey != null){
				for (int i = 0; i < arrHottestApiKey.length(); i++) {
					JSONObject jsonObj = arrHottestApiKey.getJSONObject(i);
					if(!jsonObj.isNull(titleGroup)){
						return String.format(APIDefine.URL_GET_ACTIVITY, jsonObj.get(titleGroup),20);
					}
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "";
	}
}
