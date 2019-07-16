package com.inbm.inbmstarter.inbm;

import android.graphics.Bitmap;
import android.os.AsyncTask;

public class ImageDownloader{
	
	private static ImageDownloader instance ;
	private ImageTask imageTask = null;
	
	public static ImageDownloader _instance(){ 
		if(instance == null){ 
			instance  = new ImageDownloader ();
		}

		return instance;
	}
	
	public void download(String...strings){
		
		if(imageTask == null){
			imageTask = new ImageTask();
			imageTask.execute(strings);
		}
	}
	
	
	public void cancel(){
		imageTask.cancel(true);
	}

	public class ImageTask extends AsyncTask<String, String, Bitmap> {
	
		@Override
		protected void onCancelled() {
			
			super.onCancelled();
			this.cancel(true);
		}
	
		@Override
		protected Bitmap doInBackground(String... params) {
			
			for(String str:params){
				if(isCancelled())
					return null;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					_log.inCatch("다운로드 취소 : " + e.getMessage());
				}

			}
			return null;
		}
	
		@Override
	    protected void onProgressUpdate(String... progress) {
	        // ProgressDialog�� ���ڿ��� ����
	        //setProgressMessage(progress[0]);
	    }
	
	    @Override
	    protected void onPostExecute(Bitmap result) {
	        // �񵿱� �����ηε尡 �Ϸ� �̹����� View�� ǥ��
	        //showImage(result);
	    }
	}
}
