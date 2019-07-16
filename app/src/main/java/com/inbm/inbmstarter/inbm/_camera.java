package com.inbm.inbmstarter.inbm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class _camera {

    private static String capturePath;
    private static Uri captureUri;

    public static String getCapturePath() {
        return capturePath;
    }

    public static Uri getCaptureUri() {
        return captureUri;
    }

    public static void captureCamera(Activity activity, int requestCode) {
        Intent takePictureIntent = getCaptureCameraIntent(activity);
        activity.startActivityForResult(takePictureIntent, requestCode);
    }

    public static void captureCamera(Fragment fragment, int requestCode) {
        Intent takePictureIntent = getCaptureCameraIntent(fragment.getActivity());
        fragment.startActivityForResult(takePictureIntent, requestCode);
    }

    private static Intent getCaptureCameraIntent(Context context) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile(context);
            } catch (IOException e) {
                _log.e("59: " + e.getMessage());
                // Error occurred while creating the File
            } catch (Exception e) {
                _log.e("60: " + e.getMessage());
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = _uri.getUriForFile(context, photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            }
        }

        return takePictureIntent;
    }

    public static File createImageFile(Context context) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp;
//        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File storageDir = new File(_media.getDCIMPath());

        File imageFile = File.createTempFile(
                imageFileName, // prefix
                ".jpg", // suffix
                storageDir // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        capturePath = imageFile.getAbsolutePath();
        captureUri = _uri.getUriForFile(context, imageFile);

        return imageFile;
    }

    private static void galleryAddPic(Context context) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(capturePath);
        mediaScanIntent.setData(captureUri);
        context.sendBroadcast(mediaScanIntent);
    }

    public static void setPic(ImageView iv) {
        // Get the dimensions of the View
        int targetW = iv.getWidth();
        int targetH = iv.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(capturePath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(capturePath, bmOptions);
        iv.setImageBitmap(bitmap);
    }


}
