package com.willowtree.notahitlist.view;

import android.support.annotation.UiThread;

import com.willowtree.notahitlist.TargetProto;

@UiThread
public interface TargetView {

    void addTarget(TargetProto.Target target);

    void populateList(TargetProto.Targets targets);

    void onError(String error);

}