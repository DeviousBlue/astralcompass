package com.mciad.astralcompass;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class GraphActivity extends AppCompatActivity {

    protected Hero hero;



    private ViewPager viewPager;
    private SpireStatePagerAdapter adapter;


    public GraphActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_main);

        setupViewPager();
        hero = (Hero) getIntent().getExtras().get("char");


    }

    @Override
    protected void onStart(){
        super.onStart();
    }


    private void setupViewPager(){
        viewPager = findViewById(R.id.fragContainer);
        adapter = new SpireStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new GraphFragment(), "Graph");
        adapter.addFragment(new GraphFragmentBar(), "Ironclad");
        viewPager.setAdapter(adapter);
    }

    public void setViewFragment(int fragNumber){
        viewPager.setCurrentItem(fragNumber);
    }

}
