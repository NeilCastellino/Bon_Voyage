package com.neilcastellino.bonvoyage;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.firebase.client.Firebase;

public class BonVoyage extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
            FacebookSdk.sdkInitialize(this);
        Firebase.setAndroidContext(this);
    }
}
