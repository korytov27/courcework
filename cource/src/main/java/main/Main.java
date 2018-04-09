package main;

import utils.CustomLogger;
import workflow.WorkFlow;

public class Main {

    public static void main(String[] args) {

        CustomLogger.info("Application started");

        WorkFlow<String> workFlow = new WorkFlow<>(String.class, random -> "kek");
        workFlow.perform();

        CustomLogger.info("Application shutdown");
    }
}
