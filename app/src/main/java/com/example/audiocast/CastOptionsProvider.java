package com.example.audiocast;

import android.content.Context;
import android.renderscript.Script;
import android.util.Log;

import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.OptionsProvider;
import com.google.android.gms.cast.framework.SessionProvider;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.MediaIntentReceiver;
import com.google.android.gms.cast.framework.media.NotificationOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CastOptionsProvider implements OptionsProvider {


    @Override
    public CastOptions getCastOptions(Context context) {

        NotificationOptions notificationOptions = new NotificationOptions.Builder()
                .setTargetActivityClassName(MainActivity.class.getName())
                .build();

        CastMediaOptions mediaOptions = new CastMediaOptions.Builder()
                .setNotificationOptions(notificationOptions)
                .setMediaSessionEnabled(true)
                .setExpandedControllerActivityClassName(ExpandedControlsActivity.class.getName())
                .build();

        LaunchOptions launchOptions = new LaunchOptions.Builder()
                .setAndroidReceiverCompatible(false)
                .setRelaunchIfRunning(true)
                .build();

        return new CastOptions.Builder()
                .setReceiverApplicationId(CastMediaControlIntent.DEFAULT_MEDIA_RECEIVER_APPLICATION_ID)
                .setCastMediaOptions(mediaOptions)
                .setLaunchOptions(launchOptions)
                .build();
    }

    @Override
    public List<SessionProvider> getAdditionalSessionProviders(Context context) {

        return null;
    }

}
