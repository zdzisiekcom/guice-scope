package com.zdzisiek.guice.c03_custom_scope.batch;

import com.google.inject.AbstractModule;

public class BatchModule extends AbstractModule {

    @Override
    protected void configure() {

        BatchScope scope = new BatchScope();

        bindScope(BatchScoped.class, scope);

        bind(BatchScope.class).toInstance(scope);
    }
}
