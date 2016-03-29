package com.zdzisiek.guice.c03_custom_scope.window;

import com.google.inject.Inject;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ContextDisposer implements MethodInterceptor {

	private final WindowScopeScope scope;

	public ContextDisposer(WindowScopeScope scope) {
		this.scope = scope;
	}

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		try {
			return methodInvocation.proceed();
		} finally {
			scope.clear(methodInvocation.getThis());
		}
	}
}

