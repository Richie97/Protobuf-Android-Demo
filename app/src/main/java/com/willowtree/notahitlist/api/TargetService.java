package com.willowtree.notahitlist.api;


import com.willowtree.notahitlist.TargetProto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by WillowTree, Inc on 6/8/16.
 */

public interface TargetService {
    @GET("targets")
    Call<TargetProto.Targets> getTargets();

    @POST("target")
    Call<TargetProto.Target> addTarget(@Body TargetProto.Target target);
}
