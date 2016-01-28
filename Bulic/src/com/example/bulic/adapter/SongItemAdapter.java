package com.example.bulic.adapter;

import java.io.InputStream;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bulic.BulicPlayer;
import com.example.bulic.R;
import com.example.bulic.model.YoutubeResponseModel;
import com.example.bulic.utils.Contanst;

public class SongItemAdapter extends LinearLayout{
	
	public SongItemAdapter(Context context) {
		super(context);
		
	}
	
	public SongItemAdapter(final Context context, final YoutubeResponseModel.Item songModel){
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.item_song, this, true);
		
		ImageView imgView = (ImageView) view.findViewById(R.id.img_thumbnail);
		TextView txtSongName = (TextView) view.findViewById(R.id.txt_song_name);
		TextView txtSongArtis = (TextView) view.findViewById(R.id.txt_song_artis);
		TextView txtSongDuration = (TextView) view.findViewById(R.id.txt_song_duration);
		
		loadImage(imgView, songModel.snippet.thumbnails.medium.url);
		
		String[] arrTitle = songModel.snippet.title.split("-");
		if(arrTitle.length > 1){
			txtSongName.setText(arrTitle[0].trim());
			txtSongArtis.setText(arrTitle[1].trim());
		}else{
			txtSongName.setText(songModel.snippet.title);
		}
		LinearLayout viewSongItem = (LinearLayout) view.findViewById(R.id.item_song_view);
		viewSongItem.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String songID = songModel.contentDetails.bulletin.resourceId.videoId;
				Intent intent = new Intent(context, BulicPlayer.class);
				intent.putExtra(Contanst.SendIntent.VideoID, songID);
				context.startActivity(intent);
			}
		});
		
		viewSongItem.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				System.out.println("motionevent: "+event.getAction());
				switch (event.getAction()) {
				case MotionEvent.ACTION_CANCEL:
				case MotionEvent.ACTION_SCROLL:
				case MotionEvent.ACTION_MOVE:
				case MotionEvent.ACTION_UP:
					((LinearLayout)v).setBackgroundColor(getResources().getColor(R.color.backgroud));					
					break;

				case MotionEvent.ACTION_DOWN:
					((LinearLayout)v).setBackgroundColor(getResources().getColor(R.color.text_red));
					break;
				}
				return false;
			}
		});
	}
	
	private void loadImage(ImageView imgView, String strUrl){
		DownloadImageTask downloadImageTask = new DownloadImageTask(imgView);
		downloadImageTask.execute(strUrl);
	}
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
		  ImageView bmImage;

		  public DownloadImageTask(ImageView bmImage) {
		      this.bmImage = bmImage;
		  }

		  protected Bitmap doInBackground(String... urls) {
		      String urldisplay = urls[0];
		      Bitmap mIcon11 = null;
		      try {
		        InputStream in = new java.net.URL(urldisplay).openStream();
		        mIcon11 = BitmapFactory.decodeStream(in);
		      } catch (Exception e) {
		          e.printStackTrace();
		      }
		      return mIcon11;
		  }

		  protected void onPostExecute(Bitmap result) {
		      bmImage.setImageBitmap(result);
		  }
		}

}
