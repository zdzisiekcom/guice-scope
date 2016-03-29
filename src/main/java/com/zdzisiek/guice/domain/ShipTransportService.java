package com.zdzisiek.guice.domain;

public class ShipTransportService implements TransportService {

    @Override
    public void transportPackage(Package pack) {
        System.out.println("Transport " + pack + " by ship");
    }
}
