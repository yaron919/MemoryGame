package com.yaron.sixbutton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

public class Levels extends AppCompatActivity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels);
        Bundle extras = getIntent().getExtras();
        String name = extras.getString("NAME");
        String age = extras.getString("AGE");
        TextView msg = (TextView) findViewById(R.id.hellomsg_tv_la);
        msg.setText("hello, "+name+" your age "+age+"\n Please choose your level:");
        intent = new Intent(this,Game.class);
        intent.putExtra("NAME",name);
    }

    public void easyLevel(View v)
    {
        intent.putExtra("SIZE",2);
        intent.putExtra("TIMER",30);
        startActivity(intent);
    }
    public void mediumLevel(View v)
    {
        intent.putExtra("SIZE",4);
        intent.putExtra("TIMER",45);
        startActivity(intent);
    }
    public void hardLevel(View v)
    {
        intent.putExtra("SIZE",6);
        intent.putExtra("TIMER",60);
        startActivity(intent);
    }
}
