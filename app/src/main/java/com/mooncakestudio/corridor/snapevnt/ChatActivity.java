package com.mooncakestudio.corridor.snapevnt;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class ChatActivity extends AppCompatActivity {

    Button button_chatbox_send;
    ImageButton cameraBtn, pollBtn, randomizerBtn, calendarBtn, notificationBtn;

    EditText edittext_chatbox;

    private static final String TAG = "ChatActivity";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        edittext_chatbox = (EditText) findViewById(R.id.edittext_chatbox);

        button_chatbox_send = (Button) findViewById(R.id.button_chatbox_send);

        cameraBtn = (ImageButton) findViewById(R.id.cameraBtn);

        pollBtn = (ImageButton) findViewById(R.id.pollBtn);
        randomizerBtn = (ImageButton) findViewById(R.id.randomizerBtn);
        calendarBtn = (ImageButton) findViewById(R.id.calendarBtn);
        notificationBtn = (ImageButton) findViewById(R.id.notificationBtn);

        mRecyclerView = (RecyclerView) findViewById(R.id.MessageListView);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        /*
        Message m0 = new Message("0",((App)getApplication()).getCurrentUser(),"hello world");
        Message m1 = new Message("1",new User("1","Test_Account#1","N/A"),"hi!");
        */


        mAdapter = new ChatAdapter(this, new ArrayList<Message>());
        mRecyclerView.setAdapter(mAdapter);

        final List<Message> mMessageData = ((ChatAdapter)mAdapter).mMessageData;

        button_chatbox_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = edittext_chatbox.getText().toString();
                if(!s.isEmpty() && s.length() > 0){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    Message m = new Message(new Date(), user.getUid(), user.getDisplayName(), s);
                    db.collection("rooms").document("test_r0")
                            .collection("messages").add(m);

                    // AI PREDICTION
                    Random r = new Random();
                    int randint = Math.abs(r.nextInt()) % 5;

                    if (randint == 0){
                        pollBtn.setImageResource(R.drawable.pollglow);

                    } else if (randint == 1){
                        randomizerBtn.setImageResource(R.drawable.randomizerglow);

                    } else if (randint == 2){
                        calendarBtn.setImageResource(R.drawable.calendarglow);

                    } else if (randint == 3){
                        notificationBtn.setImageResource(R.drawable.notificationglow);
                    }




                    edittext_chatbox.setText("");
                } else{
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter a message", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("MediaStore.ACTION_IMAGE_CAPTURE");
                startActivity(intent);
            }
        });

        pollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(ChatActivity.this, PollActivity.class);
                startActivity(I);

            }
        });

        randomizerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(ChatActivity.this, RandomizerActivity.class);
                startActivity(I);

            }
        });

        calendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(ChatActivity.this, CalendarActivity.class);
                startActivity(I);

            }
        });

        notificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(ChatActivity.this, NotificationsActivity.class);
                startActivity(I);

            }
        });

        db.collection("rooms").document("test_r0").collection("messages")
                .orderBy("timestamp")
                .limit(64)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }
                        for (DocumentChange doc : value.getDocumentChanges()) {
                            mMessageData.add(doc.getNewIndex(), doc.getDocument().toObject(Message.class));
                            mAdapter.notifyItemInserted(doc.getNewIndex());
                            mRecyclerView.smoothScrollToPosition(mAdapter.getItemCount()-1);
                        }
                    }
                });
    }
}
