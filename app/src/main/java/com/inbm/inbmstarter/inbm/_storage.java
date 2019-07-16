package com.inbm.inbmstarter.inbm;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.os.Environment.DIRECTORY_DCIM;

public class _storage {

    public static String getDCIMPath() {
        return Environment.getExternalStoragePublicDirectory(DIRECTORY_DCIM).getAbsolutePath();
    }

//    public static List<Uri> fetchAllImages(Context context) {
//        // DATA는 이미지 파일의 스트림 데이터 경로를 나타냅니다.
//        String[] projection = { MediaStore.Images.Media.DATA };
//
//        Cursor imageCursor = context.getContentResolver().query(
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // 이미지 컨텐트 테이블
//                projection, // DATA를 출력
//                null,       // 모든 개체 출력
//                null,
//                null);      // 정렬 안 함
//
//        ArrayList<Uri> result = new ArrayList<>(imageCursor.getCount());
//        int dataColumnIndex = imageCursor.getColumnIndex(projection[0]);
//
//        if (imageCursor == null) {
//            // Error 발생
//            // 적절하게 handling 해주세요
//        } else if (imageCursor.moveToFirst()) {
//            do {
//                String filePath = imageCursor.getString(dataColumnIndex);
//                Uri imageUri = Uri.parse(filePath);
//                result.add(imageUri);
//            } while(imageCursor.moveToNext());
//        } else {
//            // imageCursor가 비었습니다.
//        }
//        imageCursor.close();
//        return result;
//    }

    public static Uri uriToThumbnailImage(Context context, String imageId) {
        // DATA는 이미지 파일의 스트림 데이터 경로를 나타냅니다.
        String[] projection = { MediaStore.Images.Thumbnails.DATA };
        ContentResolver contentResolver = context.getContentResolver();

        // 원본 이미지의 _ID가 매개변수 imageId인 썸네일을 출력
        Cursor thumbnailCursor = contentResolver.query(
                MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI, // 썸네일 컨텐트 테이블
                projection, // DATA를 출력
                MediaStore.Images.Thumbnails.IMAGE_ID + "=?", // IMAGE_ID는 원본 이미지의 _ID를 나타냅니다.
                new String[]{imageId},
                null);
        if (thumbnailCursor == null) {
            // Error 발생
            // 적절하게 handling 해주세요
            return null;
        } else if (thumbnailCursor.moveToFirst()) {
            int thumbnailColumnIndex = thumbnailCursor.getColumnIndex(projection[0]);

            String thumbnailPath = thumbnailCursor.getString(thumbnailColumnIndex);
            thumbnailCursor.close();
            return Uri.parse(thumbnailPath);
        } else {
            // thumbnailCursor가 비었습니다.
            // 이는 이미지 파일이 있더라도 썸네일이 존재하지 않을 수 있기 때문입니다.
            // 보통 이미지가 생성된 지 얼마 되지 않았을 때 그렇습니다.
            // 썸네일이 존재하지 않을 때에는 아래와 같이 썸네일을 생성하도록 요청합니다
            return null;
//            MediaStore.Images.Thumbnails.getThumbnail(contentResolver, Long.parseLong(imageId), MediaStore.Images.Thumbnails.MINI_KIND, null);
//            thumbnailCursor.close();
//            return uriToThumbnailImage(context, imageId);
        }
    }

    public static Uri uriToThumbnailVideo(Context context, String imageId) {
        // DATA는 이미지 파일의 스트림 데이터 경로를 나타냅니다.
        String[] projection = { MediaStore.Video.Thumbnails.DATA };
        ContentResolver contentResolver = context.getContentResolver();



        // 원본 이미지의 _ID가 매개변수 imageId인 썸네일을 출력
        Cursor thumbnailCursor = contentResolver.query(
                MediaStore.Video.Thumbnails.EXTERNAL_CONTENT_URI, // 썸네일 컨텐트 테이블
                projection, // DATA를 출력
                MediaStore.Video.Thumbnails.VIDEO_ID + "=?", // IMAGE_ID는 원본 이미지의 _ID를 나타냅니다.
                new String[]{imageId},
                null);
        if (thumbnailCursor == null) {
            // Error 발생
            // 적절하게 handling 해주세요
            return null;
        } else if (thumbnailCursor.moveToFirst()) {
            int thumbnailColumnIndex = thumbnailCursor.getColumnIndex(projection[0]);

            String thumbnailPath = thumbnailCursor.getString(thumbnailColumnIndex);
            thumbnailCursor.close();
            return Uri.parse(thumbnailPath);
        } else {
            // thumbnailCursor가 비었습니다.
            // 이는 이미지 파일이 있더라도 썸네일이 존재하지 않을 수 있기 때문입니다.
            // 보통 이미지가 생성된 지 얼마 되지 않았을 때 그렇습니다.
            // 썸네일이 존재하지 않을 때에는 아래와 같이 썸네일을 생성하도록 요청합니다
            return null;
//            MediaStore.Video.Thumbnails.getThumbnail(contentResolver, Long.parseLong(imageId), MediaStore.Video.Thumbnails.MINI_KIND, null);
//            thumbnailCursor.close();
//            return uriToThumbnailVideo(context, imageId);
        }
    }

    public static List<_file_> fetchAllImages(Context context) {
        String[] projection = { MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID };

        Cursor imageCursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // 이미지 컨텐트 테이블
                projection, // DATA, _ID를 출력
                null,       // 모든 개체 출력
                null,
                null);

        List<_file_> result = new ArrayList<>(imageCursor.getCount());

        int dataColumnIndex = imageCursor.getColumnIndex(projection[0]);
        int idColumnIndex = imageCursor.getColumnIndex(projection[1]);

        if (imageCursor == null) {
            // Error 발생
            // 적절하게 handling 해주세요
        } else if (imageCursor.moveToFirst()) {
            do {
                String imagePath = imageCursor.getString(dataColumnIndex);
                String imageId = imageCursor.getString(idColumnIndex);
                Uri thumbnailUri = uriToThumbnailImage(context, imageId);
//                if (thumbnailUri == null) {
//                    // get thumbnail bitmap
//                    Bitmap bitmap = MediaStore.Images.Thumbnails.getThumbnail(context.getContentResolver(), Long.parseLong(imageId), MediaStore.Images.Thumbnails.MICRO_KIND, null);
//                    try {
//                        File file = createImageFile(context);
//                        FileOutputStream out = new FileOutputStream(file.getAbsolutePath());
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
//                        out.close();
//
//                        thumbnailUri = _uri.getUriForFile(context, file);
//                    }
//                    catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
                String thumbnailPath = thumbnailUri == null ? null : thumbnailUri.getPath();

                // 원본 이미지와 썸네일 이미지의 uri를 모두 담을 수 있는 클래스를 선언합니다.
                _file_ image = new _file_();
                image.filePath = imagePath;
                image.thumbnailPath = thumbnailPath == null ? imagePath : thumbnailPath;

                result.add(image);
            } while(imageCursor.moveToNext());
        } else {
            // imageCursor가 비었습니다.
        }

        imageCursor.close();

        return result;
    }

    public static List<_file_> fetchAllVideos(Context context) {
        String[] projection = { MediaStore.Video.VideoColumns.DATA, MediaStore.Video.Thumbnails._ID };

        Cursor imageCursor = context.getContentResolver().query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI, // 이미지 컨텐트 테이블
                projection, // DATA, _ID를 출력
                null,       // 모든 개체 출력
                null,
                null);

        List<_file_> result = new ArrayList<>(imageCursor.getCount());

        int dataColumnIndex = imageCursor.getColumnIndex(projection[0]);
        int idColumnIndex = imageCursor.getColumnIndex(projection[1]);

        if (imageCursor == null) {
            // Error 발생
            // 적절하게 handling 해주세요
        } else if (imageCursor.moveToFirst()) {
            do {
                String imagePath = imageCursor.getString(dataColumnIndex);
                String imageId = imageCursor.getString(idColumnIndex);
                Uri thumbnailUri = uriToThumbnailVideo(context, imageId);
//                if (thumbnailUri == null) {
//                    // get thumbnail bitmap
//                    Bitmap bitmap = MediaStore.Video.Thumbnails.getThumbnail(context.getContentResolver(), Long.parseLong(imageId), MediaStore.Video.Thumbnails.MICRO_KIND, null);
//                    try {
//                        File file = createImageFile(context);
//                        FileOutputStream out = new FileOutputStream(file.getAbsolutePath());
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
//                        out.close();
//
//                        thumbnailUri = _uri.getUriForFile(context, file);
//                    }
//                    catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
                String thumbnailPath = thumbnailUri == null ? null : thumbnailUri.getPath();

                // 원본 이미지와 썸네일 이미지의 uri를 모두 담을 수 있는 클래스를 선언합니다.
                _file_ image = new _file_();
                image.filePath = imagePath;
                image.thumbnailPath = thumbnailPath == null ? imagePath : thumbnailPath;

                result.add(image);
            } while(imageCursor.moveToNext());
        } else {
            // imageCursor가 비었습니다.
        }

        imageCursor.close();

        return result;
    }

    public static String getImageThumbnailPath(Context context, Uri imageUri) {
        String[] columns = { MediaStore.Images.Media._ID };

        String selection = MediaStore.Images.ImageColumns.DATA + "=?";
        String selectionArgs[] = { getImageAbsolutePath(context, imageUri) };

        Cursor imageCursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                columns,
                selection,
                selectionArgs,
                null);

        int idColumnIndex = imageCursor.getColumnIndex(columns[0]);

        if (imageCursor.moveToFirst()) {
            String imageId = imageCursor.getString(idColumnIndex);
            Uri thumbnailUri = _storage.uriToThumbnailImage(context, imageId);

            if (thumbnailUri != null) {
                return thumbnailUri.getPath();
            }
        }

        return null;
    }

    public static String getImageAbsolutePath(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            _log.e(e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static String getVideoThumbnailPath(Context context, Uri videoUri) {
        String[] columns = { MediaStore.Video.Media._ID };

        String selection = MediaStore.Video.VideoColumns.DATA + "=?";
        String selectionArgs[] = { getVideoAbsolutePath(context, videoUri) };

        Cursor imageCursor = context.getContentResolver().query(
                MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                columns,
                selection,
                selectionArgs,
                null);

        int idColumnIndex = imageCursor.getColumnIndex(columns[0]);

        if (imageCursor.moveToFirst()) {
            String videoId = imageCursor.getString(idColumnIndex);
            Uri thumbnailUri = _storage.uriToThumbnailVideo(context, videoId);

            if (thumbnailUri != null) {
                return thumbnailUri.getPath();
            }
        }

        return null;
    }

    public static String getVideoAbsolutePath(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Video.Media.DATA };
            cursor = context.getContentResolver().query(contentUri,  proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public static File createImageFile(Context context) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_thumbnail.jpg";
        File imageFile = null;

        File storageDir = new File(context.getExternalFilesDir(null), "Camera");
        _log.e(storageDir.getAbsolutePath());

        if (!storageDir.exists()) {
            Log.i("mCurrentPhotoPath1", storageDir.toString());
            storageDir.mkdirs();
        }

        imageFile = new File(storageDir, imageFileName);

        return imageFile;
    }

    public static class _file_{
        public String filePath;
        public String thumbnailPath;
    }
}
