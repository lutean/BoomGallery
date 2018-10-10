package com.prepod.boomgallery;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Антон on 29.06.2016.
 */
public class RecyclerViewHolders extends RecyclerView.ViewHolder{

    public TextView textViewRecItm;
    public ImageView cardImage;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        textViewRecItm = (TextView)itemView.findViewById(R.id.textViewRecItm);
        cardImage = (ImageView)itemView.findViewById(R.id.cardImage);
    }

}