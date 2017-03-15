package com.gmobile.githubapiproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by mark on 3/13/17.
 */

public class ProfileActivity extends AppCompatActivity {

    TextView profileurltext, usernametext;
    ImageView imagephoto;
    Button btnshare;
    String username, photo, profileurl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        initialize();
        username = getIntent().getExtras().getString("Username");
        photo = getIntent().getExtras().getString("Photo");
        profileurl = getIntent().getExtras().getString("Profileurl");

        Log.i(username, photo);
        Log.i("Profileurl", profileurl);
        usernametext.setText(username);
        profileurltext.setText(profileurl);
        assert photo != null;
        Picasso.with(this).load(photo).into(imagephoto);


        btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Checkout this awesome developer @<" + username + ">,<" + profileurl + ">");
                startActivity(shareIntent);
            }
        });

    }

    private void initialize() {
        usernametext = (TextView) findViewById(R.id.textusername);

        profileurltext = (TextView) findViewById(R.id.textprofileurl);

        imagephoto = (ImageView) findViewById(R.id.profilephoto);

        btnshare = (Button) findViewById(R.id.buttonshare);


    }


}
