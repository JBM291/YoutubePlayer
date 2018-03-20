package com.example.mills.b.joshua.youtubeplayer;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeActivity extends YouTubeBaseActivity
    implements YouTubePlayer.OnInitializedListener{
    static final String  GOOGLE_API_KEY = "";//TODO: add api key";
    static final String YOUTUBE_VIDEO_ID = "vrN4TOY8rSw";
    static final String YOUTUBE_PLAYLIST = "PL7xvYrkzD7N_fpt9C1CWq6m_91YQmBd55";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ConstraintLayout layout = (ConstraintLayout)getLayoutInflater().inflate(R.layout.activity_youtube, null);
        setContentView(layout);

        YouTubePlayerView playerView = new YouTubePlayerView(this);
        playerView.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(playerView);
        playerView.initialize(GOOGLE_API_KEY, this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if(!wasRestored){
            youTubePlayer.cueVideo(YOUTUBE_VIDEO_ID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        final int REQUEST_CODE = 1;

        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,REQUEST_CODE).show();
        }else{
            String errorMessage = String.format("There was an error initializing the YoutubePlayer (%1$s)", youTubeInitializationResult.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    private YouTubePlayer.PlaybackEventListener playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
        @Override
        public void onPlaying() {


            Toast.makeText(YoutubeActivity.this, "Now playing",Toast.LENGTH_LONG);
        }

        @Override
        public void onPaused() {
            Toast.makeText(YoutubeActivity.this, "Player paused",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onStopped() {
            Toast.makeText(YoutubeActivity.this, "Player Stopped",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onBuffering(boolean b) {
            Toast.makeText(YoutubeActivity.this, "Player Buffering",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onSeekTo(int i) {
            Toast.makeText(YoutubeActivity.this, "Seeking to",Toast.LENGTH_LONG).show();
        }
    };

    private YouTubePlayer.PlayerStateChangeListener playerStateChangeListener = new YouTubePlayer.PlayerStateChangeListener() {
        @Override
        public void onLoading() {
            Toast.makeText(YoutubeActivity.this, "Loading",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onLoaded(String s) {
            Toast.makeText(YoutubeActivity.this, "Loaded",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onAdStarted() {
            Toast.makeText(YoutubeActivity.this, "Ad Started",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onVideoStarted() {
            Toast.makeText(YoutubeActivity.this, "Seeking to",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onVideoEnded() {
            Toast.makeText(YoutubeActivity.this, "Video ended",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onError(YouTubePlayer.ErrorReason errorReason) {
            Toast.makeText(YoutubeActivity.this, "Error",Toast.LENGTH_LONG).show();
        }
    };
}
