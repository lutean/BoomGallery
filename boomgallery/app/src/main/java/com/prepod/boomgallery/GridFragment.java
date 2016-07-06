package com.prepod.boomgallery;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class GridFragment extends Fragment{

    private GridLayoutManager lLayout;
    RecyclerView rView;
    private ArrayList<String> mImages = new ArrayList<>();
    private ArrayList<Integer> imagesIds = new ArrayList<>();


    public GridFragment() {
        super();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return inflater.inflate(R.layout.fragment_feed, container, false);
        //getActivity().setTitle(getResources().getString(R.string.feed));
        View rootView = inflater.inflate(R.layout.fragment_grid, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final List<ItemObject> rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(getActivity(), 3);

        rView = (RecyclerView)getView().findViewById(R.id.rec_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        rView.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Log.v("My", "!!!");
                        Intent intent = new Intent(getActivity(), FullScreen.class);

                        //intent.putStringArrayListExtra("images", mImages);
                        intent.putExtra("image",rowListItem.get(position).getImage());

                        startActivity(intent);
                    }
                })
        );

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(getActivity(), rowListItem);
        rView.setAdapter(rcAdapter);
    }

    private List<ItemObject> getAllItemList(){

         final Uri thumbUri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI;
         final Uri sourceUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        List<ItemObject> allItems = new ArrayList<ItemObject>();
        // allItems.add(new ItemObject("United States", R.mipmap.ic_launcher));

        String[] list = {MediaStore.Images.Media._ID, MediaStore.Images.Media.DATA};
        String[] listThumb = {MediaStore.Images.Thumbnails._ID, MediaStore.Images.Thumbnails.DATA};

        Cursor cursor = getActivity().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, list, null, null, MediaStore.Images.Media._ID);
        Cursor cursorThumb = MediaStore.Images.Thumbnails.queryMiniThumbnails(getActivity().getContentResolver(),
                MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI,
                MediaStore.Images.Thumbnails.MINI_KIND,
                listThumb);

        if (cursorThumb != null) {
            while (cursorThumb.moveToNext()) {

                String image = cursorThumb.getString(1);
                mImages.add(image);
                Uri imageUri = Uri.parse(image);
                allItems.add(new ItemObject("", imageUri));
            }
        }


                //columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Thumbnails._ID);

       /* if (cursor != null) {
            while (cursor.moveToNext()) {
                int imageId = cursor.getInt(0);
                //Bitmap bm =  MediaStore.Images.Thumbnails.getThumbnail(getActivity().getContentResolver(), hz0, MediaStore.Images.Thumbnails.MINI_KIND, null);
                String image = cursor.getString(1);
                mImages.add(image);
                Uri imageUri = Uri.parse(image);
                allItems.add(new ItemObject("", imageId , imageUri));
            }
        }*/
     /*   cursor.moveToFirst();
        for (int i=0; i < 10; i++) {
            cursor.move(i);
            String hz = cursor.getString(1);

            mImages.add(hz);
            Uri imageUri = Uri.parse(hz);

            allItems.add(new ItemObject("!!!", imageUri));
        }
*/

        return allItems;
    }


}
