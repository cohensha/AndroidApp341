package itp341.cohen.sharon.finalproject;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;
import layout.LogInFragment;

public class MainActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "FB0set3tVCJyH8bXdWRC26XZ1";
    private static final String TWITTER_SECRET = "RtpPEO4oRGOYurFxLBGo5Qobz6Tzg7SJcTYc4RG70y7WkCCOVk";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);

        //set layout to login fragment layout
        FragmentManager fm = getSupportFragmentManager();

        Fragment loginFrag = fm.findFragmentById(R.id.main_frag_container);
        if(loginFrag == null){
            loginFrag = new LogInFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.main_frag_container, loginFrag);
            ft.commit();
        }

    }
}
