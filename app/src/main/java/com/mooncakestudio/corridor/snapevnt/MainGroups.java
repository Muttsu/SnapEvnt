package com.mooncakestudio.corridor.snapevnt;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainGroups extends Activity {

    ListView menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_groups);

        menuList = (ListView) findViewById(R.id.groupList);

        String[] menu = {"HACKER TROUP", "SNAP FAM", "PARTY ANIMALS", "SCHOOL PROJECT", "FAMILY", "SIGN OUT"};

        String[] listCount = new String[menu.length];

        for (int i = 0; i < menu.length; i++) {
            listCount[i] = "" + (int) (Math.floor(Math.random() * 5) + 1) ;
        }

        int [] menuImages={R.drawable.hacker,R.drawable.snap,R.drawable.party, R.drawable.school,R.drawable.family, R.drawable.signout};

        ListAdapter myAdapter = new CustomAdapter(this, menu, listCount, menuImages);

        menuList.setAdapter(myAdapter);

        menuList.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        if (position == 5){
                            signOut();
                        } else{
                            Intent myIntent = new Intent(view.getContext(), ChatActivity.class);
                            startActivityForResult(myIntent, 0);
                            overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_left );
                        }

                    }
                }
        );



    }
    public void signOut() {
        final MainActivity ma = new MainActivity();
        // [START auth_fui_signout]
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        finishActivity(123);
                    }
                });
        // [END auth_fui_signout]
    }
}

