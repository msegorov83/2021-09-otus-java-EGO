package ru.otus;

public class App {
    public static void main(String[] args) {
       TestLoggingInterface testLogging = Ioc.createTestLoging();
       testLogging.calculation(6);
       testLogging.calculation(6,6);
       testLogging.calculation(6,6,"6");
    }

}
