package com.willowtree.notahitlist.presenter.impl;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatEditText;

import com.willowtree.notahitlist.TargetProto;
import com.willowtree.notahitlist.interactor.TargetInteractor;
import com.willowtree.notahitlist.presenter.TargetPresenter;
import com.willowtree.notahitlist.view.TargetView;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public final class TargetPresenterImpl implements TargetPresenter {
    /**
     * The view
     */
    @NonNull
    private final TargetView mView;
    /**
     * The interactor
     */
    @NonNull
    private final TargetInteractor mInteractor;

    @Inject
    public TargetPresenterImpl(@NonNull TargetView view, @NonNull TargetInteractor interactor) {
        mView = view;
        mInteractor = interactor;
    }

    @Override
    public void onRestoreState(@NonNull Bundle savedInstanceState) {

    }

    @Override
    public void onStart(boolean firstStart) {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

    }

    @Override
    public void addTarget(Context context) {
        final AppCompatEditText v = new AppCompatEditText(context);
        new AlertDialog.Builder(context)
                .setTitle("Enter Name")
                .setView(v)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            mInteractor.addTarget(v.getText().toString()).enqueue(new Callback<TargetProto.Target>() {
                                @Override
                                public void onResponse(Call<TargetProto.Target> call, Response<TargetProto.Target> response) {
                                    mView.addTarget(response.body());
                                }

                                @Override
                                public void onFailure(Call<TargetProto.Target> call, Throwable t) {
                                    mView.addTarget(null);
                                }
                            });
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().show();

    }

    @Override
    public void fetchTargets() {
        mInteractor.fetchTargets().enqueue(new Callback<TargetProto.Targets>() {
            @Override
            public void onResponse(Call<TargetProto.Targets> call, Response<TargetProto.Targets> response) {
                mView.populateList(response.body());
            }

            @Override
            public void onFailure(Call<TargetProto.Targets> call, Throwable t) {
                mView.onError(t.toString());
            }
        });
    }
}