package com.yaron.sixbutton;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.os.CountDownTimer;
import android.content.Intent;

public class Game extends AppCompatActivity {
    String name;
    int size;
    int timer;
    TextView timerTV;
    TextView nameTV;
    Intent intent ;
    AlertDialog.Builder alert;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle extras = getIntent().getExtras();
        name = extras.getString("NAME");
        size = extras.getInt("SIZE");
        timer =extras.getInt("TIMER");
        nameTV = (TextView) findViewById(R.id.name_tv_ga);
        timerTV = (TextView) findViewById(R.id.timer_tv_ga);
        nameTV.setText(name);
        timer = timer * 1000;
        intent = new Intent(this,Levels.class);
        alert = new AlertDialog.Builder(this);
        countDownTimer = new CountDownTimer(timer, 1000) {

            public void onTick(long millisUntilFinished) {
                timerTV.setText("Timer: "+millisUntilFinished / 1000);
            }

            public void onFinish() {
                alert.setMessage("You are lost !!")
                        .setPositiveButton("Try again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                finish();
                            }
                        })
                        .create();
                alert.show();
            }
        }.start();
        //TODO to decide how to woke with gridlayout or tablelayout
        createButtons();
/*
        GridLayout gl = (GridLayout)findViewById(R.id.gl);
        gl.setColumnCount(size);
        for (int i = 0;i<size;i++) {

            for (int j=0;j<size;j++){
                Button btn = new Button(this);
                btn.setId(i*10+j);
                btn.setText("Button"+i+j);
                gl.addView(btn);
            }
        }
*/


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
    private void createButtons(){

        TableLayout table = (TableLayout) findViewById(R.id.table_act_game);
        for (int i = 0; i < size;i++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f
            ));
            table.addView(tableRow);
            for (int j=0; j<size;j++){
                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f
                ));
                tableRow.addView(button);
            }
        }
    }
}
