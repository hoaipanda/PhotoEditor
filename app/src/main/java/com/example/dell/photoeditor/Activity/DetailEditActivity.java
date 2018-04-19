package com.example.dell.photoeditor.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dell.photoeditor.AppUtils;
import com.example.dell.photoeditor.Contains;
import com.example.dell.photoeditor.Data.Filter;
import com.example.dell.photoeditor.R;

import java.util.ArrayList;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageFilter;
import jp.co.cyberagent.android.gpuimage.GPUImageView;

public class DetailEditActivity extends AppCompatActivity implements GPUImageView.OnPictureSavedListener {
    private Bitmap bitmapImage;
    private ImageView imEdit;
    private RecyclerView rvFilter;
    private Toolbar toolbar;
    private String nameFilter;
    private int icon;
    private TextView tvNameFilter;
    private ArrayList<Filter> list = new ArrayList<>();
    private GPUImageFilter mFilter;
    private GPUImageView mGPUImageView ;
    private GPUImage mGPUImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_edit);

        initView();

        getData();
        Glide.with(DetailEditActivity.this)
                .load(bitmapImage)
                .into(imEdit);
        list = AppUtils.getFilter();
        if (icon != -1) {
            for (Filter filter : list) {
                if (filter.getIcon() == icon) {
                    nameFilter = filter.getName();
                }
            }
            tvNameFilter.setText(nameFilter);
        }
        mGPUImage = new GPUImage(this);
    }

    private void initView() {
        imEdit = (ImageView) findViewById(R.id.imageEdit);
        rvFilter = (RecyclerView) findViewById(R.id.rvFilter);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        tvNameFilter = (TextView) toolbar.findViewById(R.id.tvNameFilter);
    }

    private void getData() {
        Intent intent = getIntent();
        icon = intent.getIntExtra(Contains.ICON, -1);
        bitmapImage = intent.getParcelableExtra(Contains.IMAGEBITMAP);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onPictureSaved(Uri uri) {

    }
}
