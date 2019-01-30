package com.clug.nanal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Survey3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey3);
    }

    public void goToSurvey4_1(View v){
        Intent intent=new Intent(this,Survey4_1Activity.class);
        startActivity(intent);
    }
}
