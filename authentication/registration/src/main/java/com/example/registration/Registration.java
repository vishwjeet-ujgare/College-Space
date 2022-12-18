package com.example.registration;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;


public class Registration extends AppCompatActivity {
    GoogleSignInClient mGoogleSignInClient;
//    private  static int RC_SIGN_IN=1000;

//    ActivityResultLauncher<Intent> startForResult=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
//            new ActivityResultCallback<ActivityResult>() {
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            if (result != null && result.getResultCode() == RESULT_OK) {
////                if (result.getData() != null && result.getData().getStringExtra(this) !=null) {
////
////                }
//                //The Task returned from this call is always completed, no need to attach
//                // a listener.
//                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
//                handleSignInResult(task);
//            }
//        }
//    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);

        // Set the dimensions of the sign-in button.
        // SignInButton signInButton = findViewById(R.id.sign_in_button);
        // signInButton.setSize(SignInButton.SIZE_STANDARD);

         Button signInButton = findViewById(R.id.sign_in_button);
         signInButton.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View view) {

                 signIn();
             }
           }
         );
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        Toast.makeText(Registration.this, "before ",Toast.LENGTH_SHORT ).show();
         startActivityForResult(signInIntent, 1000);
//        startForResult.launch(signInIntent);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
             completedTask.getResult(ApiException.class);
            startActivity(new Intent(Registration.this,TempDashBoard.class));
            finish();

//            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
//            if (acct != null) {
//                String personName = acct.getDisplayName();
//                String personGivenName = acct.getGivenName();
//                String personFamilyName = acct.getFamilyName();
//                String personEmail = acct.getEmail();
//                String personId = acct.getId();
//                Uri personPhoto = acct.getPhotoUrl();
//            }

        } catch (ApiException e) {
            Toast.makeText(Registration.this, "Something went wrong...! ",Toast.LENGTH_SHORT ).show();
            Log.d("message",e.toString());
        }
    }
    public void alreadyRegister(View view)
    {
        startActivity(new Intent(Registration.this,Login.class));
        finish();
    }
}