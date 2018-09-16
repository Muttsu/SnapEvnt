package com.mooncakestudio.corridor.snapevnt;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends Activity {

    ImageView skipBtn;
    Button loginBtn

    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GoogleSignInActivity sa = new GoogleSignInActivity();
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
