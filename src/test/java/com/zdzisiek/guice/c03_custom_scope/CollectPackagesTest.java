package com.zdzisiek.guice.c03_custom_scope;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.zdzisiek.guice.c03_custom_scope.batch.BatchModule;
import com.zdzisiek.guice.c03_custom_scope.batch.BatchRunner;
import com.zdzisiek.guice.domain.PackageImpl;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class CollectPackagesTest {

    @Inject
    BatchRunner batchRunner;

    @Inject
    Provider<CollectPackages> collectorProvider;

    @Inject
    Provider<CurrentTransport> transportProvider;

    @Test
    public void should_collect_all_packages_in_one_transport(){

        // when
        batchRunner.run(()->{
            // when
            collectorProvider.get().collect(pack1, pack2);
            collectorProvider.get().collect(pack3);

            // then
            CurrentTransport transport = transportProvider.get();
            assertThat(transport.packages).contains(pack1, pack2, pack3);
        });

    }


    @Test
    public void should_return_empty_transport_in_batch(){


        // when
        batchRunner.run(()->{
            // when
            CurrentTransport transport = transportProvider.get();
            // then
            assertThat(transport.packages).isEmpty();
        });

    }

    @Before
    public void setup(){
        Guice.createInjector(new BatchModule()).injectMembers(this);
    }


    PackageImpl pack1 = new PackageImpl("Jan", "Marcin", 12);
    PackageImpl pack2 = new PackageImpl("Jan", "Zenon", 23);
    PackageImpl pack3 = new PackageImpl("Marcin", "Zenon", 162);


}