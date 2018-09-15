package com.mooncakestudio.corridor.snapevnt;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.snapchat.kit.sdk.SnapLogin;

public class MainActivity extends Activity {

    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        View yourView = findViewById(R.id.loginBtn);
        yourView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SnapLogin.getAuthTokenManager(MainActivity.this).startTokenGrant();
            }
        });
//        loginBtn = (Button) findViewById(R.id.loginBtn);
//
//        loginBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent I = new Intent(MainActivity.this, MainGroups.class);
//                startActivity(I);
//            }
//        });

    }
}
