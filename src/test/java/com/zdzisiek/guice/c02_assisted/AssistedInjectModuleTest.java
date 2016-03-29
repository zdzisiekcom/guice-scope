package com.zdzisiek.guice.c02_assisted;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.zdzisiek.guice.c01_basic.SimpleModule;
import org.junit.Before;
import org.junit.Test;

public class AssistedInjectModuleTest {

    @Inject
    PackageFactory packageFactory;

    @Test
    public void new_package_should_have_package_service(){

        PackageAssisted aPackage = packageFactory.create("Zenon", "Nowak", 34);

        // when
        aPackage.send();

    }

    @Before
    public void setup(){
        Guice.createInjector(
                new SimpleModule(),
                new AssistedInjectModule()
        ).injectMembers(this);
    }
}