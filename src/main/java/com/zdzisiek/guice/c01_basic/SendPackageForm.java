package com.zdzisiek.guice.c01_basic;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.zdzisiek.guice.domain.PackageImpl;
import com.zdzisiek.guice.domain.PackageService;

public class SendPackageForm {

    @Inject
    private PackageService packageService;

    @Inject
    private Provider<PackageImpl> packageProvider;

    public void submit(){
        PackageImpl aPackage = packageProvider.get();
        aPackage.setFrom("Jan");
        aPackage.setTo("Zenon");
        aPackage.setWeight(123);
        packageService.sendPackage(aPackage);
    }
}


