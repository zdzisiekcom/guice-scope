package com.zdzisiek.guice.c03_custom_scope.window.usage;

import com.zdzisiek.guice.c03_custom_scope.window.WindowSingleton;

@WindowSingleton
public class PackageWindowView {

    public void show(){
        System.out.println("Show package window");
    }

}
