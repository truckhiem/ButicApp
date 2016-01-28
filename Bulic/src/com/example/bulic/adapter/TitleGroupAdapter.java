package com.example.bulic.adapter;

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
import com.example.bulic.utils.Contanst;

public class TitleGroupAdapter extends LinearLayout{
	public TitleGroupAdapter(Context context) {
		super(context);
		
	}
	
	public TitleGroupAdapter(final Context context, final String titleGroup){
		super(context);
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
}
