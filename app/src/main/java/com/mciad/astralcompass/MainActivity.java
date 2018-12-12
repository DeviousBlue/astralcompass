package com.mciad.astralcompass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupThings();
    }


    private void setupThings(){
        Button nextButton = findViewById(R.id.btn_next);
        nextButton.setOnClickListener(new OnClickListener(){
            public void onClick (View v){
                nextPage(v);
            }
        });
    }

    private void nextPage(View v) {
        Intent intent = new Intent(this, HeroActivity.class);
        startActivity(intent);
    }
}


