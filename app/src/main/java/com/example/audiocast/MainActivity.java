package com.example.audiocast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.mediarouter.app.MediaRouteButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLoadRequestData;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.CastButtonFactory;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.CastState;
import com.google.android.gms.cast.framework.CastStateListener;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.MediaIntentReceiver;
import com.google.android.gms.cast.framework.media.NotificationAction;
import com.google.android.gms.cast.framework.media.NotificationActionsProvider;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.images.WebImage;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SessionManager mSessionManager;
    private CastSession mCastSession;
    private RemoteMediaClient mRemoteMediaClient;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private CastStateListener mCastStateListener;
    private CastContext mCastContext;

    final static String TAG = "test_app";


    private final SessionManagerListener mSessionManagerListener = new SessionManagerListenerImpl();

    private class SessionManagerListenerImpl implements SessionManagerListener<CastSession> {

        @Override
        public void onSessionStarting(CastSession session) {

            Log.d(TAG,"onSessionStarting" );
        }

        @Override
        public void onSessionStarted(CastSession session, String s) {
            mCastSession = session;
            Log.d(TAG, "onSessionStarted");
            startCastSession();
//            radioCast(Stations.STATION_LIST.get(1));
            invalidateOptionsMenu();
        }

        @Override
        public void onSessionStartFailed(CastSession session, int i) {
            Log.d(TAG,"nSessionStartFailed");
        }

        @Override
        public void onSessionEnding(CastSession session) {
            Log.d(TAG,"onSessionEnding");
        }

        @Override
        public void onSessionEnded(CastSession session, int i) {

            Log.d(TAG,"onSessionEnded" );
            if (session == mCastSession) {
                mCastSession = null;
            }
            invalidateOptionsMenu();
        }

        @Override
        public void onSessionResuming(CastSession session, String s) {
            Log.d(TAG,"onSessionResuming");
        }

        @Override
        public void onSessionResumed(CastSession session, boolean b) {
            Log.d(TAG, "onSessionResume");
            mCastSession = session;
            invalidateOptionsMenu();
        }

        @Override
        public void onSessionResumeFailed(CastSession session, int i) {
            Log.d(TAG,"nSessionResumeFailed");
        }

        @Override
        public void onSessionSuspended(CastSession session, int i) {
            Log.d(TAG,"onSessionSuspended" );
        }
    }

    private void radioCast(RadioStation radioStation)
    {
        MediaMetadata nMediaMetadata = new MediaMetadata(MediaMetadata.MEDIA_TYPE_GENERIC);
        nMediaMetadata.putString(MediaMetadata.KEY_TITLE,radioStation.getName());
        if(radioStation.getImg()!=null)
        nMediaMetadata.addImage(new WebImage(Uri.parse(radioStation.getImg())));
        MediaInfo mediaInfo = new MediaInfo.Builder(radioStation.getUri())
                .setStreamType(MediaInfo.STREAM_TYPE_LIVE)
                .setContentType("audio/mpeg3")
                .setMetadata(nMediaMetadata)
                .build();

        MediaLoadRequestData mediaLoadRequestData = new MediaLoadRequestData.Builder()
                .setMediaInfo(mediaInfo)
                .setAutoplay(false)
                .build();
        mRemoteMediaClient.load(mediaLoadRequestData);

    }

    private void startCastSession() {
        mCastSession = mSessionManager.getCurrentCastSession();
        mRemoteMediaClient = mCastSession.getRemoteMediaClient();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        mSessionManager = CastContext.getSharedInstance(this).getSessionManager();
        mCastContext = CastContext.getSharedInstance(this);


        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RlistAdapter(Stations.STATION_LIST, new ClickListener() {
            @Override
            public void onItemClick(int itemIndex) {
                if(mCastSession!=null&&mCastSession.isConnected())
                radioCast(Stations.STATION_LIST.get(itemIndex));
            }
        });
        recyclerView.setAdapter(adapter);
        mCastStateListener = new CastStateListener() {
            @Override
            public void onCastStateChanged(int i) {
                Log.d(TAG,"onCastStateChange");
            }
        };
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        CastButtonFactory.setUpMediaRouteButton(getApplicationContext(),
                menu,
                R.id.media_route_menu_item);
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        mCastSession = mSessionManager.getCurrentCastSession();
        if(mCastSession!=null){
            mRemoteMediaClient = mCastSession.getRemoteMediaClient();
        }
        mCastContext.addCastStateListener(mCastStateListener);
        mCastContext.getSessionManager().addSessionManagerListener(mSessionManagerListener);
    }


       @Override
    protected void onPause() {
        super.onPause();
        mCastContext.removeCastStateListener(mCastStateListener);
        mCastContext.getSessionManager().removeSessionManagerListener(mSessionManagerListener);
           Log.d(TAG,"onPause");
    }
}