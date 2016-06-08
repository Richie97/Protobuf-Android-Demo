package com.willowtree.notahitlist.injection;

import android.content.Context;

import com.willowtree.notahitlist.App;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Context getAppContext();

    App getApp();
}