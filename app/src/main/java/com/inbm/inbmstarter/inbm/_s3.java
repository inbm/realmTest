package com.inbm.inbmstarter.inbm;

import android.annotation.SuppressLint;
import android.content.Context;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.mobile.auth.core.IdentityHandler;
import com.amazonaws.mobile.auth.core.IdentityManager;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobile.client.AWSStartupHandler;
import com.amazonaws.mobile.client.AWSStartupResult;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class _s3 {

    public interface OnInitListener {
        void onInit();
    }

    public interface OnTransferListener {
        void onCompleted();
        void onProgressChanged(int percentDone);
        void onError(Exception e);
    }

    private static AmazonS3Client s3Client;
    private static AWSMobileClient mobileClient;
    private static AWSConfiguration configuration;

    private static String accessKey;
    private static String secretKey;
    private static String bucket;

    public static void setAccessKey(String accessKey) {
        _s3.accessKey = accessKey;
    }

    public static void setSecretKey(String secretKey) {
        _s3.secretKey = secretKey;
    }

    public static void setBucket(String bucket) {
        _s3.bucket = bucket;
    }

    public static void initAWS(Context context, OnInitListener onInitListener) {
        if (
                _s3.s3Client == null || _s3.mobileClient == null || _s3.configuration == null
        ) {
            AWSMobileClient.getInstance().initialize(context, new AWSStartupHandler() {
                @Override
                public void onComplete(AWSStartupResult awsStartupResult) {
                    _s3.mobileClient = AWSMobileClient.getInstance();
                    _s3.configuration = mobileClient.getConfiguration();

                    _s3.s3Client = new AmazonS3Client(new AWSCredentials() {
                        @Override
                        public String getAWSAccessKeyId() {
                            if (_s3.accessKey == null || _s3.accessKey.length() == 0) {
                                _s3.accessKey = _s3.configuration.optJsonObject("S3TransferUtility").optString("AccessKey");
                            }
                            _log.e("[AccessKey] : " + _s3.accessKey);
                            return _s3.accessKey;
                        }

                        @Override
                        public String getAWSSecretKey() {
                            if (_s3.secretKey == null || _s3.secretKey.length() == 0) {
                                _s3.secretKey = _s3.configuration.optJsonObject("S3TransferUtility").optString("SecretKey");
                            }
                            _log.e("[SecretKey] : " + _s3.secretKey);
                            return _s3.secretKey;
                        }
                    });

                    if (_s3.bucket == null || _s3.bucket.length() == 0) {
                        _s3.bucket = _s3.configuration.optJsonObject("S3TransferUtility").optString("Bucket");
                    }

                    IdentityManager.getDefaultIdentityManager().getUserID(new IdentityHandler() {
                        @Override
                        public void onIdentityId(String identityId) {
                            //  fetch the locally cached identity id.
                            final String cachedIdentityId = IdentityManager.getDefaultIdentityManager().getCachedUserID();

                            if (onInitListener != null) {
                                onInitListener.onInit();
                            }
                        }

                        @Override
                        public void handleError(Exception exception) {
                            _log.e(exception.getMessage());
                            exception.printStackTrace();
                        }
                    });
                }
            }).execute();
        }
        else {
            onInitListener.onInit();
        }
    }

    public static void uploadWithTransferUtility(Context context, String key, String filePath, OnTransferListener onTransferListener) {
        initAWS(context, new OnInitListener() {
            @Override
            public void onInit() {
                TransferUtility transferUtility =
                        TransferUtility.builder()
                                .context(context)
                                .awsConfiguration(_s3.configuration)
                                .s3Client(_s3.s3Client)
                                .build();

                TransferObserver uploadObserver =
                        transferUtility.upload(
                                _s3.bucket,
                                key,
                                new File(filePath),
                                CannedAccessControlList.PublicRead
                        );

                // Attach a listener to the observer to get state update and progress notifications
                uploadObserver.setTransferListener(new TransferListener() {

                    @Override
                    public void onStateChanged(int id, TransferState state) {
                        if (TransferState.COMPLETED == state) {
                            if (onTransferListener != null) {
                                onTransferListener.onCompleted();
                            }
                        }
                    }

                    @Override
                    public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                        float percentDonef = ((float) bytesCurrent / (float) bytesTotal) * 100;
                        int percentDone = (int)percentDonef;

                        if (onTransferListener != null) {
                            onTransferListener.onProgressChanged(percentDone);
                        }
                    }

                    @Override
                    public void onError(int id, Exception ex) {
                        if (onTransferListener != null) {
                            onTransferListener.onError(ex);
                        }
                    }

                });
            }
        });
    }

    public static void downloadWithTransferUtility(Context context, String key, String filePath, OnTransferListener onTransferListener) {
        initAWS(context, new OnInitListener() {
            @Override
            public void onInit() {
                TransferUtility transferUtility =
                        TransferUtility.builder()
                                .context(context)
                                .awsConfiguration(_s3.configuration)
                                .s3Client(_s3.s3Client)
                                .build();

                TransferObserver downloadObserver =
                        transferUtility.download(
                                key,
                                new File(filePath));

                downloadObserver.setTransferListener(new TransferListener() {
                    @Override
                    public void onStateChanged(int id, TransferState state) {
                        if (TransferState.COMPLETED == state) {
                            if (onTransferListener != null) {
                                onTransferListener.onCompleted();
                            }
                        }
                    }

                    @Override
                    public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
                        float percentDonef = ((float)bytesCurrent/(float)bytesTotal) * 100;
                        int percentDone = (int)percentDonef;

                        if (onTransferListener != null) {
                            onTransferListener.onProgressChanged(percentDone);
                        }
                    }

                    @Override
                    public void onError(int id, Exception ex) {
                        if (onTransferListener != null) {
                            onTransferListener.onError(ex);
                        }
                    }

                });
            }
        });
    }

    public static String createKey(String parentPath, String filePath) {
        File file = new File(filePath);
        @SuppressLint("SimpleDateFormat") String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());

        return parentPath + "/" + timeStamp + "-" + file.getName();
    }

    /**
     * TODO AWS SetConstant
     */
    public class Constants {
        public static final String S3_URL = "https://s3-ap-northeast-2.amazonaws.com/";
        public static final String ACCESS_KEY = "22";
        public static final String SECRET_KEY = "11";
        public static final String BUCKET = "33";
    }
}



