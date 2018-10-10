package com.prepod.boomgallery.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.prepod.boomgallery.BoomGallery;
import com.prepod.boomgallery.R;
import com.prepod.boomgallery.fragments.PagerFragment;
import com.prepod.boomgallery.utils.ImageLoadHelper;

public class PagerActivity extends FragmentActivity {

    private ViewPager mPager;
    private Integer startPosition;
    private PagerAdapter mPagerAdapter;
    private Integer currentImagePosition;
    private LinearLayout llShareBtn;
    private LinearLayout llDeleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);

        llShareBtn = (LinearLayout) findViewById(R.id.pager_share);
        llDeleteBtn = (LinearLayout) findViewById(R.id.pager_delete);
        mPager = (ViewPager) findViewById(R.id.pager);

        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        llShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("My", "");
                shareImage();
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                 startPosition = (Integer) extras.get("imagePosition");
            }
        }
        mPager.setCurrentItem(startPosition);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            currentImagePosition = position;
            PagerFragment myFragment = new PagerFragment();
            Bundle args = new Bundle();
            args.putInt("startPosition", position);
            myFragment.setArguments(args);
            return myFragment;
        }

        @Override
        public int getCount() {
            return BoomGallery.imageList.size();
        }
    }

    private void shareImage(){
        Uri uriToImage = ImageLoadHelper.getContentUri(mPager.getCurrentItem());
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uriToImage);
        shareIntent.setType("image/*");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "Send to"));
    }

}
