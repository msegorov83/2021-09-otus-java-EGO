package ru.otus;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;
import java.nio.file.Files;
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
        public Object invoke(Object proxy, Method method, Object[] args) throws  IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
            var loader = new TestClassLoader();
            Class<?> clazz = loader.defineClass("ru.otus.TestLogging");
            Method[] methods = clazz.getDeclaredMethods();
            for (Method m : methods) {
                if (m.getName().equals(method.getName()) & m.getParameterCount()==args.length & m.isAnnotationPresent(Log.class)) {
                    System.out.println("executed method: " + method.getName() + " param: " + Arrays.stream(args).toList());
                    m.setAccessible(true);
                    Constructor constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    return m.invoke(constructor.newInstance(),args);
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
