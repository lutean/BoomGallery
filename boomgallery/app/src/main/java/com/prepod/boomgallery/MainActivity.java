package com.prepod.boomgallery;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecAdapter recAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> list = getLsit();

       // recyclerView = (RecyclerView) findViewById(R.id.recyclerList);
       // recyclerView.setHasFixedSize(true);

        //layoutManager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(layoutManager);

       // recAdapter = new RecAdapter(list);
       //recyclerView.setAdapter(recAdapter);

    }

    private ArrayList<String> getLsit(){
        ArrayList<String> list = new ArrayList<>();
        for (int i=0; i < 100; i++){
            list.add(i, "item" + i);
        }
        list.add(list.size(), "ITEM" + list.size() + 1);
        return list;
    }
}
