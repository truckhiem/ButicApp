package com.bulic.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bulic.HottestActivity;
import com.bulic.utils.Contanst;
import com.example.bulic.R;

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
		
		view.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, HottestActivity.class);
				intent.putExtra(Contanst.SendIntent.TitleGroup, titleGroup);
				context.startActivity(intent);
			}
		});
	}
}
