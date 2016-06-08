package com.willowtree.notahitlist;

import android.app.Application;
import android.support.annotation.NonNull;

import com.willowtree.notahitlist.injection.AppComponent;
import com.willowtree.notahitlist.injection.AppModule;
import com.willowtree.notahitlist.injection.DaggerAppComponent;

public final class App extends Application {
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    @NonNull
    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}