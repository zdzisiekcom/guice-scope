package com.zdzisiek.guice.c03_custom_scope.batch;

import com.google.inject.Inject;

public class BatchRunner {

    @Inject
    BatchScope scope;

    public void run(Runnable r){
        try {
            scope.enter();
            r.run();
        } finally {
            scope.exit();
        }
    }
}
