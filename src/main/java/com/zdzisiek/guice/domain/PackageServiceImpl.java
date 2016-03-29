package com.zdzisiek.guice.domain;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.zdzisiek.guice.c01_basic.Plane;
import com.zdzisiek.guice.c01_basic.Ship;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton
public class PackageServiceImpl implements PackageService {

    @Inject
    @Plane
    TransportService planeService;

    @Inject
    @Ship
    TransportService shipService;


    private List<Package> packages = new ArrayList<>();

    @Override
    public Optional<Package> findPackageByDestination(String to) {
        return packages.stream()
                .filter(p -> p.getTo().contains(to))
                .findFirst();
    }

    @Override
    public void sendPackage(Package pack) {
        packages.add(pack);
        if (pack.getWeight() < 10) {
            planeService.transportPackage(pack);
        } else {
            shipService.transportPackage(pack);
        }
    }
}
