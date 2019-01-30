package com.clug.nanal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class Survey1Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey1);
    }

    public void onlyOneGender(View v){
        CheckBox male=(CheckBox)findViewById(R.id.Male);
        CheckBox female=(CheckBox)findViewById(R.id.Female);
        switch(v.getId()){
            case R.id.Male: female.setChecked(false); break;
            case R.id.Female: male.setChecked(false); break;
        }
    }

    public void goToSurvey2(View v){
        EditText name=(EditText) findViewById(R.id.usernameInput);

        Intent intent=new Intent(this, Survey2Activity.class);
        intent.putExtra("name",name.getText().toString());
        startActivity(intent);
    }
}
