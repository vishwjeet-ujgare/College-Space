package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class TempDashBoard extends AppCompatActivity {

    TextView name,gmailId;
    ImageView pic;
    Button signOutBtn;

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_dash_board);

        name=findViewById(R.id.name);
        gmailId=findViewById(R.id.gamilId);
        pic=findViewById(R.id.imageView);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
        //  String personId = acct.getId();
        //  String personGivenName = acct.getGivenName();
        //  String personFamilyName = acct.getFamilyName();

            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();

            name.setText(personName);
            gmailId.setText(personEmail);
            Glide.with(this).load(String.valueOf(personPhoto)).into(pic);
        }

        signOutBtn=findViewById(R.id.signOutBtn);
        signOutBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                signOut();
            }
        });


    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(Task<Void> task) {
                        finish();
                        Toast.makeText(TempDashBoard.this, "signout",Toast.LENGTH_SHORT ).show();
                        startActivity(new Intent(TempDashBoard.this,Login.class));
                    }
                });
    }
}

