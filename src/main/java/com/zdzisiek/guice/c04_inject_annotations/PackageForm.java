package com.zdzisiek.guice.c04_inject_annotations;

public class PackageForm {

    @Message("from.name")
    String fromName;

    @Message("to.name")
    String toName;

    public String getToName() {
        return toName;
    }

    public String getFromName() {
        return fromName;
    }

    public void show(){
        System.out.printf("Enter %s and %s\n", fromName, toName);
    }
}


