package com.example.quiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameLevels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_levels);

        SharedPreferences save = getSharedPreferences("Save", MODE_PRIVATE);
        final int level = save.getInt("Level", 1);


        Button button_back = (Button)findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    Intent i = new Intent(GameLevels.this, MainActivity.class);
                    startActivity(i);finish();
                }catch (Exception e){

                }
            }
        });
        //Кнопка для прехода на первый уровень
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    if(level>=1) {
                        Intent i = new Intent(GameLevels.this, Level1.class);
                        startActivity(i);
                        finish();
                    }else {

                    }
                }catch (Exception e){
                    //пусто
                }
            }
        });
        TextView textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(level>=1) {
                    Intent i = new Intent(GameLevels.this, Level2.class);
                    startActivity(i);
                    finish();
                }else {}
            }
        });
        TextView textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(level>=1) {
                Intent i = new Intent(GameLevels.this, Level3.class);
                startActivity(i); finish();
            }else {
                }
            }
        });

        final int[]x={
          R.id.textView1,
          R.id.textView2,
          R.id.textView3,
        };
        for(int i=1; i<level; i++){
            TextView tv = findViewById(x[1]);
            tv.setText(""+(i+1));
        }
    }

    //Системная кнопка "назад"
    @Override
    public void onBackPressed() {
        try {
            Intent i = new Intent(GameLevels.this, MainActivity.class);
            startActivity(i);
            finish();
        } catch (Exception e) {

        }

    }
}