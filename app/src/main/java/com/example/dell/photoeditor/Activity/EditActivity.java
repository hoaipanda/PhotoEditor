package com.example.dell.photoeditor.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.dell.photoeditor.Adapter.FilterAdapter;
import com.example.dell.photoeditor.AppUtils;
import com.example.dell.photoeditor.Contains;
import com.example.dell.photoeditor.Data.Filter;
import com.example.dell.photoeditor.R;
import com.xiaopo.flying.sticker.StickerView;

import java.util.ArrayList;

import static com.example.dell.photoeditor.AppUtils.getFilter;

public class EditActivity extends AppCompatActivity {
    private String image;
    private Bitmap bitmapImage;
    private boolean isCamera;
    private ImageView imEdit;
    private RecyclerView rvFilter;
    private FilterAdapter adapter;
    private ArrayList<Filter> listFilter = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        imEdit = (ImageView) findViewById(R.id.imageEdit);
        rvFilter = (RecyclerView) findViewById(R.id.rvFilter);
        getData();
        listFilter = AppUtils.getFilter();
        updateRecyclerview();

        adapter.setOnItemClickedListener(new FilterAdapter.OnItemClickedListener() {
            @Override
            public void onItemClick(int icon) {
                Intent intent = new Intent(EditActivity.this,DetailEditActivity.class);
                intent.putExtra(Contains.ICON,icon);
                intent.putExtra(Contains.IMAGEBITMAP,bitmapImage);
                startActivityForResult(intent,1);
            }
        });
    }

    private void updateRecyclerview() {
        adapter = new FilterAdapter(listFilter);
        LinearLayoutManager linearLayout = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        rvFilter.setAdapter(adapter);
        rvFilter.setLayoutManager(linearLayout);
    }

    private void getData(){
        Intent intent = getIntent();

        isCamera = intent.getBooleanExtra(Contains.ISTAKEPHOTO,false);
        if(isCamera){
            bitmapImage = intent.getParcelableExtra(Contains.IMAGEBITMAP);
            Glide.with(EditActivity.this)
                    .load(bitmapImage)
                    .into(imEdit);
        }else {
            image = intent.getStringExtra(Contains.IMAGE);
            bitmapImage = intent.getParcelableExtra(Contains.IMAGEBITMAP);
            Glide.with(EditActivity.this)
                    .load(image)
                    .into(imEdit);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1){

        }
    }
}
