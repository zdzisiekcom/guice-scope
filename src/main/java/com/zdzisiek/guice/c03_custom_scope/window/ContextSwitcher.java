package com.zdzisiek.guice.c03_custom_scope.window;

import com.google.inject.Inject;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ContextSwitcher implements MethodInterceptor {

	private final WindowScopeScope scope;

	@Inject
	public ContextSwitcher(WindowScopeScope scope) {
		this.scope = scope;
	}

	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		boolean in = false;
		Object aThis = methodInvocation.getThis();
		try {
			in = true;
			scope.enter(aThis);
			return methodInvocation.proceed();
		} finally {
			if (in) {
				scope.exit(aThis);
			}
		}
	}
}