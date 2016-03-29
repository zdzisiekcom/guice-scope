package com.zdzisiek.guice.c03_custom_scope.window;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.Stream;

public class WindowScopeModule extends AbstractModule {


	@Override
	protected void configure() {

		WindowScopeScope scope = new WindowScopeScope();

		bindScope(WindowSingleton.class, scope);

		bind(WindowScopeScope.class).toInstance(scope);

		bindInterceptor(
			Matchers.annotatedWith(WindowSingleton.class),
			Matchers.annotatedWith(WindowContextAware.class),
			new ContextSwitcher(scope));

		bindInterceptor(
			Matchers.annotatedWith(WindowContext.class),
			Matchers.annotatedWith(WindowContextDispose.class),
			new ContextDisposer(scope));

		bindListener(Matchers.any(), new TypeListener() {

			@Override
			public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
				if (isWindowContext(typeLiteral)) {
					Optional<Method> method = getDisposeMethod(typeLiteral);
					if (!method.isPresent()) {
						throw new IllegalStateException("WindowContext object must have "
							+ "dispose method (annodated with @WindowContextDispose) ");
					}
				}
			}

			private <I> Optional<Method> getDisposeMethod(TypeLiteral<I> typeLiteral) {
				return Stream.of(typeLiteral.getRawType().getMethods())
					.filter(m -> m.isAnnotationPresent(WindowContextDispose.class))
					.findAny();
			}

			private <I> boolean isWindowContext(TypeLiteral<I> typeLiteral) {
				return typeLiteral.getRawType().isAnnotationPresent(WindowContext.class);
			}
		});

	}

}
