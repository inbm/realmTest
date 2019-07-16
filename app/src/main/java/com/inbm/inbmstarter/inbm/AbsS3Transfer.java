package com.inbm.inbmstarter.inbm;

import android.content.Context;

public abstract class AbsS3Transfer {

    protected Context context;

    public AbsS3Transfer(Context context, String key, String filePath, _s3.OnTransferListener onTransferListener) {
        this.context = context;

        _s3.setAccessKey(getAccessKey());
        _s3.setSecretKey(getSecretKey());
        _s3.setBucket(getBucket());

        transfer(key, filePath, onTransferListener);
    }

    public abstract String getAccessKey();
    public abstract String getSecretKey();
    public abstract String getBucket();
    public abstract void transfer(String key, String filePath, _s3.OnTransferListener onTransferListener);
}
