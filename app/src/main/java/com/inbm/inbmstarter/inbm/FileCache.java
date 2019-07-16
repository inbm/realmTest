package com.inbm.inbmstarter.inbm;

import android.content.Context;

import java.io.File;

public class FileCache {
    
    private File cacheDir;
    private static final String APP_STORAGE = "readingbook_temp";
    private String _ROOT = "";

    public File getDir(){
        return cacheDir;
    }
    public FileCache(Context context){

        _ROOT = context.getFilesDir().getPath();
        //Find the dir to save cached images
//        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
//            cacheDir = new File(/*android.os.Environment.getExternalStorageDirectory()*/"/sdcard", APP_STORAGE);
//        }
//        else
//            cacheDir=context.getCacheDir();
//        if(!cacheDir.exists())
//            cacheDir.mkdirs();
        //cacheDir = new File(_ROOT, APP_STORAGE);
        cacheDir = new File(_ROOT);
    }

    public FileCache(Context context, String path){
        //Find the dir to save cached images
//        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
//           // cacheDir=new File(android.os.Environment.getExternalStorageDirectory(), APP_STORAGE);
//        	 cacheDir=new File(android.os.Environment.getExternalStorageDirectory(), path);
//        else
//            cacheDir=context.getCacheDir();
//        if(!cacheDir.exists())
//            cacheDir.mkdirs();
    }
    
    public File getFile(String url){
        //I identify images by hashcode. Not a perfect solution, good for the demo.
        String filename= String.valueOf(url.hashCode());
        //Another possible solution (thanks to grantland)
        //String filename = URLEncoder.encode(url);
        File f = new File(cacheDir, filename + ".zip");
        return f;
        
    }
    
    public void clear(){
        File[] files=cacheDir.listFiles();
        if(files==null)
            return;
        for(File f:files)
            f.delete();
    }

}