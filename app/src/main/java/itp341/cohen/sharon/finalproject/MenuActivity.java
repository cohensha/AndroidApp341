package itp341.cohen.sharon.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import itp341.cohen.sharon.finalproject.Model.User;

public class MenuActivity extends AppCompatActivity {

    Button openResourcesButton;
    TextView nameView;
    Button donateButton;

    User u;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        openResourcesButton = (Button) findViewById(R.id.openResourcesButton);
        nameView = (TextView) findViewById(R.id.nameView);
        donateButton = (Button) findViewById(R.id.donateButton);

        u = (User) getIntent().getSerializableExtra("user");
        //add name and location to user when button is pressed...
        nameView.setText("Welcome " + u.getEmail());

        openResourcesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //passes user into resources activity
                Intent i = new Intent(MenuActivity.this, LearnActivity.class);
                Bundle bun = new Bundle();
                bun.putSerializable("user", u);
                i.putExtras(bun);
                startActivity(i);
            }
        });

        donateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO - donation from app
            }
        });
    }
}
