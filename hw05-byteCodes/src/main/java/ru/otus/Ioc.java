package ru.otus;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Ioc {

    private Ioc() {
    }

    static TestLoggingInterface createTestLoging() {

       InvocationHandler handler = new TestLoggingInvocationHandler(new TestLogging());
        TestLoggingInterface testLoggingInterface = (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
        return testLoggingInterface;
    }

    static class TestLoggingInvocationHandler implements InvocationHandler {
        private final Object target;
        private final Map<String, Integer> methods = new HashMap<>();

        TestLoggingInvocationHandler(Object target) {
            this.target = target;
        }

         public Object getTestLogging() {
             return target;
         }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            methods.merge(method.getName(), 1 ,Integer::sum);
            Class clazz = getTestLogging().getClass();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method m : methods) {

                if (m.isAnnotationPresent(Log.class) & m.getName().equals(method.getName()) & m.getParameterCount()==args.length) {
                        System.out.println("executed method: " + method.getName() + " param: " + Arrays.stream(args).toList());
                        return method.invoke(target, args);
                }
            }

            return null;
        }

        @Override
        public String toString() {
            return "TestLoggingInvocationHandler{" +
                    "target=" + target +
                    '}';
        }

    }

}
