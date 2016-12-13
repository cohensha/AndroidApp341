package itp341.cohen.sharon.finalproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import layout.EmailFragment;
import layout.LogInFragment;

public class EmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        //set layout to email fragment
        FragmentManager fm = getSupportFragmentManager();

        Fragment emailFragment = fm.findFragmentById(R.id.email_frag_container);

        //get email and link strings from intent
        String email = getIntent().getStringExtra("email");
        String link = getIntent().getStringExtra("link");

        if(emailFragment == null){
            emailFragment = new EmailFragment();

            //pass the strings into the fragment, and load fragment
            Bundle bun = new Bundle();
            bun.putString("email", email);
            bun.putString("link", link);
            emailFragment.setArguments(bun);

            fm.beginTransaction().add(R.id.email_frag_container, emailFragment).commit();
        }


    }
}
