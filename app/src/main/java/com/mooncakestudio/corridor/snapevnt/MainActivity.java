package com.mooncakestudio.corridor.snapevnt;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends Activity {

    private static final int RC_SIGN_IN = 123;

    ImageView skipBtn;
<<<<<<< Updated upstream
    Button loginBtn

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GoogleSignInActivity sa = new GoogleSignInActivity();
=======
    Button loginBtn;
    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.GoogleBuilder().build());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        skipBtn = (ImageView) findViewById(R.id.skipBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(
                        AuthUI.getInstance()
                                .createSignInIntentBuilder()
                                .setAvailableProviders(providers)
                                .build(),
                        RC_SIGN_IN);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                
                // ...
                Intent I = new Intent(MainActivity.this, MainGroups.class);
                startActivity(I);
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...

                // Fail Silently
            }
        }
    }

    public void skipLogin(View view){
        Toast toast = Toast.makeText(getApplicationContext(), "Skip login btn clicked", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
>>>>>>> Stashed changes
    }
//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
//        mFirebaseAuth = FirebaseAuth.getInstance();
//        mFirebaseUser = mFirebaseAuth.getCurrentUser();
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        skipBtn = (ImageView) findViewById(R.id.skipBtn);
//        loginBtn = (Button) findViewById(R.id.loginBtn);
//
//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(this, SignInActivity.class));
//                finish();
//                return;
//                Intent I = new Intent(MainActivity.this, MainGroups.class);
//                startActivity(I);
//            }
//        });
//
//        skipBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                skipLogin(MainActivity.super.getCurrentFocus());
//            }
//        });
//
//        if (mFirebaseUser == null) {
//        } else {
//            skipLogin(this.getCurrentFocus());
//        }
//
//    }
//
//    public void skipLogin(View view){
//        Toast toast = Toast.makeText(getApplicationContext(), "Skip login btn clicked", Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.show();
//    }
//
}
