package com.zdzisiek.guice.c02_assisted;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.zdzisiek.guice.domain.Package;
import com.zdzisiek.guice.domain.PackageService;
import com.zdzisiek.guice.domain.PackageServiceImpl;

public class AssistedInjectModule extends AbstractModule{

    @Override
    protected void configure() {

        install(new FactoryModuleBuilder()
                .implement(Package.class, PackageAssisted.class)
                .build(PackageFactory.class));
    }
}
