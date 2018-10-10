package com.prepod.boomgallery.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prepod.boomgallery.BoomGallery;
import com.prepod.boomgallery.Consts;
import com.prepod.boomgallery.R;
import com.prepod.boomgallery.RecyclerItemClickListener;
import com.prepod.boomgallery.activities.FullScreenActivity;
import com.prepod.boomgallery.activities.PagerActivity;
import com.prepod.boomgallery.adapters.ItemImage;
import com.prepod.boomgallery.adapters.RecyclerViewAdapter;
import com.prepod.boomgallery.utils.GalleryLoader;

import java.util.ArrayList;
import java.util.List;

public class GridFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<ItemImage>> {

    private GridLayoutManager lLayout;
    RecyclerView rView;
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<Integer> imagesIds = new ArrayList<>();
    //private List<ItemImage> imageList;
    private RecyclerViewAdapter rcAdapter;

    public GridFragment() {
        super();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_grid, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Configuration config = getResources().getConfiguration();
        if (config.orientation == Configuration.ORIENTATION_PORTRAIT) {
            lLayout = new GridLayoutManager(getActivity(), Consts.GRID_SPAN_COUNT);
        } else {
            lLayout = new GridLayoutManager(getActivity(), Consts.GRID_SPAN_COUNT_LAND);
        }


        rView = (RecyclerView)getView().findViewById(R.id.rec_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        rView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), rView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Log.v("My", "!!!");
                        Intent intent = new Intent(getActivity(), PagerActivity.class);
                        intent.putExtra("imagePosition", position);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Log.v("My", "" + view);
                    }

                })
        );

        rView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

        rcAdapter = new RecyclerViewAdapter(getActivity(), BoomGallery.imageList);
        rView.setAdapter(rcAdapter);


        if(ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    Consts.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

        } else {
            getLoaderManager().initLoader(0, null, this);
        }

    }

    @Override
    public Loader<List<ItemImage>> onCreateLoader(int id, Bundle args) {
        return new GalleryLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<ItemImage>> loader, List<ItemImage> data) {
        BoomGallery.imageList.clear();

        for(int i = 0; i < data.size(); i++){
            ItemImage item = data.get(i);
            BoomGallery.imageList.add(item);
        }
        rcAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<ItemImage>> loader) {
        BoomGallery.imageList.clear();
        rcAdapter.notifyDataSetChanged();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            lLayout.setSpanCount(Consts.GRID_SPAN_COUNT);
        } else {
            lLayout.setSpanCount(Consts.GRID_SPAN_COUNT_LAND);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Consts.MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLoaderManager().initLoader(0, null, this);

                } else {

                }
                return;
            }
        }
    }

}
