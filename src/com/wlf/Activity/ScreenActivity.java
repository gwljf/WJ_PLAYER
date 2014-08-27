package com.wlf.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.*;
import com.wlf.LocalCodec.AudioDecoderThread;
import com.wlf.LocalCodec.VideoDecoderThread;
import com.wlf.MenuActivity.R;

/**
 * Created by jun on 2014/8/26.
 */
public class ScreenActivity extends Activity implements SurfaceHolder.Callback {

    private VideoDecoderThread mVideoDecoder;
    private AudioDecoderThread mAudioDecoder;
    private static String file;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.screen_layout);
//        ActionBar actionBar = getActionBar();
//        actionBar.hide();

        SurfaceView surfaceView = new SurfaceView(this);
        surfaceView.getHolder().addCallback(this);
        setContentView(surfaceView);
        file = getIntent().getStringExtra("file");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.local__codec_, menu);
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        // TODO Auto-generated method stub

        mVideoDecoder = new VideoDecoderThread();
        mAudioDecoder = new AudioDecoderThread();

        if (mVideoDecoder != null) {
            if (mVideoDecoder.init(holder.getSurface(), file) && mAudioDecoder.init(file) ) {
                mVideoDecoder.start();
                mAudioDecoder.start();
            } else {
                mVideoDecoder = null;
                mAudioDecoder = null;
            }
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
        if (mVideoDecoder != null) {
            mVideoDecoder.close();
        }
        if (mAudioDecoder != null) {
            mAudioDecoder.close();
        }
        super.onBackPressed();
    }
}