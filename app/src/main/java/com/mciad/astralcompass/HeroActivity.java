package com.mciad.astralcompass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HeroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        setupResources();
    }

    private void setupResources(){
        ImageView ironView = findViewById(R.id.ironImgView);
        ironView.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                nextPage(v, Hero.IRONCLAD);
            }
        });

        ImageView silentView = findViewById(R.id.silentImgView);
        silentView.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                nextPage(v, Hero.SILENT);
            }
        });

        ImageView defectView = findViewById(R.id.defectImgView);
        defectView.setOnClickListener(new View.OnClickListener(){
            public void onClick (View v){
                nextPage(v, Hero.DEFECT);
            }
        });
    }

    private void nextPage(View v, Hero option) {
        Intent intent = new Intent(this, GraphActivity.class);
        intent.putExtra("char", option);
        startActivity(intent);
    }

}
