package com.mooncakestudio.corridor.snapevnt;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    private Context mContext;
    public List<Message> mMessageData;
    private static final int RECV_MESSAGE_TYPE = 0;
    private static final int SENT_MESSAGE_TYPE = 1;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static abstract class ChatViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ConstraintLayout mChatView;
        public ChatViewHolder(ConstraintLayout v) {
            super(v);
            mChatView = v;
        }

        public abstract void bind(Message message);
    }

    public static class RecvChatViewHolder extends ChatViewHolder {
        public RecvChatViewHolder(ConstraintLayout v) {
            super(v);
        }

        @Override
        public void bind(Message message) {
            TextView mAuthorTextView = (TextView) mChatView.findViewById(R.id.AuthorTextView);
            TextView mContentTextView = (TextView) mChatView.findViewById(R.id.ContentTextView);
            ImageView mProfileImageView = (ImageView) mChatView.findViewById(R.id.ProfileImageView);
            mAuthorTextView.setText(message.getAuthorName());
            mContentTextView.setText(message.getContent());
            // TODO : Implement Profile images
        }
    }

    public static class SentChatViewHolder extends ChatViewHolder {
        public SentChatViewHolder(ConstraintLayout v) {
            super(v);
        }

        @Override
        public void bind(Message message) {
            TextView mAuthorTextView = (TextView) mChatView.findViewById(R.id.AuthorTextView);
            TextView mContentTextView = (TextView) mChatView.findViewById(R.id.ContentTextView);
            mContentTextView.setText(message.getContent());
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ChatAdapter(Context context, List<Message> messageData) {
        mContext = context;
        mMessageData = messageData;
    }

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    public int getItemViewType(int position) {
        Message m = mMessageData.get(position);
        if (m.getAuthorId().equals(user.getUid())) {
            return SENT_MESSAGE_TYPE;
        } else {
            return RECV_MESSAGE_TYPE;
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ChatAdapter.ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        ChatViewHolder vh;
        ConstraintLayout v;
        switch(viewType){
            default:
            case RECV_MESSAGE_TYPE:
                v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_recv_message, parent, false);
                vh = new RecvChatViewHolder(v);
                break;
            case SENT_MESSAGE_TYPE:
                v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_sent_message, parent, false);
                vh = new SentChatViewHolder(v);
                break;
        }
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ChatViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.bind(mMessageData.get(position));

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mMessageData.size();
    }
}
