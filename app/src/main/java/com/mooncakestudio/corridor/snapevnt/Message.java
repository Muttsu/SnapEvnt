package com.mooncakestudio.corridor.snapevnt;

import com.google.firebase.firestore.DocumentReference;

class Message {
    private String authorId;
    private String authorName;
    private String content;
    Message() {}
    Message(String authorId, String authorName, String content) {
        this.authorId = authorId;
        this.authorName = authorName;
        this.content = content;
    }
    public String getAuthorId() {
        return authorId;
    }
    public String getAuthorName() {
        return authorName;
    }
    public String getContent() {
        return content;
    }
}
