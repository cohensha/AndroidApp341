package layout;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import itp341.cohen.sharon.finalproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmailFragment extends Fragment {

    EditText fromText;
    EditText toText;
    EditText bodyText;
    Button sendButton;


    public EmailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_email, container, false);

        fromText = (EditText) view.findViewById(R.id.fromEditText);
        toText = (EditText) view.findViewById(R.id.toEditText);
        bodyText = (EditText) view.findViewById(R.id.bodyEditText);
        sendButton = (Button) view.findViewById(R.id.sendButton);

        //gets strings from intent
        String email = getArguments().get("email").toString();
        String link = getArguments().get("link").toString();
        fromText.setText(email);
        bodyText.setText("Learn more about domestic violence awareness" + link);


        //uses intent to send email
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fromEmail = fromText.getText().toString().trim();
                final String toEmail = toText.getText().toString().trim();
                final String body = bodyText.getText().toString();


                Intent i = new Intent(Intent.ACTION_SENDTO);
                i.setType("message/rfc822");
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{toEmail});
                i.putExtra(Intent.EXTRA_SUBJECT, fromEmail);
                i.putExtra(Intent.EXTRA_TEXT, body);

                try{
                    startActivity(Intent.createChooser(i, "Send mail..."));

                }catch(android.content.ActivityNotFoundException exc){
                    Toast.makeText(getActivity(), "no email clients installed", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }

}
