package com.inbm.inbmstarter.inbm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class _media {

    @SuppressLint("IntentReset")
    private static Intent getSelectPhotoIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);

        return intent;
    }

    public static void startSelectPhotoIntent(Activity activity, int requestCode) {
        Intent intent = getSelectPhotoIntent();
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startSelectPhotoIntent(Fragment fragment, int requestCode) {
        Intent intent = getSelectPhotoIntent();
        fragment.startActivityForResult(intent, requestCode);
    }

    @SuppressLint("IntentReset")
    private static Intent getSelectVideoIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("video/*");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);

        return intent;
    }

    public static void startSelectVideoIntent(Activity activity, int requestCode) {
        Intent intent = getSelectVideoIntent();
        activity.startActivityForResult(intent, requestCode);
    }

    public static void startSelectVideoIntent(Fragment fragment, int requestCode) {
        Intent intent = getSelectVideoIntent();
        fragment.startActivityForResult(intent, requestCode);
    }

    public static void startCaptureCameraIntent(Activity activity, int requestCode) {
        _camera.captureCamera(activity, requestCode);
    }

    public static void startCaptureCameraIntent(Fragment fragment, int requestCode) {
        _camera.captureCamera(fragment, requestCode);
    }

    public static String createImageThumbnail(String imagePath) {
        String thumbnailPath = null;

        try {
            Bitmap thumbnail = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(imagePath), 200, 200);

            thumbnailPath = imagePath + "_thumbnail.jpg";
            File file = new File(thumbnailPath);

            File dir = file.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }

            FileOutputStream fos = new FileOutputStream(file);
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, fos);

            fos.flush();
            fos.close();
        } catch (Exception e) {
            _log.e(e.getMessage());
            e.printStackTrace();
        }

        return thumbnailPath;
    }

    public static String createImageThumbnail_jsiss(String imagePath) {
        String thumbnailPath = null;
        Bitmap srcBmp = BitmapFactory.decodeFile(imagePath);
//        Bitmap dstBmp;
        try {
            try {
                // 이미지를 상황에 맞게 회전시킨다
                ExifInterface exif = new ExifInterface(imagePath);
                int exifOrientation = exif.getAttributeInt(
                        ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                int exifDegree = exifOrientationToDegrees(exifOrientation);
                srcBmp = rotate(srcBmp, exifDegree);
                // 변환된 이미지 사용
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            Bitmap thumbnail = ThumbnailUtils.extractThumbnail(srcBmp, 200, 200);

            thumbnailPath = imagePath + "_thumbnail.jpg";
            File file = new File(thumbnailPath);

            File dir = file.getParentFile();
            if (!dir.exists()) {
                dir.mkdirs();
            }

            FileOutputStream fos = new FileOutputStream(file);
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, fos);

            fos.flush();
            fos.close();
        } catch (Exception e){
            _log.e(e.getMessage());
            e.printStackTrace();
        }

        return thumbnailPath;
    }

    public static String createVideoThumbnail(String videoPath) {
        String thumbnailPath = null;
        try {
            Bitmap thumbnail = ThumbnailUtils.createVideoThumbnail(videoPath, MediaStore.Video.Thumbnails.MICRO_KIND);

            thumbnailPath = videoPath + "_thumbnail.jpg";
            File file = new File(thumbnailPath);

            File dir = file.getParentFile();
            if (!dir.exists()) dir.mkdirs();

            FileOutputStream fos = new FileOutputStream(file);
            thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, fos);

            fos.flush();
            fos.close();
        } catch (Exception e) {
            _log.e(e.getMessage());
            e.printStackTrace();
        }
        return thumbnailPath;
    }

    public static String getDCIMPath() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath();
    }

    public static String getDownloadPath() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
    }

    public static int exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }
    public static Bitmap rotate(Bitmap bitmap, int degrees)
    {
        if(degrees != 0 && bitmap != null)
        {
            Matrix m = new Matrix();
            m.setRotate(degrees, (float) bitmap.getWidth() / 2,
                    (float) bitmap.getHeight() / 2);

            try
            {
                Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0,
                        bitmap.getWidth(), bitmap.getHeight(), m, true);
                if(bitmap != converted)
                {
                    bitmap.recycle();
                    bitmap = converted;
                }
            }
            catch(OutOfMemoryError ex)
            {
                // 메모리가 부족하여 회전을 시키지 못할 경우 그냥 원본을 반환합니다.
            }
        }
        return bitmap;
    }

}
