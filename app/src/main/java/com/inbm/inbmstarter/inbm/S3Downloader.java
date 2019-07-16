package com.inbm.inbmstarter.inbm;

import android.content.Context;

public class S3Downloader extends AbsS3Downloader {
    public S3Downloader(Context context, String key, String filePath, _s3.OnTransferListener onTransferListener) {
        super(context, key, filePath, onTransferListener);
    }

    @Override
    public String getAccessKey() {
        return _s3.Constants.ACCESS_KEY;
    }

    @Override
    public String getSecretKey() {
        return _s3.Constants.SECRET_KEY;
    }

    @Override
    public String getBucket() {
        return _s3.Constants.BUCKET;
    }
}
