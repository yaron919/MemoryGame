package com.yaron.sixbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;


public class Starter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter);
    }

    public void newGame(View v)
    {
        EditText name = (EditText)findViewById(R.id.name_ed_sa);
        EditText age = (EditText)findViewById(R.id.age_ed_sa);
        Intent intent = new Intent(this,Levels.class);
        intent.putExtra("NAME",name.getText().toString());
        intent.putExtra("AGE",age.getText().toString());
        startActivity(intent);
    }
}
