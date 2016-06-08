package com.willowtree.notahitlist.presenter;

import android.content.Context;

public interface TargetPresenter extends BasePresenter {
    void addTarget(Context context);
    void fetchTargets();
}