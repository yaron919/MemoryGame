package com.yaron.sixbutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.drawable.ColorDrawable;
import android.widget.Button;
import android.view.View;
import  android.graphics.Color;
public class MainActivity extends AppCompatActivity {
    //TODO chagne the button to image button
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private int count =0;
    private Button firstBtn;
    private Button secondBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.button);
        btn2 = (Button)findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button)findViewById(R.id.button4);
        btn5 = (Button)findViewById(R.id.button5);
        btn6 = (Button)findViewById(R.id.button6);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn1.setBackgroundColor(Color.RED);
                count++;
                if (count == 1){
                    firstBtn = btn1;
                    btn1.setEnabled(false);
                }else if (count == 2){
                    secondBtn = btn1;
                    btn1.setEnabled(false);
                    cheackColors();
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn2.setBackgroundColor(Color.BLUE);
                count++;
                if (count == 1) {
                    firstBtn = btn2;
                    btn2.setEnabled(false);
                } else if (count == 2) {
                    secondBtn = btn2;
                    btn2.setEnabled(false);
                    cheackColors();
                }
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn5.setBackgroundColor(Color.RED);
                count++;
                if (count == 1){
                    firstBtn = btn5;
                    btn5.setEnabled(false);
                }else if (count == 2){
                    secondBtn = btn5;
                    btn5.setEnabled(false);
                    cheackColors();
                }
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn3.setBackgroundColor(Color.BLUE);
                count++;
                if (count == 1){
                    firstBtn = btn3;
                    btn3.setEnabled(false);
                }else if (count == 2){
                    secondBtn = btn3;
                    btn3.setEnabled(false);
                    cheackColors();
                }
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn4.setBackgroundColor(Color.GREEN);
                count++;
                if (count == 1){
                    firstBtn = btn4;
                    btn4.setEnabled(false);
                }else if (count == 2){
                    secondBtn = btn4;
                    btn4.setEnabled(false);
                    cheackColors();
                }
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn6.setBackgroundColor(Color.GREEN);
                count++;
                if (count == 1){
                    firstBtn = btn6;
                    btn6.setEnabled(false);
                }else if (count == 2){
                    secondBtn = btn6;
                    btn6.setEnabled(false);
                    cheackColors();
                }
            }
        });

    }
    public void cheackColors() {
        int color1 = ((ColorDrawable) firstBtn.getBackground()).getColor();
        int color2 = ((ColorDrawable) secondBtn.getBackground()).getColor();
        if (color1 == color2) {
            firstBtn.setBackgroundResource(android.R.drawable.star_big_on);
            firstBtn.setText("");
            secondBtn.setBackgroundResource(android.R.drawable.star_big_on);
            secondBtn.setText("");
        } else {
            //TODO change to default button icon
            firstBtn.setBackgroundResource(android.R.drawable.btn_default);
            secondBtn.setBackgroundResource(android.R.drawable.btn_default);
            firstBtn.setEnabled(true);
            secondBtn.setEnabled(true);
        }
        count = 0;
    }
}
 // TODO use the others of activity and not only onCreate
