package com.example.dell.photoeditor.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.FileProvider;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.dell.photoeditor.BuildConfig;
import com.example.dell.photoeditor.Contains;
import com.example.dell.photoeditor.R;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import static com.example.dell.photoeditor.AppUtils.PERMISSIONS;

public class MainActivity extends AppCompatActivity implements  BaseSliderView.OnSliderClickListener, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private Intent startIntent;
    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;
    private NavigationView navigationView;
    private RelativeLayout choseImage, takePhoto;
    static final int REQUEST_TAKE_PHOTO = 2,REWQUEST_CHOSE_PHOTO = 1;
    private String pathTakePhoto;
    private SliderLayout sliderLayout;
    private HashMap<String, String> Hash_file_maps;
    public static int count = 0;
    private Uri uriTakePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startIntent = getIntent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (String permission : PERMISSIONS) {
                if (checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(PERMISSIONS, 0);
                    return;
                }
            }
            initView();
        } else {
            initView();
        }
        showDrawer();
        setSlider();

        final String dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/picFolder/";
        File newdir = new File(dir);
        newdir.mkdirs();


    }

    private void setSlider() {
        Hash_file_maps = new HashMap<String, String>();

        sliderLayout = (SliderLayout) findViewById(R.id.slider);

        Hash_file_maps.put("1", "http://media2.tiin.vn//archive/images/2016/10/01/112559_10.jpg");
        Hash_file_maps.put("2", "http://s1.img.yan.vn/YanNews/2167221/201608/20160827-082140-9_600x627.jpg");
        Hash_file_maps.put("3", "http://media2.tiin.vn//archive/images/2016/10/01/112559_3.jpg");
        Hash_file_maps.put("4", "https://i.pinimg.com/originals/1a/cf/60/1acf60d40eca578ceda0a5cc8fcfd205.jpg");
        Hash_file_maps.put("5", "http://s1.img.yan.vn/YanNews/2167221/201606/20160606-021544-9a_591x593.jpg");

        for (String name : Hash_file_maps.keySet()) {

            TextSliderView textSliderView = new TextSliderView(MainActivity.this);
            textSliderView
//                    .description(name)
                    .image(Hash_file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            textSliderView.bundle(new Bundle());
//            textSliderView.getBundle()
//                    .putString("extra",name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
    }

    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        navigationView = (NavigationView) findViewById(R.id.navigation);
        mDrawer = (DrawerLayout) findViewById(R.id.main);
        choseImage = (RelativeLayout) findViewById(R.id.choseImage);
        takePhoto = (RelativeLayout) findViewById(R.id.takePhoto);

        choseImage.setOnClickListener(this);
        takePhoto.setOnClickListener(this);
    }

    private void showDrawer() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, 0, 0);
        mDrawer.addDrawerListener(mToggle);
        mToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int result : grantResults) {
            if (result != PackageManager.PERMISSION_GRANTED) {
                finish();
                return;
            }
        }
        finish();
        startActivity(startIntent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.choseImage:
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, REWQUEST_CHOSE_PHOTO);
                break;
            case R.id.takePhoto:
               takePhoto();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REWQUEST_CHOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        final Uri imageUri = data.getData();
                        String image = String.valueOf(imageUri);
                        Intent intent = new Intent(MainActivity.this, EditActivity.class);
                        intent.putExtra(Contains.IMAGE, image);
                        intent.putExtra(Contains.ISTAKEPHOTO,false);
                        startActivity(intent);
                    }
                }
                break;
            case REQUEST_TAKE_PHOTO:
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uriTakePhoto);
                    Intent intent = new Intent(MainActivity.this,EditActivity.class);
                    intent.putExtra(Contains.IMAGEBITMAP, bitmap);
                    intent.putExtra(Contains.ISTAKEPHOTO,true);
                    startActivity(intent);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

        }



    }

    private void takePhoto() {
        try{
            File f=new File(Contains.FORDER_APP);
            if (!f.exists()) {
                f.mkdir();
            }else {
                Log.e("file exit", "exit " );
            }
            File file=new File(f,Contains.IMG
                    + Contains.PICTURE_NAME_DATE_FORMAT.format(new Date())
                    + Contains.IMAGE_TYPE);
            Intent camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

            if (Build.VERSION.SDK_INT >=24){
                Uri uri=FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".provider", file);
                camera.putExtra("output", uri);
            }else {
                camera.putExtra("output", Uri.fromFile(file));
            }
            startActivityForResult(camera, REQUEST_TAKE_PHOTO);
            uriTakePhoto=Uri.fromFile(file);
        }catch (Exception e){
            Log.e("erro", e.getMessage() );
            Toast.makeText(this,"Error, please try again",Toast.LENGTH_LONG).show();
        }
    }




    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
}
