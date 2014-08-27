package com.wlf.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.wlf.Adapter.StableArrayAdapter;
import com.wlf.MenuActivity.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by jun on 2014/8/25.
 */
public class OpenFolderActivity extends Activity {

    Stack<String> pathStack = new Stack<String>();
    ListView listview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.openfolder_layout);

        listview = (ListView) findViewById(R.id.listview);
        String path = getIntent().getStringExtra("path");
        pathStack.push(path);

        upListView(path, listview);
    }

    public void upListView (final String path, final ListView listview) {

        File file = new File(path);
        File[] files = file.listFiles();
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < files.length; ++i) {
            list.add(files[i].getName());
        }

        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                File nextFile = new File(pathStack.peek()+"/"+item);
                if (nextFile.isDirectory()) {
                    pathStack.push(pathStack.peek() + "/" + item);
                    upListView(pathStack.peek(), listview);
                } else {
                    String path = pathStack.peek()+"/"+item;
                    if (path.endsWith(".mp4")) {
                        Intent intent = new Intent(OpenFolderActivity.this, ScreenActivity.class);
                        intent.putExtra("file", path);
                        startActivity(intent);
                    } else {
                        Toast.makeText(OpenFolderActivity.this, "Not support this format", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (pathStack.size() == 1) {
            pathStack.pop();
            super.onBackPressed();
        } else {
            pathStack.pop();
            upListView(pathStack.peek(), listview);
        }
    }
}