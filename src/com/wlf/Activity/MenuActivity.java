package com.wlf.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.wlf.MenuActivity.R;

//import com.wlf.Activity.R;

public class MenuActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    final String storagePath = "/storage";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(MenuActivity.this, OpenFolderActivity.class);
                intent.putExtra("path", storagePath);
                startActivity(intent);
            }
        });
    }
}
