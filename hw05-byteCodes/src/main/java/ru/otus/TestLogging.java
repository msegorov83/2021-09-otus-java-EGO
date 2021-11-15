package ru.otus;

public class TestLogging implements TestLoggingInterface {

    int param;
    int param1;
    int param2;
    String param3;

    @Log
    @Override
    public void calculation(int param) { this.param = param; }

    @Log
    @Override
    public void calculation(int param1, int param2) {
        this.param1 = param1;
        this.param2 = param2;
    }

    @Override
    public void calculation(int param1, int param2, String param3) {
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
    }

    @Override
    public String toString() {
        return "MyClassImpl{}";
    }
}
