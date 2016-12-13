package itp341.cohen.sharon.finalproject;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import java.util.ArrayList;

import io.fabric.sdk.android.Fabric;
import itp341.cohen.sharon.finalproject.Model.CardModel;
import itp341.cohen.sharon.finalproject.Model.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends Fragment {

    ArrayList<CardModel> listitems = new ArrayList<>();
    RecyclerView MyRecyclerView;
    String resources[] = {"National Domestic Violence Hotline", "New Safe Start", "The Shade Tree"};
    int  Images[] = {R.drawable.flower_img, R.drawable.open_hands_img, R.drawable.beach_img};
    String urls[] = {"http://www.thehotline.org/", "http://www.newsafestart.org/", "http://www.theshadetree.org/"};

    String email;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeList();
        getActivity().setTitle("StandStrong");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_card, container, false);

        //load card views into recycler view
        MyRecyclerView = (RecyclerView) view.findViewById(R.id.cardView);
        MyRecyclerView.setHasFixedSize(true);
        LinearLayoutManager MyLayoutManager = new LinearLayoutManager(getActivity());
        MyLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        if (listitems.size() > 0 & MyRecyclerView != null) {
            MyRecyclerView.setAdapter(new MyAdapter(listitems));
        }
        MyRecyclerView.setLayoutManager(MyLayoutManager);

        email = getArguments().get("email").toString();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    //class extends recycler view, creates card layouts
    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private ArrayList<CardModel> list;

        public MyAdapter(ArrayList<CardModel> Data) {
            list = Data;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
            // create a new view
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycle_items, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            holder.urlTextView.setText(list.get(position).getUrl());
            holder.titleTextView.setText(list.get(position).getCardName());
            holder.coverImageView.setImageResource(list.get(position).getImageResourceId());
            holder.coverImageView.setTag(list.get(position).getImageResourceId());
//            holder.shareImageView.setTag(list.get(position).getUri());
            //holder.shareButton.setImageURI(list.get(position).getUri());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    //class creates cards and sets listeners of card actions
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titleTextView;
        public ImageView coverImageView;
        public TextView urlTextView;

//        public ImageView shareImageView;

        public ImageButton shareButton;
        public ImageButton emailButton;
        public ImageButton tweetButton;

        public MyViewHolder(View v) {
            super(v);
            titleTextView = (TextView) v.findViewById(R.id.titleTextView);
            coverImageView = (ImageView) v.findViewById(R.id.coverImageView);
            urlTextView = (TextView) v.findViewById(R.id.urlTextView);
            shareButton = (ImageButton) v.findViewById(R.id.shareButton);
            emailButton = (ImageButton) v.findViewById(R.id.emailButton);
            tweetButton = (ImageButton) v.findViewById(R.id.tweetButton);

            //gets link, opens browser
            shareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   Uri linkUri = Uri.parse(urlTextView.getText().toString());

                    Toast.makeText(getActivity(), "opening link", Toast.LENGTH_LONG).show();
                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_VIEW);
                    i.addCategory(Intent.CATEGORY_BROWSABLE);
                    i.setData(linkUri);
                    startActivity(i);

                }
            });

            //takes user to email screen
            emailButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String link = urlTextView.getText().toString();

                    Toast.makeText(getActivity(), "opening email", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(getActivity(), EmailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("email", email);
                    bundle.putString("link", link);
                    i.putExtras(bundle);
                    startActivity(i);


                }
            });

            //uses fabric to open tweet composer
            tweetButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String link = urlTextView.getText().toString();

                    //TODO tweet link
                    TwitterAuthConfig authConfig =  new TwitterAuthConfig("consumerKey", "consumerSecret");
                    Fabric.with(getActivity(), new TwitterCore(authConfig), new TweetComposer());

                    TweetComposer.Builder builder = new TweetComposer.Builder(getActivity())
                            .text("Visit " + link + "to learn more about DVA");
                    builder.show();
                }

            });

        }
    }

    //instantiates card models and sets their variables
    public void initializeList() {
        listitems.clear();

        for(int i =0;i<3;i++){


            CardModel item = new CardModel();
            item.setCardName(resources[i]);
            item.setImageResourceId(Images[i]);
            item.setUrl(urls[i]);
            item.setIsturned(0);
            listitems.add(item);

        }
    }
}