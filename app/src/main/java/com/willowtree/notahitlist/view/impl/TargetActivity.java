package com.willowtree.notahitlist.view.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.willowtree.notahitlist.R;
import com.willowtree.notahitlist.TargetProto;
import com.willowtree.notahitlist.injection.AppComponent;
import com.willowtree.notahitlist.injection.DaggerTargetViewComponent;
import com.willowtree.notahitlist.injection.TargetViewModule;
import com.willowtree.notahitlist.presenter.BasePresenter;
import com.willowtree.notahitlist.presenter.TargetPresenter;
import com.willowtree.notahitlist.recycler.TargetAdapter;
import com.willowtree.notahitlist.view.TargetView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public final class TargetActivity extends BaseActivity implements TargetView {
    @Inject
    TargetPresenter mPresenter;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        mPresenter.fetchTargets();
    }

    @OnClick(R.id.fab)
    public void addTarget(){
        mPresenter.addTarget(TargetActivity.this);
    }


    @Override
    protected void setupComponent(@NonNull AppComponent parentComponent) {
        DaggerTargetViewComponent.builder()
                .appComponent(parentComponent)
                .targetViewModule(new TargetViewModule(this))
                .build()
                .inject(this);
    }

    @Override
    protected BasePresenter getBasePresenter() {
        return mPresenter;
    }

    @Override
    public void addTarget(TargetProto.Target target) {
        mPresenter.fetchTargets();
    }

    @Override
    public void populateList(TargetProto.Targets targets) {
        recycler.setAdapter(new TargetAdapter(targets));
    }

    @Override
    public void onError(String error) {
//        Snackbar.make(recycler, error, Snackbar.LENGTH_SHORT).show();
    }
}
