package com.mooncakestudio.corridor.snapevnt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class RandomizerActivity extends AppCompatActivity {

    ListView itemList;
    Button addItemBtn, randomItemBtn;
    EditText itemETxt;

    ArrayList<String> names = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_randomizer);

        itemList = (ListView) findViewById(R.id.itemList);
        addItemBtn = (Button) findViewById(R.id.addChoiceBtn);
        randomItemBtn = (Button) findViewById(R.id.randomItemBtn);
        itemETxt = (EditText) findViewById(R.id.choiceETxt);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        itemList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        itemList.setAdapter(adapter);

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = itemETxt.getText().toString();
                if(!s.isEmpty() && s.length() > 0){
                    // ADD
                    adapter.add(s);
                    names.add(s);

                    // REFRESH
                    adapter.notifyDataSetChanged();
                    itemETxt.setText("");


                } else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter an item", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });

        randomItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!names.isEmpty()){
                    Random rn = new Random();
                    String random = names.get(rn.nextInt(names.size()));

                    Toast toast = Toast.makeText(getApplicationContext(), "RANDOM RESULT: " + random, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                } else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Please add items to randomize!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });
    }
}
