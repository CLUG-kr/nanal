package com.clug.nanal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class Survey4_2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey4_2);
    }

    public void goToSurvey4_3(View v){
        Intent intent=new Intent(this, Survey4_3Activity.class);
        startActivity(intent);
    }

    int getUserTemp(int userTemp, int temp){
        CheckBox tshirt= (CheckBox)findViewById(R.id.tshirt);
        CheckBox shirt= (CheckBox)findViewById(R.id.shirt);
        CheckBox mtm= (CheckBox)findViewById(R.id.mtm);
        CheckBox hood= (CheckBox)findViewById(R.id.hood);
        CheckBox knit= (CheckBox)findViewById(R.id.knit);
        CheckBox polar= (CheckBox)findViewById(R.id.polar_t);
        userTemp=temp;

        if(tshirt.isChecked()) userTemp+=1;
        if(shirt.isChecked()) userTemp+=1;
        if(mtm.isChecked()) userTemp+=3;
        if(hood.isChecked()) userTemp+=3;
        if(knit.isChecked()) userTemp+=5;
        if(polar.isChecked()) userTemp=7;

        return userTemp;
    }
}
