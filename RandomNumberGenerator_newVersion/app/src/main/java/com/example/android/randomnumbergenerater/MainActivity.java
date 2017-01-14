package com.example.android.randomnumbergenerater;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	    ImageButton setting = (ImageButton) findViewById(R.id.settings);
	    ImageView background = (ImageView) findViewById(R.id.background_image);
	    SharedPreferences sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
	    int settingResource = sharedPreferences.getInt("Settings Button", R.mipmap.ic_settings_applications_white_24dp);
	    int backgroundResource = sharedPreferences.getInt("Background", R.drawable.background_1);
	    setting.setImageResource(settingResource);
	    background.setImageResource(backgroundResource);
    }

    public void settings(View view){
        Intent openSettings = new Intent(this, SettingsActivity.class);
        startActivity(openSettings);
        Toast.makeText(MainActivity.this, "打开设置！", Toast.LENGTH_SHORT).show();
    }

    public void submitGeneration(View view){
        EditText lo = (EditText) findViewById(R.id.lowerBound);
        EditText hi = (EditText) findViewById(R.id.upperBound);
        EditText num = (EditText) findViewById(R.id.numberToGenerate);
        TextView showingBox = (TextView) findViewById(R.id.AnswerBox);
        String loBound = lo.getText().toString();
        String hiBound = hi.getText().toString();
        int loReIndex = loBound.indexOf("\n");
        int hiReIndex = hiBound.indexOf("\n");
        loBound = loBound.substring(0, loReIndex-1);
        hiBound = hiBound.substring(0, hiReIndex-1);
        int lowerBound = Integer.parseInt(loBound);
        int upperBound = Integer.parseInt(hiBound);
        int numberToGenerate = Integer.parseInt(num.getText().toString());
        if((lowerBound > upperBound && numberToGenerate != 0) || (lowerBound == upperBound && lowerBound == 0 && numberToGenerate != 0) ){
            showingBox.setText("请确保最大数字大于最小数字，不然无法产生随机数");
        }
        else {
            String result = generateRandomNum(lowerBound, upperBound, numberToGenerate);
            showingBox.setText(result);
        }
    }

    public void resetAll(View view){
        EditText lo = (EditText) findViewById(R.id.lowerBound);
        EditText hi = (EditText) findViewById(R.id.upperBound);
        EditText num = (EditText) findViewById(R.id.numberToGenerate);
        TextView showingBox = (TextView) findViewById(R.id.AnswerBox);
        lo.setText("0");
        hi.setText("0");
        num.setText("0");
        showingBox.setText("");
    }

    private String generateRandomNum(int lowerBound, int upperBound, int numberToGenerate){
        String result = "";
        int i;
        for(i = 0; i < numberToGenerate; i++){
            int newNum = 0;
            while(newNum > upperBound || newNum == 0){
                newNum = (int) (Math.random() * (Math.random() * 300 * 100));
                newNum = lowerBound + newNum;
            }
            if(numberToGenerate == 1){
                result = result + newNum;
            }
            else if(i == numberToGenerate-1){
                result = result + newNum;
            }
            else{
                result = result + newNum + ", ";
            }
        }
        return result;
    }

}
