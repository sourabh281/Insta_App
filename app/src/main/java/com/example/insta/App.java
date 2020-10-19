package com.example.insta;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("9gFzFyeNU86NHyzRPAqvPHXlgy0qK1mB56L2wYpq")
                .clientKey("BKO8QDsUTafWkEKOFQ9GoYLLbCtM5dUYiTNg4vvv")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
