package com.mooncakestudio.corridor.snapevnt;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainGroups extends Activity {

    ListView menuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_groups);

        menuList = (ListView) findViewById(R.id.groupList);

        String[] menu = {"HACKER TROUP", "SNAP FAM", "PARTY ANIMALS", "SCHOOL PROJECT", "FAMILY"};

        String[] listCount = new String[menu.length];

        for (int i = 0; i < menu.length; i++) {
            listCount[i] = "" + (int) (Math.floor(Math.random() * 5) + 1) ;
        }

        int [] menuImages={R.drawable.hacker,R.drawable.snap,R.drawable.party, R.drawable.school,R.drawable.family};

        ListAdapter myAdapter = new CustomAdapter(this, menu, listCount, menuImages);

        menuList.setAdapter(myAdapter);

        menuList.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Intent myIntent = new Intent(view.getContext(), ChatActivity.class);
                        startActivityForResult(myIntent, 0);
                        overridePendingTransition( R.anim.slide_in_right, R.anim.slide_out_left );

                    }
                }
        );

    }
}

