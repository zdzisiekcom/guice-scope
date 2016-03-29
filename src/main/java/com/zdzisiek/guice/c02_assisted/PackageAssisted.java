package com.zdzisiek.guice.c02_assisted;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.zdzisiek.guice.domain.Package;
import com.zdzisiek.guice.domain.PackageService;

public class PackageAssisted implements Package {

    private final PackageService service;
    private final String from;
    private final String to;
    private final int weight;

    @Inject
    public PackageAssisted(PackageService service,
                           @Assisted("from") String from,
                           @Assisted("to") String to,
                           @Assisted int weight){

        this.service = service;
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String getFrom() {
        return from;
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    public void send(){
        service.sendPackage(this);
    }

    @Override
    public String toString() {
        return "PackageAssisted{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", weight='" + weight + '\'' +
                '}';
    }
}
