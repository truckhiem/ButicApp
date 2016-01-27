package com.bulic.adapter;

import java.io.InputStream;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bulic.model.YoutubeResponseModel;
import com.example.bulic.R;

public class SongItemAdapter extends LinearLayout{
	
	public SongItemAdapter(Context context) {
		super(context);
		
	}
	
	public SongItemAdapter(Context context, YoutubeResponseModel.Item songModel){
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
