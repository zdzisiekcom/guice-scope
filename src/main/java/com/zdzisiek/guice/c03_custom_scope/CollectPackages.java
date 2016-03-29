package com.zdzisiek.guice.c03_custom_scope;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.zdzisiek.guice.domain.Package;

import static java.util.Arrays.stream;

public class CollectPackages {

    @Inject
    Provider<CurrentTransport> transport;

    public void collect(Package... packages) {

        CurrentTransport transport = this.transport.get();

        stream(packages)
                .forEach(p -> {
                    transport.add(p);
                });
    }

}
