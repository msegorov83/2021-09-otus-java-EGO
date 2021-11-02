package ru.otus.testing.services;

import ru.otus.testing.annotation.AfterEach;
import ru.otus.testing.annotation.BeforeEach;
import ru.otus.testing.annotation.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class RunnerTests {

    public static void main(String [] args) throws Exception {

        Class<CalculatorTest> clazz = CalculatorTest.class;
        Constructor[] constructors = clazz.getDeclaredConstructors();
        Constructor constructor =
                Arrays.stream(constructors).filter(e -> e.getName() == clazz.getName()).findAny().get();

        for (Method method : clazz.getDeclaredMethods()) {
            Object obj = constructor.newInstance();
            if (method.isAnnotationPresent(Test.class)) {
                executeBefore(obj);

                try {
                    method.invoke(obj);
                    System.out.println("TEST: " + method.getName() + " SUCCESS");
                } catch (InvocationTargetException e) {
                    System.out.println("TEST: " + method.getName() + " FAILED");
                }

                executeAfter(obj);
            }
        }
    }

    private static void executeBefore(Object obj) throws InvocationTargetException, IllegalAccessException {

        Method[] methods = obj.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(BeforeEach.class)) {
                method.invoke(obj);
            }
        }

    }

    private static void executeAfter(Object obj) throws InvocationTargetException, IllegalAccessException {

        Method[] methods = obj.getClass().getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(AfterEach.class)) {
                method.invoke(obj);
            }
        }

    }
}
