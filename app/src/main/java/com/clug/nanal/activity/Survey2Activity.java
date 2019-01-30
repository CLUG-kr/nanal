package com.clug.nanal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class Survey2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey2);

        Intent i=getIntent();
        String name=i.getExtras().getString("name");
        TextView cold=(TextView) findViewById(R.id.textView2);
        TextView hot=(TextView) findViewById(R.id.textView4);

        cold.setText(name+"님은 추위를 잘 타시나요?");
        hot.setText(name+"님은 더위를 잘 타시나요?");
    }

    public void goToSurvey3(View v){
        Intent intent=new Intent(this,Survey3Activity.class);
        startActivity(intent);
    }

    int getType(int type){
        CheckBox yesCold=(CheckBox) findViewById(R.id.YesCold);
        CheckBox noCold=(CheckBox) findViewById(R.id.NoCold);
        CheckBox yesHot=(CheckBox) findViewById(R.id.YesHot);
        CheckBox noHot=(CheckBox) findViewById(R.id.YesHot);

        if(yesCold.isChecked()&&noHot.isChecked()) type=1;
        else if(noCold.isChecked()&&yesHot.isChecked()) type=2;
        else if(yesCold.isChecked()&&yesHot.isChecked()) type=3;
        else type=4;

        return type;
    }
    public void onlyOneChecked(View v){
        CheckBox yesCold=(CheckBox) findViewById(R.id.YesCold);
        CheckBox noCold=(CheckBox) findViewById(R.id.NoCold);
        CheckBox yesHot=(CheckBox) findViewById(R.id.YesHot);
        CheckBox noHot=(CheckBox) findViewById(R.id.NoHot);

        switch(v.getId()){
            case R.id.YesCold: noCold.setChecked(false); break;
            case R.id.NoCold: yesCold.setChecked(false); break;
            case R.id.YesHot: noHot.setChecked(false); break;
            case R.id.NoHot: yesHot.setChecked(false); break;
        }
    }
}
