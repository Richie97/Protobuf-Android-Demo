package com.willowtree.notahitlist.interactor;


import com.willowtree.notahitlist.TargetProto;

import java.io.IOException;

import retrofit2.Call;

public interface TargetInteractor extends BaseInteractor {
    Call<TargetProto.Target> addTarget(String name) throws IOException;

    Call<TargetProto.Targets> fetchTargets();
}