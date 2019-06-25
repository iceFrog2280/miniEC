package icefrog.com.latte;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SubjectProxy implements InvocationHandler {
    private Subject mTarget = null;
    public SubjectProxy(Subject target) {
        mTarget = target;
    }
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("proxy: "+o.getClass().getName()+" method = "+ method.getName());
        System.out.println("before...");
        method.invoke(mTarget, objects);
        System.out.println("after...");
        return null;
    }
}
