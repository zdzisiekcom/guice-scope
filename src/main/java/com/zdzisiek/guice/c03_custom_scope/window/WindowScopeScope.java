package com.zdzisiek.guice.c03_custom_scope.window;

import com.google.common.base.Objects;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;
import com.google.inject.Scopes;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Stack;

public class WindowScopeScope implements Scope {

	public static final org.slf4j.Logger LOG = LoggerFactory.getLogger(WindowScopeScope.class);

	public Stack<Context> currentContextStack = new Stack<Context>();

	private BiMap<ContextKey, Object> objects = HashBiMap.create();

	@Override
	public <T> Provider<T> scope(Key<T> key, Provider<T> provider) {
		LOG.debug("Create provider for {}", key.getTypeLiteral().getRawType());
		return new WindowScopeProvider<>(key, provider);
	}

	public void enter(Object object) {
		Context context = getContextForObject(object);
		LOG.debug("Enter into context {}", context);
		getContextStack().push(context);
	}

	public void exit(Object object) {
		Context context = getContextForObject(object);
		LOG.debug("Exit from context {}", context);
		removeContextFromStack(context);
		if (!getContextStack().isEmpty()) {
			LOG.debug("Active context is {}", context);
		}
	}

	public void clear(Object object) {
		Context objectContext = getContextForObject(object);
		objects.entrySet()
			.removeIf(entry -> entryFromContext(entry).equals(objectContext));
	}

	private Context entryFromContext(
		Map.Entry<ContextKey, Object> entry) {
		return entry.getKey().context;
	}

	private Context getContextForObject(Object object) {
		if (objects.containsValue(object)) {
			return objects.inverse().get(object).context;
		} else {
			throw new IllegalStateException("No context for: " + object);
		}
	}

	private Stack<Context> getContextStack() {
		return currentContextStack;
	}

	private boolean removeContextFromStack(Context context) {
		return getContextStack().remove(context);
	}

	private class WindowScopeProvider<T> implements Provider<T> {

		private final Key<T> typeKey;
		private final Provider<T> parentProvider;

		public WindowScopeProvider(Key<T> typeKey, Provider<T> parentProvider) {
			this.typeKey = typeKey;
			this.parentProvider = parentProvider;
		}

		@Override
		public T get() {

			Class<? super T> keyType = typeKey.getTypeLiteral().getRawType();
			LOG.debug("Provide object for type {} in context", keyType);

			if (getContextStack().isEmpty()) {
				if (canCreateNewContext(keyType)) {
					return createNewObjectInNewContext(keyType);
				} else {
					throw new IllegalStateException("No context root for bean "	+ keyType.getTypeName());
				}
			}

			ContextKey contextKey = getContextKey(typeKey);

			if (objects.containsKey(contextKey)) {
				LOG.debug("Get {} object from window", keyType);
				return (T) objects.get(contextKey);
			}

			return createNewObjectInContext(contextKey);
		}

		private T createNewObjectInNewContext(Class<? super T> keyType) {

			LOG.debug("Create new {} context root", keyType);

			getContextStack().push(new Context());

			T item = parentProvider.get();
			ContextKey contextKey = getContextKey(typeKey);
			objects.put(contextKey, item);

			getContextStack().pop();

			return (T) item;
		}

		private T createNewObjectInContext(ContextKey contextKey) {

			LOG.debug("Create new object {} for window {}", contextKey.key, contextKey.context);

			T item = parentProvider.get();

			if (!objects.containsValue(item) && !Scopes.isCircularProxy(item)) {
				objects.put(contextKey, item);
			} else {
				ContextKey existingItem = objects.inverse().get(item);
				if (existingItem.context != contextKey.context) {
					throw new IllegalStateException("Duplicate context objects");
				}
			}

			LOG.debug("Added new object {} for window {}", contextKey.key, contextKey.context);

			return item;
		}

		private ContextKey getContextKey(Key<T> type) {
			return new ContextKey(getContextStack().peek(), type);
		}

		private boolean canCreateNewContext(Class<? super T> keyType) {
			return keyType.isAnnotationPresent(WindowContext.class);
		}
	}

	private static class Context {

		public Context() {

		}
	}

	private static class ContextKey {

		Context context;
		Key key;

		public ContextKey(Context context, Key key) {
			this.context = context;
			this.key = key;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;

			ContextKey that = (ContextKey) o;

			return Objects.equal(this.context, that.context) &&
				Objects.equal(this.key, that.key);
		}

		@Override
		public int hashCode() {
			return Objects.hashCode(context, key);
		}
	}


}