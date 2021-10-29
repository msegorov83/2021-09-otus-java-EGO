package ru.otus.testing.services;

import ru.otus.testing.annotation.AfterEach;
import ru.otus.testing.annotation.BeforeEach;
import ru.otus.testing.annotation.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunnerTests {

    public static void main(String [] args) throws Exception {

        Class<CalculatorTest> clazz = CalculatorTest.class;
        Constructor[] constructors = clazz.getDeclaredConstructors();

        Constructor constructor = null;
        for (int i = 0; i < constructors.length; i++) {
            constructor = constructors[i];
            Object obj = constructor.newInstance();

            Method[] methods = obj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Test.class)) {
                    executeBefore(obj);
                    method.invoke(obj);
                    executeAfter(obj);
                }
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
