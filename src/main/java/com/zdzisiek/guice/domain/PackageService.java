package com.zdzisiek.guice.domain;

import com.google.inject.ImplementedBy;
import com.google.inject.Singleton;

import java.util.Optional;

@ImplementedBy(PackageServiceImpl.class)
public interface PackageService {

    Optional<Package> findPackageByDestination(String to);

    void sendPackage(Package pack);
}
