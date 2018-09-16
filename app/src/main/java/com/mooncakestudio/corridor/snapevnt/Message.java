package com.mooncakestudio.corridor.snapevnt;

import com.google.firebase.firestore.DocumentReference;

import java.util.Date;

class Message {
    private Date timestamp;
    private String authorId;
    private String authorName;
    private String content;
    Message() {}
    Message(Date timestamp, String authorId, String authorName, String content) {
        this.timestamp = timestamp;
        this.authorId = authorId;
        this.authorName = authorName;
        this.content = content;
    }
    public Date getTimestamp() { return timestamp; }
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
