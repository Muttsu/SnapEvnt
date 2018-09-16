package com.mooncakestudio.corridor.snapevnt;

import com.google.firebase.firestore.DocumentReference;

class User {
    private String id;
    private String name;
    private String pictureUrl;
    private DocumentReference[] rooms;
    User(String id, String name, String pictureUrl) {
        this.id = id;
        this.name = name;
        this.pictureUrl = pictureUrl;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPictureUrl() {
        return pictureUrl;
    }
}
