package com.clug.nanal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class Survey4_1Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey4_1);
    }

    public void goToSurvey4_2(View v){
        Intent intent=new Intent(this,Survey4_2Activity.class);
        startActivity(intent);
    }

    int getUserTemp(int userTemp, int temp){
        CheckBox cardigan= (CheckBox) findViewById(R.id.cardigan);
        CheckBox zipup= (CheckBox) findViewById(R.id.zipup);
        CheckBox jacket= (CheckBox) findViewById(R.id.jacket);
        CheckBox leather= (CheckBox) findViewById(R.id.leather);
        CheckBox coat= (CheckBox) findViewById(R.id.coat);
        CheckBox padding=(CheckBox) findViewById(R.id.padding);
        userTemp=temp;

        if(cardigan.isChecked())  userTemp+=2;
        if(zipup.isChecked())  userTemp+=3;
        if(jacket.isChecked()) userTemp+=4;
        if(leather.isChecked()) userTemp+=5;
        if(coat.isChecked()) userTemp+=7;
        if(padding.isChecked()) userTemp+=10;

        return userTemp;
    }
}