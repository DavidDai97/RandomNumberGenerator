package com.example.android.randomnumbergenerater;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class ResetSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        ArrayList<Images> imageList = new ArrayList<Images>();
        imageList.add(new Images(R.mipmap.ic_settings_applications_white_24dp, "Default Setting Button"));
	    imageList.add(new Images(R.mipmap.ic_settings_white_24dp, "White Setting Button"));

	    ImagesAdapter adapter = new ImagesAdapter(this, imageList);
	    ListView listView = (ListView) findViewById(R.id.reset_list_view);
	    listView.setAdapter(adapter);

	    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		    @Override
		    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			    Images currentImage = (Images) parent.getItemAtPosition(position);
			    SharedPreferences sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
			    SharedPreferences.Editor editor = sharedPreferences.edit();
			    editor.putInt("Settings Button", currentImage.getmImageResourceId());
			    editor.commit();
			    Toast.makeText(ResetSettingsActivity.this, "设置完成！", Toast.LENGTH_SHORT).show();
			    Intent setBackground = new Intent(ResetSettingsActivity.this, MainActivity.class);
			    setBackground.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			    setBackground.addFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
			    startActivity(setBackground);
		    }
	    });
/*
	    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		    @Override
		    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			    Images currentImage = (Images) parent.getItemAtPosition(position);
			    Intent setBackground = new Intent(ResetSettingsActivity.this, MainActivity.class);
			    SettingResource resource = new SettingResource(currentImage.getmImageResourceId(), "reset");
			    Bundle bundle = new Bundle();
			    bundle.putSerializable("settingButtonResource", resource);
			    setBackground.putExtras(bundle);
			    //setBackground.putExtra("settingButtonResource", currentImage.getmImageResourceId());
			    setBackground.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			    setBackground.addFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
			    startActivity(setBackground);
		    }
	    });*/
    }
}
