package com.willowtree.notahitlist.interactor.impl;

import com.willowtree.notahitlist.TargetProto;
import com.willowtree.notahitlist.api.TargetService;
import com.willowtree.notahitlist.interactor.TargetInteractor;

import java.io.IOException;
import java.util.UUID;

import javax.inject.Inject;

import retrofit2.Call;

public final class TargetInteractorImpl implements TargetInteractor {
    TargetService service;

    @Inject
    public TargetInteractorImpl(TargetService service) {
        this.service = service;
    }

    @Override
    public Call<TargetProto.Target> addTarget(String name) throws IOException {
        TargetProto.Target person = new TargetProto.Target();
        person.id = UUID.randomUUID().toString();
        person.name = name;
        return service.addTarget(person);
    }

    @Override
    public Call<TargetProto.Targets> fetchTargets() {
        return service.getTargets();
    }
}