package com.clug.nanal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class Survey4_3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey4_3);
    }

    public void goToSurvey5(View v){
        Intent intent=new Intent(this, Survey5Activity.class);
        startActivity(intent);
    }

    int getUserTemp(int userTemp, int temp){
        CheckBox pants=(CheckBox)findViewById(R.id.pants);
        CheckBox thickPants=(CheckBox)findViewById(R.id.thick_pants);
        CheckBox stocking=(CheckBox)findViewById(R.id.stocking);
        CheckBox leggings=(CheckBox)findViewById(R.id.leggings);
        userTemp=temp;

        if(pants.isChecked()) userTemp+=1;
        if(leggings.isChecked()) userTemp+=2;
        if(thickPants.isChecked()) userTemp+=3;
        if(stocking.isChecked()) userTemp+=1;

        return userTemp;
    }
}
