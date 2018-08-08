package com.liuly.www.like;

import android.app.Application;

import com.liuly.www.likelibrary.utils.LikeUtils;

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LikeUtils.init(this);
    }
}
