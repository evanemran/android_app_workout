package com.example.fitness_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    private TextView textView;
    private Button btnstart;
    private CountDownTimer countDownTimer;
    private long time = 300000;
    private ImageView imgabt;
    private boolean timerrunning;
    int flag = 0;
    MediaPlayer player,player2,player3;
    Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgabt = findViewById(R.id.imgabt);


        textView = findViewById(R.id.txttime);
        btnstart = findViewById(R.id.btnstart);

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //delay with starting audio
                if (flag == 0)
                {
                    play2();
                    btnstart.setEnabled(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            starttimer();
                            btnstart.setEnabled(true);
                            play();
                            flag++;
                        }
                    }, 5000);
                }
                else if (flag != 0)
                    startstop();

            }
        });
        updatetimer();

        imgabt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ClientActivity.class);
                startActivity(intent);

            }
        });



        models = new ArrayList<>();
        models.add(new Model(R.drawable.pushup, "Pushups", "5 min"));
        models.add(new Model(R.drawable.crunch, "Crunches", "5 min"));
        models.add(new Model(R.drawable.sidebend, "Sidebends", "5 min"));
        models.add(new Model(R.drawable.leglift, "Leglifts", "5 min"));
        models.add(new Model(R.drawable.plank, "Planks", "1 min"));

        adapter = new Adapter(models, this);
        viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4),
                getResources().getColor(R.color.color5)
        };

        colors = colors_temp;


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() -1) && position < (colors.length -1))
                {
                    viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset, colors[position], colors[position + 1]));
                }
                else
                {
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public void startstop()
    {
        if (timerrunning)
        {
            stoptimer();
            pause();

        }
        else
        {
            starttimer();
            play();
        }
    }
    public void starttimer()
    {
        countDownTimer = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time = millisUntilFinished;
                updatetimer();

            }

            @Override
            public void onFinish() {
                play3();

            }
        }.start();
        btnstart.setText("Pause");
        timerrunning = true;

    }
    public void stoptimer()
    {
        countDownTimer.cancel();
        btnstart.setText("Start");
        timerrunning = false;

    }

    public void updatetimer()
    {
        int minutes = (int) time / 60000;
        int sec = (int) time % 60000 / 1000;
        String timeleft;
        timeleft = "" + minutes;
        timeleft += ":" ;

        if (sec < 10) timeleft += "0";

        timeleft += sec;

        textView.setText(timeleft);
    }

    public void play()
    {
        if (player==null)
        {
            player = MediaPlayer.create(this, R.raw.pop);
        }

        player.start();
    }
    public void play2()
    {
        if (player2==null)
        {
            player2 = MediaPlayer.create(this, R.raw.gm);
        }

        player2.start();
    }
    public void play3()
    {
        if (player3==null)
        {
            player3 = MediaPlayer.create(this, R.raw.relax);
        }

        player3.start();
    }
    public void pause()
    {
        if (player!=null)
        {
            player.pause();
        }
    }
    private void stop()
    {
        stopplayer();
    }
    private void stopplayer()
    {
        if (player!=null)
        {
            player.release();
            player = null;
            //Toast.makeText(this, "Mediaplayer released", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        //stopplayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopplayer();
    }
}
