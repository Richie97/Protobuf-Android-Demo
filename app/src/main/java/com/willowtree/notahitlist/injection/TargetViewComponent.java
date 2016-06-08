package com.willowtree.notahitlist.injection;

import com.willowtree.notahitlist.view.impl.TargetActivity;

import dagger.Component;

@ActivityScope
@Component(dependencies = AppComponent.class, modules = TargetViewModule.class)
public interface TargetViewComponent {
    void inject(TargetActivity activity);
}