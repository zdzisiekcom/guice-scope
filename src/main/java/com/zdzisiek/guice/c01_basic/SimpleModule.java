package com.zdzisiek.guice.c01_basic;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.zdzisiek.guice.domain.*;
import com.zdzisiek.guice.domain.Package;

public class SimpleModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(PackageService.class).to(PackageServiceImpl.class).in(Scopes.SINGLETON);

        bind(TransportService.class)
                .annotatedWith(Plane.class)
                .to(PlaneTransportService.class)
                .in(Scopes.SINGLETON);

    }

    @Provides @Ship
    public TransportService shipFactory(){
        return new ShipTransportService();
    }

    @Provides
    public PackageImpl createPackage(){
        return new PackageImpl();
    }

}
