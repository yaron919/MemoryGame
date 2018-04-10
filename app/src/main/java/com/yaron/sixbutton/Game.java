package com.yaron.sixbutton;

import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.os.CountDownTimer;
import java.util.ArrayList;
import android.content.res.TypedArray;
import java.util.Random;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Game extends AppCompatActivity {
    String name;
    int size;
    int timer;
    TextView timerTV;
    TextView nameTV;
    AlertDialog.Builder alert;
    CountDownTimer countDownTimer;
    private ImageView [][] imageViews;
    int imageView1 = -1;
    int winCounter = 0;
    private ArrayList imagesIds1 = new ArrayList();
    private ArrayList imagesIds2= new ArrayList();
    ImageView v1,v2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Bundle extras = getIntent().getExtras();
        name = extras.getString("NAME");
        size = extras.getInt("SIZE");
        timer =extras.getInt("TIMER");

        nameTV = findViewById(R.id.name_tv_ga);
        timerTV = findViewById(R.id.timer_tv_ga);
        nameTV.setText(name);
        timer = timer * 1000;
        alert = new AlertDialog.Builder(this);
        setTimer();
        createButtons();






    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
    private void createButtons(){
        TableLayout table = (TableLayout) findViewById(R.id.table_act_game);
        table.setPadding(8,16,8,0);
        imageViews = new ImageView[size][size];
        for (int i = 0; i < size; i++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    4.0f
            ));
            table.addView(tableRow);
            for (int j = 0; j < size; j++){
                final ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        4.0f
                ));
                imageView.setAdjustViewBounds(true);
                imageView.setCropToPadding(true);
                int id = setPictureForButton((size*size)/2);
                imageView.setId(id);
                imageView.setPadding(10,10,10,10);
                imageView.setMinimumWidth(0);
                imageView.setMaxWidth(0);
                imageView.setMinimumHeight(0);
                imageView.setMaxHeight(0);
                imageView.setImageResource(R.drawable.question_mark);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setIvActivity(imageView, imageView.getId());
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isSame(imageView);
                            }
                        },500);
                    }
                });
                imageViews[i][j] = imageView;
                tableRow.addView(imageView);

            }
        }

    }
    private int setPictureForButton(int size){
        int id = getRandomImage(size);
        // add 2 images of the same picture
        while( imagesIds1.contains(id) && imagesIds2.contains(id)){
            id = getRandomImage(size);
        }
        if(imagesIds1.contains(id)){
            imagesIds2.add(id);
        }else
            imagesIds1.add(id);
        return id;
    }
    private int getRandomImage(int size) {
        TypedArray imgs = getResources().obtainTypedArray(R.array.random_imgs);
        int id = imgs.getResourceId(new Random().nextInt(size), -1); //-1 is default if nothing is found (we don't care)
        imgs.recycle();
        return id;
    }
    private void setIvActivity(ImageView iv,int pictureId){
        Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),pictureId);
        iv.setImageBitmap(originalBitmap);
        iv.setClickable(false);
    }
    private void isSame(ImageView ivPressed){
        if(imageView1 == -1)
        {
            v1=ivPressed;
            imageView1 = ivPressed.getId();
        }
        else if (imageView1 == ivPressed.getId()){ //match!
            v2 = ivPressed;
            winChecker();
            imageView1 = -1;
        }else{ // no match
            v2 = ivPressed;
            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.question_mark);
            v1.setImageBitmap(originalBitmap);
            v1.setClickable(true);
            v2.setImageBitmap(originalBitmap);
            v2.setClickable(true);
            imageView1 = -1;
        }

    }
    private void winChecker() {
        winCounter++;
        if (winCounter == (size * size) / 2) {
            countDownTimer.cancel();
            alert.setMessage("You Win !!")
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
    }
    private void setTimer() {
        countDownTimer = new CountDownTimer(timer, 1000) {

            public void onTick(long millisUntilFinished) {
                timerTV.setText("Timer: "+millisUntilFinished / 1000);
            }

            public void onFinish() {
                timerTV.setText("Timer: 0");
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
    }

}
