package com.zdzisiek.guice.c04_inject_annotations;

import com.google.inject.MembersInjector;
import com.google.inject.spi.InjectionListener;

import java.lang.reflect.Field;

public class MessageInjector<I> implements MembersInjector<I> {

    private MessageProvider messageProvider;
    private final Message message;
    private final Field field;

    public MessageInjector(MessageProvider messageProvider, Message message, Field field) {
        this.messageProvider = messageProvider;
        this.message = message;
        this.field = field;
        field.setAccessible(true);
    }

    @Override
    public void injectMembers(I instance) {

        try {
            field.set(instance, messageProvider.get(message.value()));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}


