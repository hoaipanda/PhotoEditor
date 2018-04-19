package com.example.dell.photoeditor;

import android.os.Environment;

import java.text.SimpleDateFormat;

/**
 * Created by dell on 4/19/2018.
 */

public class Contains {
    public static final String IMAGE = "image";
    public static final String ICON = "icon";
    public static final String IMAGEBITMAP = "imagebitmap";
    public static final String ISTAKEPHOTO = "istakephoto";
    public static final String FORDER_APP = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Photo Editer/";
    public static final String IMG="IMG_";
    public static final String IMAGE_TYPE =".JPG";
    public static SimpleDateFormat PICTURE_NAME_DATE_FORMAT= new SimpleDateFormat("yyyyMMdd_HHmmss");
}
