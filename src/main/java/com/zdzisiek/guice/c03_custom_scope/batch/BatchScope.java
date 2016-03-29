package com.zdzisiek.guice.c03_custom_scope.batch;

import com.google.common.collect.Maps;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;
import com.google.inject.Scopes;

import java.util.Map;

public class BatchScope implements Scope {

    private final ThreadLocal<Map<Key<?>, Object>> values = new ThreadLocal<Map<Key<?>, Object>>();

    @Override
    public <T> Provider<T> scope(Key<T> key, Provider<T> unscoped) {

       return () -> {

           Map<Key<?>, Object> scopedObjects = values.get();
           T current = (T) scopedObjects.get(key);

           if (current == null && !scopedObjects.containsKey(key)) {
               // create new object
               current = unscoped.get();
               // don't remember proxies; these exist only to serve circular dependencies
               if (Scopes.isCircularProxy(current)) {
                   return current;
               }
               scopedObjects.put(key, current);
           }

           return current;
       };
    }

    public void enter() {
        values.set(Maps.<Key<?>, Object>newHashMap());
    }

    public void exit() {
        values.remove();
    }
}

