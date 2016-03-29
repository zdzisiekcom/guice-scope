package com.zdzisiek.guice.c01_basic;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.testing.fieldbinder.Bind;
import com.google.inject.testing.fieldbinder.BoundFieldModule;
import com.google.inject.util.Modules;
import com.zdzisiek.guice.domain.PackageImpl;
import com.zdzisiek.guice.domain.PackageService;
import com.zdzisiek.guice.domain.TransportService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class BoundModuleTest {

    @Mock
    @Bind @Plane
    private TransportService planeTransportService;

    @Mock
    @Bind @Ship
    private TransportService shipTransportService;

    @Inject
    PackageService packageService;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        Guice.createInjector(
                Modules
                        .override(new SimpleModule())
                        .with(BoundFieldModule.of(this))
        ).injectMembers(this);
    }

    @Test
    public void send_heavy_package_by_ship(){

        // given
        PackageImpl aPackage = new PackageImpl("Zenon", "Brygida", 123);

        // when
        packageService.sendPackage(aPackage);

        // then
        Mockito.verify(shipTransportService).transportPackage(aPackage);

    }

    @Test
    public void send_light_package_by_ship(){

        // given
        PackageImpl aPackage = new PackageImpl("Brygida", "Zenon", 3);

        // when
        packageService.sendPackage(aPackage);

        // then
        Mockito.verify(planeTransportService).transportPackage(aPackage);

    }
}
