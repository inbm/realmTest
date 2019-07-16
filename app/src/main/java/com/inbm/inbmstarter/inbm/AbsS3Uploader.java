package com.inbm.inbmstarter.inbm;

import android.content.Context;

public abstract class AbsS3Uploader extends AbsS3Transfer {

    public AbsS3Uploader(Context context, String key, String filePath, _s3.OnTransferListener onTransferListener) {
        super(context, key, filePath, onTransferListener);
    }

    @Override
    public void transfer(String key, String filePath, _s3.OnTransferListener onTransferListener) {
        _s3.uploadWithTransferUtility(context, key, filePath, onTransferListener);
    }
}
