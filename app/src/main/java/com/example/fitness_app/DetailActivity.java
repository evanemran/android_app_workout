package com.example.fitness_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView textView = findViewById(R.id.textView);
        ImageView imageView = findViewById(R.id.imgdt);
        ImageView gifview = findViewById(R.id.imggif);
        Bundle bundle = getIntent().getExtras();
        int resId = bundle.getInt("image");
        imageView.setImageResource(resId);
        textView.setText(getIntent().getStringExtra("param"));


        String tt = textView.getText().toString();
        if (tt.equals("Pushups"))
        {
            gifview.setImageResource(R.drawable.pushupgif);
        }
        else if (tt.equals("Crunches"))
        {
            gifview.setImageResource(R.drawable.crunchesgif);
        }
        else if (tt.equals("Sidebends"))
        {
            gifview.setImageResource(R.drawable.sidebendgif);
        }
        else if (tt.equals("Leglifts"))
        {
            gifview.setImageResource(R.drawable.legliftgif);
        }
        else if (tt.equals("Planks"))
        {
            gifview.setImageResource(R.drawable.plankgif);
        }

    }
}
