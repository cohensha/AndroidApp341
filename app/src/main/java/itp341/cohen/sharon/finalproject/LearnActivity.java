package itp341.cohen.sharon.finalproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import itp341.cohen.sharon.finalproject.Model.User;

public class LearnActivity extends AppCompatActivity {
    User u;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        FragmentManager fm = getSupportFragmentManager();
        Fragment learnFragment = fm.findFragmentById(R.id.learn_frag_container);

        //get user object from intent
        u = (User) getIntent().getSerializableExtra("user");

        if(learnFragment == null){
            learnFragment = new CardFragment();
            Bundle bun = new Bundle();
            bun.putString("email", u.getEmail());
            learnFragment.setArguments(bun);
            fm.beginTransaction().add(R.id.learn_frag_container, learnFragment).commit();
        }






    }
}
