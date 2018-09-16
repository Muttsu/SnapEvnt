package com.mooncakestudio.corridor.snapevnt;

import android.app.Application;

public class App extends Application{
    private User currentUser = new User("service_u0","Service Account #1", "N/A");
    public User getCurrentUser() {
        return currentUser;
    }
    private void setCurrentUser(User user) {
        currentUser = user;
    }
}
