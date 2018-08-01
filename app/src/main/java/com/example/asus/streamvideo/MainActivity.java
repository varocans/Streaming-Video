package com.example.asus.streamvideo;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ProgressDialog mDialog;
    VideoView videoView;
    ImageButton btnPlayPause;

    //link video nya
    String videoURL = "https://";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView)findViewById(R.id.videoView);
        btnPlayPause = (ImageButton)findViewById(R.id.btn_play_pause);
        btnPlayPause.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setMessage("Please Wait...");
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();

        try{
            if (!videoView.isPlaying()) {
                Uri uri = Uri.parse(videoURL);
                videoView.setVideoURI(uri);
                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        btnPlayPause.setImageResource(R.drawable.ic_play);
                    }
                });
            }else {
                videoView.pause();
                btnPlayPause.setImageResource(R.drawable.ic_play);
            }
        }catch (Exception ex){

        }
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mDialog.dismiss();
                mediaPlayer.setLooping(true);

                videoView.start();
                btnPlayPause.setImageResource(R.drawable.ic_pause);
            }
        });
    }
}
