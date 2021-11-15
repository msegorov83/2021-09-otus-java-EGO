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
        return (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
    }


    static class TestLoggingInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface testLogging;
        private final Map<String, Method> methods = new HashMap<>();

        TestLoggingInvocationHandler(TestLoggingInterface testLogging) {
            this.testLogging = testLogging;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(Log.class)) {
                System.out.println("executed method: " + method.getName() + " param: " + Arrays.stream(args).toList());
                return method.invoke(testLogging, args);
            }
            return null;
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" +
                    "testLogging=" + testLogging +
                    '}';
        }

    }

}
