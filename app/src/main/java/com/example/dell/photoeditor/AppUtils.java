package com.example.dell.photoeditor;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.example.dell.photoeditor.Data.Filter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 4/19/2018.
 */

public class AppUtils {
    public static final String[] PERMISSIONS = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,

    };

    public static Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte=Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }
    public static Bitmap compressBitmap(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,stream);

        byte[] byteArray = stream.toByteArray();
        Bitmap compressedBitmap = BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        return compressedBitmap;
    }
    public static ArrayList<Filter> getFilter(){
        ArrayList<Filter> filters = new ArrayList<>(7);
        filters.add(new Filter(R.drawable.effect,"Effect"));
        filters.add(new Filter(R.drawable.frame,"Frame"));
        filters.add(new Filter(R.drawable.crop,"Crop"));
        filters.add(new Filter(R.drawable.edit,"Paint"));
        filters.add(new Filter(R.drawable.label,"Label"));
        filters.add(new Filter(R.drawable.blur,"Blur"));
        filters.add(new Filter(R.drawable.replay,"Swivel"));
        return filters;
    }


}
