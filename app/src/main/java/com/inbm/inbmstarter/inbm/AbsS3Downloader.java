package com.inbm.inbmstarter.inbm;

import android.content.Context;

public abstract class AbsS3Downloader extends AbsS3Transfer {

    public AbsS3Downloader(Context context, String key, String filePath, _s3.OnTransferListener onTransferListener) {
        super(context, key, filePath, onTransferListener);
    }

    @Override
    public void transfer(String key, String filePath, _s3.OnTransferListener onTransferListener) {
        _s3.downloadWithTransferUtility(context, key, filePath, onTransferListener);
    }

}
