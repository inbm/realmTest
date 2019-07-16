package com.inbm.inbmstarter.inbm.firebase;

import com.inbm.inbmstarter.inbm.App;
import com.inbm.inbmstarter.inbm._shared;


public class FirebaseInstanceIDService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseIIDService";

    // [START refresh_token]
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

        String deviceToken = s;
        _shared.setDeviceToken(s);
        App.firebase_token = deviceToken;
        sendToken2Server(s);
    }

    //TODO send token to server
    private void sendToken2Server(String token) {

    }

}