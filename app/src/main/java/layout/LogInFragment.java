package layout;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.OnCompleteListener;


import itp341.cohen.sharon.finalproject.LearnActivity;
import itp341.cohen.sharon.finalproject.MenuActivity;
import itp341.cohen.sharon.finalproject.Model.User;
import itp341.cohen.sharon.finalproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogInFragment extends Fragment {

    EditText userNameField;
    EditText passwordField;

    Button loginButton;
    Button signUpButton;
    Button guestButton;

    private FirebaseAuth firebaseAuth;

    public LogInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_log_in, container, false);

        // get views from layout
        userNameField = (EditText) view.findViewById(R.id.userNameField);
        passwordField = (EditText) view.findViewById(R.id.passwordField);

        loginButton = (Button) view.findViewById(R.id.loginButton);
        signUpButton = (Button) view.findViewById(R.id.signUpButton);

        //initialize firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        setListeners();


        return view;
    }

    public void setListeners(){

        userNameField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userNameField.getText().clear();
            }
        });

        passwordField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordField.getText().clear();
            }
        });

        //login using firebase
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   // Toast.makeText(getApplicationContext()
                //TODO on click listener
                final String email = userNameField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();

                //signs in
                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getActivity(), "successful login!", Toast.LENGTH_SHORT).show();
                                    User u = new User(email);
                                    goToMenu(u);
                                }
                                else{
                                    Toast.makeText(getActivity(), "login failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        //registration using firebase
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO on click listener
                final String email = userNameField.getText().toString().trim();
                String password = passwordField.getText().toString().trim();

                //checks to make sure email and password are provided
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getActivity(), "enter email address", Toast.LENGTH_SHORT).show();

                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getActivity(), "enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getActivity(), "Successful registration!", Toast.LENGTH_SHORT).show();
                            User u = new User(email);
                            goToMenu(u);
                        }
                        else{
                            Toast.makeText(getActivity(), "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//
            }
        });

    }

    //passes user into menu activity
    public void goToMenu(User u){
        Intent i = new Intent(getActivity(), LearnActivity.class);
        Bundle bun = new Bundle();
        bun.putSerializable("user", u);
        i.putExtras(bun);
        startActivity(i);

    }

}
