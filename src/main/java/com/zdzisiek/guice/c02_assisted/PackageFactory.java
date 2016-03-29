package com.zdzisiek.guice.c02_assisted;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.name.Named;

public interface PackageFactory {

    PackageAssisted create(@Assisted("from") String from,
                           @Assisted("to") String to,
                           int weight);

}
