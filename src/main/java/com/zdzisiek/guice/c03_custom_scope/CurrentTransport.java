package com.zdzisiek.guice.c03_custom_scope;

import com.zdzisiek.guice.c03_custom_scope.batch.BatchScoped;
import com.zdzisiek.guice.domain.Package;

import java.util.ArrayList;
import java.util.List;

@BatchScoped
public class CurrentTransport {

    List<Package> packages
            = new ArrayList<>();

    public void add(Package pack)
    {
        packages.add(pack);
    }

}


