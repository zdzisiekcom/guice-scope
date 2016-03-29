package com.zdzisiek.guice.c01_basic;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.zdzisiek.guice.domain.Package;
import com.zdzisiek.guice.domain.PackageImpl;
import com.zdzisiek.guice.domain.PackageService;
import org.junit.Before;
import org.junit.Test;

public class SimpleModuleTest {

    @Inject
    PackageService packageService;

    @Before
    public void setup(){
        Guice.createInjector(new SimpleModule()).injectMembers(this);
    }

    @Test
    public void send_heavy_package_by_ship(){

        packageService.sendPackage(new PackageImpl("Zenon", "Brygida", 123));

    }

}

