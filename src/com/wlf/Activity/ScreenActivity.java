package com.wlf.Activity;

import android.app.Activity;
import android.os.Bundle;
import com.wlf.MenuActivity.R;

/**
 * Created by jun on 2014/8/26.
 */
public class ScreenActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_layout);
    }
}