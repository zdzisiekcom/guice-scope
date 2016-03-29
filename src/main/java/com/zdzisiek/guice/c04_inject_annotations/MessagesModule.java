package com.zdzisiek.guice.c04_inject_annotations;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

import java.lang.reflect.Field;

public class MessagesModule extends AbstractModule {

    @Override
    protected void configure() {

        MessageProvider messageProvider = new MessageProvider();
        bind(MessageProvider.class).toInstance(messageProvider);

        bindListener(Matchers.any(), new TypeListener()
        {
            @Override
            public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter)
            {
                Class<?> clazz = type.getRawType();
                while (clazz != null) {
                    for (Field field : clazz.getDeclaredFields()) {
                        if (field.isAnnotationPresent(Message.class)) {
                            Message prop = field.getAnnotation(Message.class);
                            encounter.register(new MessageInjector<>(messageProvider, prop, field));
                        }
                    }
                    clazz = clazz.getSuperclass();
                }
            }
        });
    }
}
