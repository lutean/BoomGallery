package com.prepod.boomgallery;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;

public class FeedListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<String> pictures;

    public ImageView delBtn;


    public FeedListAdapter(Activity activity, List<String> feedItems) {
        this.activity = activity;
        this.pictures = feedItems;
    }

    @Override
    public int getCount() {
        return pictures.size();
    }

    @Override
    public Object getItem(int location) {
        return pictures.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.item, null);


        TextView name = (TextView) convertView
                .findViewById(R.id.name);

        ImageView imageView = (ImageView) convertView
                .findViewById(R.id.image);

        LinearLayout itemLL = (LinearLayout) convertView.findViewById(R.id.itemLL);




        String item = pictures.get(position);

        name.setText("");

        if (position % 2 == 0){
            itemLL.setBackgroundColor(Color.parseColor("#EEFCFF"));
        }else{
            itemLL.setBackgroundColor(Color.parseColor("#ffffff"));
        }







        // Feed image
        if (item != null) {
            Uri imageUri = Uri.parse(item);
            imageView.setImageURI(imageUri);
            imageView.setVisibility(View.VISIBLE);

        } else {
            imageView.setVisibility(View.GONE);
        }

        return convertView;
    }

}