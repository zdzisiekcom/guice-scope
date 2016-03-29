package com.zdzisiek.guice.c03_custom_scope.window.usage;

import com.google.inject.Inject;
import com.zdzisiek.guice.c03_custom_scope.window.WindowSingleton;

@WindowSingleton
public class PackageValidator {

    @Inject
    PackageWindowView view;

    public boolean isValid(){
        assert view != null;
        return true;
    }

}
