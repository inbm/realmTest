package com.inbm.inbmstarter.inbm;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class _uri {
    public static Uri getUriForFile(Context context, File file) {
        Uri uri;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            // API 24 이상 일경우..
            uri = FileProvider.getUriForFile(context, context.getPackageName() + ".fileprovider", file);
        } else {
            // API 24 미만 일경우..
            uri = Uri.fromFile(file);
        }
        return uri;
    }

    public static Uri getUriFromBitmap(Context context, Bitmap bitmap){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null);
        return Uri.parse(path);
    }
}
