package ru.otus;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;

public class Ioc {
    private static ArrayList<Method> loggingMethods = new ArrayList();

    private Ioc() {
    }

    static TestLoggingInterface createTestLoging()  {
        TestLogging testLogging = new TestLogging();
        getInfoAboutClazz(testLogging);
        InvocationHandler handler = new TestLoggingInvocationHandler(testLogging, loggingMethods);
        TestLoggingInterface testLoggingInterface = (TestLoggingInterface) Proxy.newProxyInstance(Ioc.class.getClassLoader(),
                new Class<?>[]{TestLoggingInterface.class}, handler);
        return testLoggingInterface;
    }

    static private void getInfoAboutClazz(Object target)  {
        try {
            var loader = new TestLoggingInvocationHandler.TestClassLoader();
            Class<?> clazz = loader.defineClass(target.getClass().getName());
            Method[] declaredMethods = clazz.getDeclaredMethods();
            for (Method m : declaredMethods) {
                if (m.isAnnotationPresent(Log.class)) {
                    loggingMethods.add(m);
                }
            }
        } catch (IOException e) {}

    }

    static class TestLoggingInvocationHandler implements InvocationHandler {
        private final Object target;
        private final ArrayList<Method> loggingMethod;

        TestLoggingInvocationHandler(Object target, ArrayList<Method> loggingMethod) {
            this.target = target;
            this.loggingMethod = loggingMethod;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws  InvocationTargetException, IllegalAccessException {

            for (Method m : loggingMethod) {
                if (m.getName().equals(method.getName()) & m.getParameterCount()==args.length ) {
                  System.out.println("executed method: " + method.getName() + " param: " + Arrays.stream(args).toList());
                  return method.invoke(target, args);
                }
            }
            return null;
        }

        private static class TestClassLoader extends ClassLoader {
            Class<?> defineClass(String className) throws IOException {
                var file = new File(getFileName(className));
                byte[] bytecode = Files.readAllBytes(file.toPath());
                return super.defineClass(className, bytecode, 0, bytecode.length);
            }

            String getFileName(String className) {
                return "clazz" + File.separator + className.substring(className.lastIndexOf('.') + 1) + ".class";
            }
        }

        @Override
        public String toString() {
            return "TestLoggingInvocationHandler{" +
                    "target=" + target +
                    '}';
        }

    }

}
