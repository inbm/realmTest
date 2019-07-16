package com.inbm.inbmstarter.inbm;

import com.google.firebase.iid.FirebaseInstanceId;

public class _firebase {
    public static void setToken() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(instanceIdResult -> {
            String token = instanceIdResult.getToken();
            _shared.setDeviceToken(token);
            App.firebase_token = token;
            _log.e(instanceIdResult.getToken());
        });
    }

}
