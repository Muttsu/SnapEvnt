package com.mooncakestudio.corridor.snapevnt;

import android.content.Intent;
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

public class PollActivity extends AppCompatActivity {

    ListView choicesList;
    Button addChoiceBtn, submitChoiceBtn;
    EditText choiceETxt;

    ArrayList<String> names = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);

        choicesList = (ListView) findViewById(R.id.choicesList);
        addChoiceBtn = (Button) findViewById(R.id.addChoiceBtn);
        submitChoiceBtn = (Button) findViewById(R.id.submitChoiceBtn);
        choiceETxt = (EditText) findViewById(R.id.choiceETxt);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        choicesList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        choicesList.setAdapter(adapter);

        addChoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = choiceETxt.getText().toString();
                if(!s.isEmpty() && s.length() > 0){
                    // ADD
                    adapter.add(s);
                    names.add(s);

                    // REFRESH
                    adapter.notifyDataSetChanged();
                    choiceETxt.setText("");


                } else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter a choice", Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });


        submitChoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "Choice submitted successfully", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();

                Intent I = new Intent(PollActivity.this, ChatActivity.class);
                startActivity(I);
            }
        });


    }
}
