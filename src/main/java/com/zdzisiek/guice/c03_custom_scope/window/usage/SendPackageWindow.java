package com.zdzisiek.guice.c03_custom_scope.window.usage;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.zdzisiek.guice.c03_custom_scope.window.WindowContext;
import com.zdzisiek.guice.c03_custom_scope.window.WindowContextAware;
import com.zdzisiek.guice.c03_custom_scope.window.WindowContextDispose;
import com.zdzisiek.guice.c03_custom_scope.window.WindowSingleton;
import com.zdzisiek.guice.domain.PackageService;

@WindowContext
@WindowSingleton
public class SendPackageWindow {

    private final PackageService packageService;
    private final Provider<PackageWindowView> viewProvider;

    @Inject
    public SendPackageWindow(PackageService packageService, Provider<PackageWindowView> viewProvider) {

        this.packageService = packageService;
        this.viewProvider = viewProvider;
    }

    @WindowContextAware
    public PackageWindowView createView(){
        return viewProvider.get();
    }

    @WindowContextDispose
    public void close(){
        System.out.println("close window");
    }
}
