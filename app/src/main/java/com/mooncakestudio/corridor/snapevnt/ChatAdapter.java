package com.mooncakestudio.corridor.snapevnt;

import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    // TODO : get data from firestore
    private String tempdata = "testmessage";

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ConstraintLayout mChatView;
        public ChatViewHolder(ConstraintLayout v) {
            super(v);
            mChatView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ChatAdapter() {
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ChatAdapter.ChatViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        ChatViewHolder vh = new ChatViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView mAuthorTextView = (TextView) holder.mChatView.findViewById(R.id.AuthorTextView);
        TextView mContentTextView = (TextView) holder.mChatView.findViewById(R.id.ContentTextView);
        mAuthorTextView.setText("test");
        mContentTextView.setText("hello world");

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return 1;
    }
}
