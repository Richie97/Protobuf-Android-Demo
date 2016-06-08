package com.willowtree.notahitlist.injection;

import android.support.annotation.NonNull;

import com.willowtree.notahitlist.api.TargetService;
import com.willowtree.notahitlist.api.converter.ProtoConverterFactory;
import com.willowtree.notahitlist.view.TargetView;
import com.willowtree.notahitlist.interactor.TargetInteractor;
import com.willowtree.notahitlist.interactor.impl.TargetInteractorImpl;
import com.willowtree.notahitlist.presenter.TargetPresenter;
import com.willowtree.notahitlist.presenter.impl.TargetPresenterImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public final class TargetViewModule {
    /**
     * Stored view
     */
    private final TargetView mView;

    public TargetViewModule(@NonNull TargetView view) {
        mView = view;
    }

    @Provides
    public TargetView provideView() {
        return mView;
    }

    @Provides
    public TargetInteractor provideInteractor(TargetService service) {
        return new TargetInteractorImpl(service);
    }

    @Provides
    public TargetPresenter providePresenter(@NonNull TargetView view, @NonNull TargetInteractor interactor) {
        return new TargetPresenterImpl(view, interactor);
    }

    @Provides
    public TargetService provideApi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://peoplebuf.herokuapp.com/")
                .addConverterFactory(ProtoConverterFactory.create())
                .build();

        return retrofit.create(TargetService.class);
    }
}
