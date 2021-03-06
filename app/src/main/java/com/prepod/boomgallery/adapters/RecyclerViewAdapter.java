package com.prepod.boomgallery.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prepod.boomgallery.R;
import com.prepod.boomgallery.RecyclerViewHolders;

import java.util.List;

/**
 * Created by Антон on 29.06.2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolders> {

    private List<ItemImage> itemList;
    private Context context;

    public RecyclerViewAdapter(Context context, List<ItemImage> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecyclerViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        RecyclerViewHolders rcv = new RecyclerViewHolders(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolders holder, int position) {
        holder.textViewRecItm.setText(itemList.get(position).getImageName());
        holder.cardImage.setImageURI(itemList.get(position).getThumbImageUri());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}