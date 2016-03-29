package com.zdzisiek.guice.c04_inject_annotations;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Provider;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class MessagesModuleTest {

    @Inject
    Provider<PackageForm> packageFormProvider;

    @Before
    public void setup(){
        Guice.createInjector(new MessagesModule()).injectMembers(this);
    }

    @Test
    public void messages_should_not_be_empty(){

        // when
        PackageForm form = packageFormProvider.get();

        assertThat(form.getFromName()).isNotEmpty();
        assertThat(form.getToName()).isNotEmpty();
    }
}