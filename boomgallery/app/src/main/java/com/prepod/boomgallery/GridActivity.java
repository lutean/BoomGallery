package com.prepod.boomgallery;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridLayout;

/**
 * Created by Антон on 23.05.2016.
 */
public class GridActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grid);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.grid);

    }
}
