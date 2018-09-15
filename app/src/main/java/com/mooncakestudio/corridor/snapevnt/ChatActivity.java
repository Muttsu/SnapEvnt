package com.mooncakestudio.corridor.snapevnt;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private static final String TAG = "ChatActivity";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_chat);
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

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        final DocumentReference docRef = db.collection("rooms").document("SF");
        db.collection("rooms").document("test_r0").collection("messages")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w(TAG, "Listen failed.", e);
                            return;
                        }
                        for (DocumentChange doc : value.getDocumentChanges()) {
                            mMessageData.add(doc.getNewIndex(), doc.getDocument().toObject(Message.class));
                            mAdapter.notifyItemInserted(mMessageData.size()-1);
                        }
                    }
                });
    }
}
