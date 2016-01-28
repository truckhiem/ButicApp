package com.example.bulic;

import android.os.Bundle;

import com.example.bulic.api.APIDefine;
import com.example.bulic.utils.Contanst;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class BulicPlayer extends YouTubeBaseActivity {
	
	private YouTubePlayerView mPlayer;
	private YouTubePlayer.OnInitializedListener onListener;
	private String videoID;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bulic_player);
		initData();
		initView();
		mPlayer.initialize(APIDefine.API_KEY.replace("&key=", ""), onListener);
	}

	private void initData() {
		videoID = getIntent().getStringExtra(Contanst.SendIntent.VideoID);
	}

	private void initView() {
		mPlayer = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
		
		onListener = new YouTubePlayer.OnInitializedListener() {
			
			@Override
			public void onInitializationSuccess(Provider arg0, YouTubePlayer youtubePlayer,
					boolean arg2) {
				youtubePlayer.loadVideo(videoID);
			}
			
			@Override
			public void onInitializationFailure(Provider arg0,
					YouTubeInitializationResult arg1) {
				// TODO Auto-generated method stub
				System.out.println(arg1.toString());
			}
		};
	}
}
